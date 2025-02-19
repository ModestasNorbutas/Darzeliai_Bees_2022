package it.akademija.kindergarten;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.akademija.application.Application;
import it.akademija.application.ApplicationDAO;
import it.akademija.application.ApplicationStatus;
import it.akademija.kindergartenchoise.KindergartenChoise;
import it.akademija.kindergartenchoise.KindergartenChoiseDAO;

@Service
public class KindergartenService {

	private static final Logger LOG = LoggerFactory.getLogger(KindergartenService.class);

	@Autowired
	private KindergartenDAO gartenDao;

	@Autowired
	private ApplicationDAO applicationDao;
	
	@Autowired
	private KindergartenChoiseDAO kindergartenChoiseDAO;

	/**
	 * Get all kindergarten ID's, names and addresses where capacity in any age
	 * group is more than zero
	 * 
	 * @return a list of kindergarten ID, names and addresses sorted by name ASC
	 */
	@Transactional(readOnly = true)
	public List<KindergartenInfo> getAllWithNonZeroCapacity() {

		List<Kindergarten> kindergartens = gartenDao.findAllWithNonZeroCapacity(Sort.by("name").ascending());

		return kindergartens.stream().map(garten -> new KindergartenInfo(
			garten.getId(), garten.getName(), garten.getAddress(), garten.getElderate(),
			garten.getLatitude(), garten.getLongitude())).collect(Collectors.toList());
	}

	/**
	 * Gel all elderates' names
	 * 
	 * @return list of elderates
	 */
	public Set<String> getAllElderates() {

		return gartenDao.findDistinctElderates();
	}

	/**
	 * 
	 * Returns a page of Kindergarten with specified page number and page size
	 * 
	 * @param pageable
	 * @return page from kindergarten database
	 */
	@Transactional(readOnly = true)
	public Page<Kindergarten> getKindergartenPage(Pageable pageable, String filter) {
	    
	    if (filter.equals("")) {
		return gartenDao.findAllKindergarten(pageable);
	    }
	    
		return gartenDao.findByNameContainingIgnoreCase(filter, pageable);
	}

	/**
	 * Save new kindergarten to database
	 * 
	 * @param kindergarten
	 */
	@Transactional
	public void createNewKindergarten(KindergartenDTO kindergarten) {

		gartenDao.save(new Kindergarten(
			kindergarten.getId(),
			kindergarten.getName(),
			kindergarten.getAddress(),
			kindergarten.getElderate(),
			kindergarten.getManagerName(),
			kindergarten.getManagerSurname(),
			kindergarten.getCapacityAgeGroup2to3(),
			kindergarten.getCapacityAgeGroup3to6(),
			kindergarten.getLatitude(),
			kindergarten.getLongitude()			
			));

	}

	/**
	 * Find kindergarten by id. Read only
	 * 
	 * @param id
	 * @return kindergarten or null if not found
	 */
	@Transactional(readOnly = true)
	public Kindergarten findById(String id) {

		return gartenDao.findById(id).orElse(null);
	}

