package com.estufa.utfpr.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.estufa.utfpr.model.NivesLeituras;

public interface NiveisLeiturasRepository extends CrudRepository<NivesLeituras, Long> {
	
	@Query("select u from NivesLeituras u where u.data = ?1")
	List<NivesLeituras>findByDate(Date data);
	
	@Query("select u from NivesLeituras u where u.data between ?1 and ?2")
	List<NivesLeituras>findByDatesBetween(Date data1, Date data2);
	
	

}
