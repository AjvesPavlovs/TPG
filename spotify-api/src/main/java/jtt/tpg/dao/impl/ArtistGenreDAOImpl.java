package jtt.tpg.dao.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jtt.tpg.dao.ArtistGenreDAO;
import jtt.tpg.dto.ArtistGenre;
import jtt.tpg.repositories.ArtistGenreRepository;

//this class is made to describe methods that is saved in GenreDAO 

@Service
public class ArtistGenreDAOImpl implements ArtistGenreDAO{

	@Autowired		//creates an object without a constructor function
	ArtistGenreRepository agRepository;
	
	@Override
	public ArtistGenre insert(ArtistGenre value) {
		for (ArtistGenre existingAG : this.getAllData()) {
			if(existingAG.getArtistID() == value.getArtistID() && existingAG.getGenreID() == value.getGenreID()) return null;	
		}
		return agRepository.save(value);
	}

	@Override
	public ArtistGenre update(ArtistGenre value, int id) {
		ArtistGenre agData = agRepository.findById(id).get();

		if(Objects.nonNull(value.getArtistID()) && Objects.nonNull(value.getGenreID()))
			agData.setArtistID(value.getArtistID());
			agData.setGenreID(value.getGenreID());
		
		return agRepository.save(agData);
	}

	@Override
	public void delete(int id) {
		agRepository.deleteById(id);
	}

	@Override
	public int getID(ArtistGenre value) {
		return agRepository.findID(value.getArtistID(), value.getGenreID());
	}

	@Override
	public ArtistGenre getByID(int id) {
		return agRepository.findById(id).get();
	}

	@Override
	public List<ArtistGenre> getAllData() {
		List<ArtistGenre> artistGenres = (List<ArtistGenre>) agRepository.findAll();
		return artistGenres;
	}

	@Override
	public List<ArtistGenre> getByArtist(int artistID) {
		List<ArtistGenre> artistGenres = (List<ArtistGenre>) agRepository.findByArtistID(artistID);
		return artistGenres;
	}

	@Override
	public List<ArtistGenre> getByGenre(int genreID) {
		List<ArtistGenre> artistGenres = (List<ArtistGenre>) agRepository.findByGenreID(genreID);
		return artistGenres;
	}
}