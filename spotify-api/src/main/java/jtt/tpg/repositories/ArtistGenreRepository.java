package jtt.tpg.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import jtt.tpg.dto.ArtistGenre;

public interface ArtistGenreRepository extends CrudRepository<ArtistGenre, Integer>{
	@Query("SELECT id FROM artist_genres a WHERE a.artist_id = :artist_id AND a.genre_id = :genre_id")
	int findID(@Param("artist") String name);
	
	@Query("SELECT a FROM ArtistGenres a WHERE a.artistID = :artistID AND a.genreID = :genreID")
	int findByID(@Param("id") int artistID, int genreID);
	
	@Query("SELECT a FROM ArtistGenres a WHERE a.artistID = :artistID")
	List<ArtistGenre> findByArtistID(@Param("artistID") int artistID);
	
	@Query("SELECT a FROM ArtistGenres a WHERE a.genreID = :genreID")
	List<ArtistGenre> findByGenreID(@Param("genreID") int genreID);
}