package jtt.tpg.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import jtt.tpg.dto.Autors;

public interface AutorsRepository extends CrudRepository<Autors, Integer>{
	
}
