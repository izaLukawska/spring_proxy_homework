package org.spring_homework;

import org.spring_homework.entity.User;
import org.spring_homework.repository.BaseRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
		BaseRepository myBean = context.getBean(BaseRepository.class);
		User user1 = new User("user1Login");
		myBean.save(user1);
		System.out.println(myBean.findById(1L));

		User user2 = new User("user2Login");
		myBean.save(user2);
		System.out.println(myBean.findById(1L));
	}
}
