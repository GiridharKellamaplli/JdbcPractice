package com.tek.jdbcdemo;

import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tek.jdbcdemo.dao.JdbcDemoDAO;

public class JdbcDemoTest {

	public static void main(String[] args) {
		JdbcDemoTest demoTest = new JdbcDemoTest();
		demoTest.insertAndGetPerson();
	}

	private void insertAndGetPerson() {

		ApplicationContext context = new ClassPathXmlApplicationContext("beansConfig.xml");

		JdbcDemoDAO demoDAO = context.getBean("jdbcDemoDAO", JdbcDemoDAO.class);

		List<Map<String, Object>> persons = demoDAO.getPersons();
		for (Map<String, Object> person : persons) {
			System.out.println(person);
			System.out.println(person.get("id") + "  " + person.get("name") + "  " + person.get("age"));
		}
		System.out.println("----------------------------------------------");
		List<Map<String, Object>> personsList = demoDAO.getPersonsByName("Giridhar");
		for (Map<String, Object> person : personsList) {
			System.out.println(person);
			System.out.println(person.get("id") + "  " + person.get("name") + "  " + person.get("age"));
		}
	}

}
