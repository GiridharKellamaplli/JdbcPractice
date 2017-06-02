package com.tek.jdbcdemo;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tek.jdbcdemo.dao.JdbcDemoDAO;
import com.tek.jdbcdemo.model.Person;

public class JdbcDemoTest {

	public static void main(String[] args) {
		JdbcDemoTest demoTest = new JdbcDemoTest();
		demoTest.insertAndGetPerson();
	}

	private void insertAndGetPerson() {

		ApplicationContext context = new ClassPathXmlApplicationContext("beansConfig.xml");

		JdbcDemoDAO demoDAO = context.getBean("jdbcDemoDAO", JdbcDemoDAO.class);
		// JdbcDemoDAO demoDAO = new JdbcDemoDAO();
		// demoDAO.savePerson("Giridhar", 25);
		List<Person> persons = demoDAO.getPersonsByName("Giridhar");
		for (Person person : persons) {
			System.out.println(person.getName() + "  " + person.getAge());
		}

	}

}
