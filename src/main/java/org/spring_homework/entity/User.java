package org.spring_homework.entity;

public class User {

	private final String login;

	public User(String login) {
		this.login = login;
	}

	@Override
	public String toString() {
		return "User{" +
				"login='" + login + '\'' +
				'}';
	}
}
