package jtt.tpg.dao.base;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T> {

	T save(T value);
	T update(T value, int id);
	void delete(int id);
	T getID(T value);
	T getByID(int id);
	List<T> getAllData();
}