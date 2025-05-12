package jtt.tpg.dao.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jtt.tpg.dao.AutorDAO;
import jtt.tpg.dto.Autors;
import jtt.tpg.repositories.AutorsRepository;

@Service
public class AutorDAOImpl implements AutorDAO{

	@Autowired
	AutorsRepository autorsRepository;
	
	@Override
	public Autors save(Autors value) {
		return autorsRepository.save(value);
	}

	@Override
	public Autors update(Autors value, int id) {
		Autors autorData = autorsRepository.findById(id).get();
		
		if(Objects.nonNull(value.getName()) && !value.getName().equals(""));
		autorData.setName(value.getName());
		
		return autorsRepository.save(autorData);
	}

	@Override
	public void delete(int id) {
		autorsRepository.deleteById(id);
	}

	@Override
	public Autors getID(Autors value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Autors getByID(int id) {
		Autors autorData = autorsRepository.findById(id).get();
		return autorData;
	}

	@Override
	public List<Autors> getAllData() {
		List<Autors> autori = (List<Autors>) autorsRepository.findAll();
		return autori;
	}

}
