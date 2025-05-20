package jtt.tpg.dao.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jtt.tpg.dao.ArtistDAO;
import jtt.tpg.dto.Artist;
import jtt.tpg.repositories.ArtistRepository;

//this class is made to describe methods that is saved in ArtistDAO 

@Service
public class ArtistDAOImpl implements ArtistDAO{

	@Autowired		//creates an object without a constructor function
	ArtistRepository artistRepository;

	@Override
	public Artist insert(Artist value) {
		return artistRepository.save(value);
	}

	@Override
	public Artist update(Artist value, int id) {
		Artist artistData = artistRepository.findById(id).get();

		if(Objects.nonNull(value.getName()) && !value.getName().equals(""))
			artistData.setName(value.getName());
		if(Objects.nonNull(value.getFollowers()) && value.getFollowers() > -1)
			artistData.setFollowers(value.getFollowers());
		
		return artistRepository.save(artistData);
	}

	@Override
	public void delete(int id) {
		artistRepository.deleteById(id);
	}

	@Override
	public int getID(Artist value) {
		return artistRepository.findID(value.getName());
	}

	@Override
	public Artist getByID(int id) {
		Artist artistData = artistRepository.findById(id).get();
		return artistData;
	}

	@Override
	public List<Artist> getAllData() {
		List<Artist> artists = (List<Artist>) artistRepository.findAll();
		return artists;
	}
	


}
