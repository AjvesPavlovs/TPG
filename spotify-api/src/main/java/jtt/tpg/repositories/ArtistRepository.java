package jtt.tpg.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import jtt.tpg.dto.Artist;

//this class is made to describe queries needed for Artist entity

public interface ArtistRepository extends CrudRepository<Artist, Integer>{
	@Query("SELECT id FROM Brush b WHERE b.name = :name")
	int findID(@Param("name") String name);
	
	@Query("SELECT a FROM Artist a WHERE a.name = :name")
	int findByName(@Param("name") String name);
	

}
