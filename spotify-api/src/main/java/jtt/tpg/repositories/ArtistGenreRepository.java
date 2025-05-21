package jtt.tpg.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArtistGenreRepository {
	@Query("SELECT id FROM artist_genres a WHERE a.artist_id = :artist_id AND a.genre_id = :genre_id")
	int findID(@Param("artist") String name);
	
	@Query("SELECT a FROM Artist a WHERE a.name = :name")
	int findByName(@Param("name") String name);
}
