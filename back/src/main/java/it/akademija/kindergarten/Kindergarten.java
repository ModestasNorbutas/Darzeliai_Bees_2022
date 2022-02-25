package it.akademija.kindergarten;

import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import it.akademija.application.Application;
import it.akademija.kindergartenchoise.KindergartenChoise;

@Entity
public class Kindergarten {
	
	@Id
	@Column(name = "kindergarten_id")
	@Pattern(regexp = "^(?!\\s*$)[0-9\\s]{9}$|", message = "Įstaigos kodas turi būti sudarytas iš 9 skaitmenų")
	private String id;

	@NotBlank(message = "Pavadinimas privalomas")
	@Pattern(regexp = "\\S[\\s\\S]{2,49}")
	@Column(name = "name", unique = true)
	private String name;

	@Column
	@NotBlank(message = "Adresas privalomas")
	private String address;

	@Column
	@Pattern(regexp = "^[\\p{L}\\s]{3,20}$")
	@NotBlank(message = "Seniūnija privaloma")
	private String elderate;
	
	@Column
	@Pattern(regexp = "^[A-zÀ-ž\\s-]{2,32}")
	private String managerName;
	
	@Column
	@Pattern(regexp = "^[A-zÀ-ž\\s-]{2,32}")
	private String managerSurname;	

	@Min(value = 0, message = "Laisvų vietų skaičius negali būti mažesnis už 0")
	@Max(value=999, message = "Laisvų vietų skaičius negali būti didesnis už 999")
	private int capacityAgeGroup2to3;
	
	private int placesTakenAgeGroup2to3;

	@Min(value = 0, message = "Laisvų vietų skaičius negali būti mažesnis už 0")
	@Max(value=999, message = "Laisvų vietų skaičius negali būti didesnis už 999")
	private int capacityAgeGroup3to6;
	
	private int placesTakenAgeGroup3to6;

	@OneToMany(mappedBy = "kindergarten", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<KindergartenChoise> kindergartenChoises;
	
	@OneToMany(mappedBy = "approvedKindergarten", cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE}, fetch = FetchType.LAZY)
	private Set<Application> approvedApplications;

	public Kindergarten() {

	}