	/**
	 * Find kindergarten by name. Read only
	 * 
	 * @param name
	 * @return kindergarten or null if not found
	 */
	@Transactional(readOnly = true)
	public boolean nameAlreadyExists(String name, String id) {

		Kindergarten kindergarten = gartenDao.findByName(name);

		if (kindergarten != null && kindergarten.getId() != id) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * Delete kindergarten with specified id. Also deletes all related kindergarten
	 * choises
	 * 
	 * @param id
	 */
	//@Transactional
	public ResponseEntity<String> deleteKindergarten(String id) {

		Kindergarten kindergarten = gartenDao.findById(id).orElse(null);

		if (kindergarten != null) {
			Set<Application> applicationQueue = kindergarten.getApprovedApplications();
			Set<KindergartenChoise> kindergartenChoises = kindergarten.getKindergartenChoises();
			
			for (Application application : applicationQueue) {
				application.setApprovedKindergarten(null);

				if (application.getKindergartenChoises().size() > 1) {
					application.setStatus(ApplicationStatus.Pateiktas);
				} else {
					application.setStatus(ApplicationStatus.Neaktualus);
					
				}
				
				applicationDao.saveAndFlush(application);
			}
			
			for (KindergartenChoise kindergartenChoise : kindergartenChoises) {
				kindergartenChoise.setKindergarten(null);;
				
				kindergartenChoiseDAO.saveAndFlush(kindergartenChoise);
			}
			
			gartenDao.saveAndFlush(kindergarten);
			gartenDao.deleteById(id);

			LOG.info("** UserService: trinamas darželis ID [{}] **", id);

			return new ResponseEntity<String>("Darželis ištrintas sėkmingai", HttpStatus.OK);

		} else {

			return new ResponseEntity<String>("Darželis su tokiu įstaigos kodu nerastas", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Update kindergarten
	 * 
	 * @param currentInfo
	 * @param kindergarten
	 */
	@Transactional
	public void updateKindergarten(String id, KindergartenDTO updatedInfo) {

		Kindergarten current = gartenDao.findById(id).orElse(null);

		current.setName(updatedInfo.getName());
		current.setAddress(updatedInfo.getAddress());
		current.setElderate(updatedInfo.getElderate());
		current.setManagerName(updatedInfo.getManagerName());
		current.setManagerSurname(updatedInfo.getManagerSurname());
		current.setCapacityAgeGroup2to3(updatedInfo.getCapacityAgeGroup2to3());
		current.setCapacityAgeGroup3to6(updatedInfo.getCapacityAgeGroup3to6());
		current.setLatitude(updatedInfo.getLatitude());
		current.setLongitude(updatedInfo.getLongitude());

		gartenDao.save(current);
	}

	/**
	 * 
	 * Kindergarten prioritize statistics
	 * 
	 * @param pageable
	 * 
	 * @return statistics
	 */
	public Page<KindergartenStatistics> getKindergartenStatistics(Pageable pageable) {

		return gartenDao.findAllChoises(pageable);
	}

	/**
	 * Delete kindergarten by name. Used during DB setup
	 * 
	 * @param name
	 */
	@Transactional
	public void deleteByName(String name) {

		gartenDao.deleteByName(name);
	}

	/**
	 * 
	 * Set number of taken places in Kindergarten for corresponding age group by 1
	 * 
	 * @param garten
	 */
	public void decreaseNumberOfTakenPlacesInAgeGroup(Kindergarten garten, long age) {

		int takenPlaces = 0;

		if (age >= 1 && age < 3) {

			takenPlaces = garten.getPlacesTakenAgeGroup2to3() - 1;
			if (takenPlaces > 0) {
				garten.setPlacesTakenAgeGroup2to3(takenPlaces);
			}
			garten.setPlacesTakenAgeGroup2to3(0);

		} else if (age >= 3) {
			takenPlaces = garten.getPlacesTakenAgeGroup3to6() - 1;
			if (takenPlaces > 0) {
				garten.setPlacesTakenAgeGroup3to6(takenPlaces);
			}
			garten.setPlacesTakenAgeGroup3to6(0);
		}

		gartenDao.save(garten);
	}

	/**
	 * Increase number of taken places for specific Kindergarten age group by 1
	 * 
	 * @param garten
	 * @param age
	 */
	public void increaseNumberOfTakenPlacesInAgeGroup(Kindergarten garten, long age) {

		int capacity = 0;

		if (age >= 1 && age < 3) {
			capacity = garten.getPlacesTakenAgeGroup2to3() + 1;
			garten.setPlacesTakenAgeGroup2to3(capacity);
		} else if (age >= 3 && age < 7) {
			capacity = garten.getPlacesTakenAgeGroup3to6() + 1;
			garten.setPlacesTakenAgeGroup3to6(capacity);
		}
		gartenDao.save(garten);
	}

	/**
	 * Upon approving Application queue decreases number of available places in all
	 * Kindergartens' age groups by the number of places that were assigned as taken
	 * during Application processing stage. Also resets number of taken in all
	 * groups to zero.
	 */
	public void decreaseNumberOfAvailablePlaces() {

		List<Kindergarten> kindergartens = gartenDao.findAll();

		for (Kindergarten garten : kindergartens) {

			garten.setCapacityAgeGroup2to3(garten.getCapacityAgeGroup2to3() - garten.getPlacesTakenAgeGroup2to3());
			garten.setCapacityAgeGroup3to6(garten.getCapacityAgeGroup3to6() - garten.getPlacesTakenAgeGroup3to6());
			garten.setPlacesTakenAgeGroup2to3(0);
			garten.setPlacesTakenAgeGroup3to6(0);

		}

		gartenDao.saveAll(kindergartens);
	}

	/**
	 * Upon deleting or deactivating an approved application, increases number of
	 * available places in specified Kindergarten's age group.
	 * 
	 * @param approved kindergarten
	 * @param child's  age
	 */
	public void increaseNumberOfAvailablePlacesInAgeGroup(Kindergarten garten, long age) {

		int capacity = 0;

		if (age >= 1 && age < 3) {
			capacity = garten.getCapacityAgeGroup2to3() + 1;
			garten.setCapacityAgeGroup2to3(capacity);
		} else if (age >= 3 && age < 7) {
			capacity = garten.getCapacityAgeGroup3to6() + 1;
			garten.setCapacityAgeGroup3to6(capacity);
		}

		gartenDao.save(garten);
	}

	public KindergartenInfo getKindergartenInfoByApplicationId(Long id) {
		return gartenDao.getKindergartenInfoByApplicationId(id);
	}

	public Kindergarten getKindergartenByApplicationId(String id) {
		return gartenDao.getById(id);
	}


}
