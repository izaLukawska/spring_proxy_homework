package org.spring_homework.repository;

public interface BaseRepository<K, T> {

	T findById(K id);

	void save(T object);
}
