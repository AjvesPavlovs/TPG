package jtt.tpg.dao;

import jtt.tpg.dao.base.GenericDAO;
import jtt.tpg.dto.Artist;

//this interface is made to save methods for Artist entity

public interface ArtistDAO extends GenericDAO<Artist>{
	Artist getByName(String name);
}
