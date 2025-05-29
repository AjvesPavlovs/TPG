package jtt.tpg.dao;

import java.util.List;

import jtt.tpg.dao.base.GenericDAO;
import jtt.tpg.dto.ArtistGenre;

public interface ArtistGenreDAO extends GenericDAO<ArtistGenre>{
	List<ArtistGenre> getByArtist(int artistID);
	List<ArtistGenre> getByGenre(int genreID);
}