	public Kindergarten(String id, String name, String address, String elderate, int capacityAgeGroup2to3,
			int capacityAgeGroup3to6) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.elderate = elderate;
		this.capacityAgeGroup2to3 = capacityAgeGroup2to3;
		this.capacityAgeGroup3to6 = capacityAgeGroup3to6;
	}	
	
	public Kindergarten(
		@Pattern(regexp = "^(?!\\s*$)[0-9\\s]{9}$|", message = "Įstaigos kodas turi būti sudarytas iš 9 skaitmenų") String id,
		@NotBlank(message = "Pavadinimas privalomas") @Pattern(regexp = "\\S[\\s\\S]{2,49}") String name,
		@NotBlank(message = "Adresas privalomas") String address,
		@Pattern(regexp = "^[\\p{L}\\s]{3,20}$") @NotBlank(message = "Seniūnija privaloma") String elderate,
		@Pattern(regexp = "^[A-zÀ-ž\\s-]{2,32}") String managerName,
		@Pattern(regexp = "^[A-zÀ-ž\\s-]{2,32}") String managerSurname,
		@Min(value = 0, message = "Laisvų vietų skaičius negali būti mažesnis už 0") @Max(value = 999, message = "Laisvų vietų skaičius negali būti didesnis už 999") int capacityAgeGroup2to3,
		int placesTakenAgeGroup2to3,
		@Min(value = 0, message = "Laisvų vietų skaičius negali būti mažesnis už 0") @Max(value = 999, message = "Laisvų vietų skaičius negali būti didesnis už 999") int capacityAgeGroup3to6,
		int placesTakenAgeGroup3to6, Set<KindergartenChoise> kindergartenChoises,
		Set<Application> approvedApplications) {
	    super();
	    this.id = id;
	    this.name = name;
	    this.address = address;
	    this.elderate = elderate;
	    this.managerName = managerName;
	    this.managerSurname = managerSurname;
	    this.capacityAgeGroup2to3 = capacityAgeGroup2to3;
	    this.placesTakenAgeGroup2to3 = placesTakenAgeGroup2to3;
	    this.capacityAgeGroup3to6 = capacityAgeGroup3to6;
	    this.placesTakenAgeGroup3to6 = placesTakenAgeGroup3to6;
	    this.kindergartenChoises = kindergartenChoises;
	    this.approvedApplications = approvedApplications;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getElderate() {
	    return elderate;
	}

	public String getManagerName() {
	    return managerName;
	}

	public void setManagerName(String managerName) {
	    this.managerName = managerName;
	}

	public String getManagerSurname() {
	    return managerSurname;
	}

	public void setManagerSurname(String managerSurname) {
	    this.managerSurname = managerSurname;
	}

	public void setElderate(String elderate) {
	    this.elderate = elderate;
	}

	public int getCapacityAgeGroup2to3() {
	    return capacityAgeGroup2to3;
	}

	public void setCapacityAgeGroup2to3(int capacityAgeGroup2to3) {
		this.capacityAgeGroup2to3 = capacityAgeGroup2to3;
	}

	public int getCapacityAgeGroup3to6() {
		return capacityAgeGroup3to6;
	}

	public void setCapacityAgeGroup3to6(int capacityAgeGroup3to6) {
		this.capacityAgeGroup3to6 = capacityAgeGroup3to6;
	}

	public int getPlacesTakenAgeGroup2to3() {
		return placesTakenAgeGroup2to3;
	}

	public void setPlacesTakenAgeGroup2to3(int placesTakenAgeGroup2to3) {
		this.placesTakenAgeGroup2to3 = placesTakenAgeGroup2to3;
	}

	public int getPlacesTakenAgeGroup3to6() {
		return placesTakenAgeGroup3to6;
	}

	public void setPlacesTakenAgeGroup3to6(int placesTakenAgeGroup3to6) {
		this.placesTakenAgeGroup3to6 = placesTakenAgeGroup3to6;
	}

	public Set<KindergartenChoise> getKindergartenChoises() {
		return kindergartenChoises;
	}

	public void setKindergartenChoises(Set<KindergartenChoise> kindergartenChoises) {
		this.kindergartenChoises = kindergartenChoises;
	}
	
	

	public Set<Application> getApprovedApplications() {
		return approvedApplications;
	}

	public void setApprovedApplications(Set<Application> approvedApplications) {
		this.approvedApplications = approvedApplications;
	}

	@Override
	public int hashCode() {
	    return Objects.hash(address, approvedApplications, capacityAgeGroup2to3, capacityAgeGroup3to6, elderate, id,
		    kindergartenChoises, managerName, managerSurname, name, placesTakenAgeGroup2to3,
		    placesTakenAgeGroup3to6);
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
		return true;
	    if (obj == null)
		return false;
	    if (getClass() != obj.getClass())
		return false;
	    Kindergarten other = (Kindergarten) obj;
	    return Objects.equals(address, other.address)
		    && Objects.equals(approvedApplications, other.approvedApplications)
		    && capacityAgeGroup2to3 == other.capacityAgeGroup2to3
		    && capacityAgeGroup3to6 == other.capacityAgeGroup3to6 && Objects.equals(elderate, other.elderate)
		    && Objects.equals(id, other.id) && Objects.equals(kindergartenChoises, other.kindergartenChoises)
		    && Objects.equals(managerName, other.managerName)
		    && Objects.equals(managerSurname, other.managerSurname) && Objects.equals(name, other.name)
		    && placesTakenAgeGroup2to3 == other.placesTakenAgeGroup2to3
		    && placesTakenAgeGroup3to6 == other.placesTakenAgeGroup3to6;
	}

}
