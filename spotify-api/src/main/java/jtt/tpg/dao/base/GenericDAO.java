package jtt.tpg.dao.base;

import java.util.List;

//this interface is made to save methods for all entities

public interface GenericDAO<T> {
	T insert(T value);
	T update(T value, int id);
	void delete(int id);
	int getID(T value);
	T getByID(int id);
	List<T> getAllData();
}
