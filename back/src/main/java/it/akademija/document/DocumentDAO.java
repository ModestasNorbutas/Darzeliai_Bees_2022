package it.akademija.document;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DocumentDAO extends JpaRepository<DocumentEntity, Long> {

	DocumentEntity getDocumentById(long id);
	
	void deleteByUploaderId(long uploaderId);

	@Query("SELECT new it.akademija.document.DocumentViewmodel("
			+ "de.id, "
			+ "de.name, "
			+ "de.uploadDate) "
			+ "FROM DocumentEntity de")
	Page<DocumentViewmodel> findAllDocumentViewModel(Pageable pageable);
	
}
