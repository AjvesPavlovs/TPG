package jtt.tpg.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import jtt.tpg.dto.ArtistGenre;

public interface ArtistGenreRepository extends CrudRepository<ArtistGenre, Integer>{
    @Query("SELECT a.id FROM ArtistGenre a WHERE a.artist_id = :artist_id AND a.genre_id = :genre_id")
    Integer findID(@Param("artist_id") int artist_id, @Param("genre_id") int genre_id);
    
    @Query("SELECT a FROM ArtistGenre a WHERE a.id = :id")
    ArtistGenre findByID(@Param("artistID") int artistID, @Param("genreID") int genreID);
    
    @Query("SELECT a FROM ArtistGenre a WHERE a.artist_id = :artistID")
    List<ArtistGenre> findByArtistID(@Param("artistID") int artistID);
    
    @Query("SELECT a FROM ArtistGenre a WHERE a.genre_id = :genreID")
    List<ArtistGenre> findByGenreID(@Param("genreID") int genreID);
}