package jtt.tpg.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import jtt.tpg.dto.Genre;

//this class is made to describe queries needed for Genre entity

public interface GenreRepository extends CrudRepository<Genre, Integer>{
	@Query("SELECT id FROM Genre a WHERE a.name = :name")
	int findID(@Param("name") String name);
	
	@Query("SELECT a FROM Genre a WHERE a.name = :name")
	int findByGenreName(@Param("name") String genreName);
}
