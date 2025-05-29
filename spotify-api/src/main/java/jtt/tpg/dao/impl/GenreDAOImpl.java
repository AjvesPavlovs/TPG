package jtt.tpg.dao.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jtt.tpg.dto.Genre;
import jtt.tpg.repositories.GenreRepository;
import jtt.tpg.dao.GenreDAO;

//this class is made to describe methods that is saved in GenreDAO 

@Service
public class GenreDAOImpl implements GenreDAO{
	
	@Autowired		//creates an object without a constructor function
	GenreRepository genreRepository;
	
	@Override
	public Genre insert(Genre value) {
		return genreRepository.save(value);
	}

	@Override
	public Genre update(Genre value, int id) {
		Genre genreData = genreRepository.findById(id).get();

		if(Objects.nonNull(value.getName()) && !value.getName().equals(""))
			genreData.setName(value.getName());
		
		return genreRepository.save(genreData);
	}

	@Override
	public void delete(int id) {
		genreRepository.deleteById(id);
	}

	@Override
	public int getID(Genre value) {
		return genreRepository.findID(value.getName());
	}

	@Override
	public Genre getByID(int id) {
		return genreRepository.findById(id).get();
	}

	@Override
	public List<Genre> getAllData() {
		List<Genre> genres = (List<Genre>) genreRepository.findAll();
		return genres;
	}
}