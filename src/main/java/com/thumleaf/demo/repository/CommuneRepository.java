package com.java.springboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.java.springboot.model.Commune;

public interface CommuneRepository extends JpaRepository<Commune, Long> {

	@Query("from Commune")
	List<Commune> getCommune();

	@Query("from Commune where id = ?1")
	Optional<Commune> getCommuneById(long communeId);

	@Query(nativeQuery = true, value="select nom from commune as nomCommune where id=?1")
	String getNomCommune(Commune commune);

}
