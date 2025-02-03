package org.spring_homework.repository;

import org.spring_homework.beanPostProcessor.Transaction;
import org.spring_homework.entity.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepository implements BaseRepository<Long, User>{

	private final Map<Long, User> users = new HashMap<>();

	@Override
	public User findById(Long id) {
		return users.get(id);
	}

	@Transaction (methodName = "save")
	@Override
	public void save(User user) {
		users.put(1L, user);
	}
}
