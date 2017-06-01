package com.tek.jdbcdemo;

import java.util.List;

import com.tek.jdbcdemo.dao.JdbcDemoDAO;
import com.tek.jdbcdemo.model.Person;

public class JdbcDemoTest {

	public static void main(String[] args) {
		JdbcDemoTest demoTest = new JdbcDemoTest();
		demoTest.insertAndGetPerson();
	}

	private void insertAndGetPerson() {
		JdbcDemoDAO demoDAO = new JdbcDemoDAO();
		// demoDAO.savePerson("Giridhar", 25);
		List<Person> persons = demoDAO.getPersonsByName("Giridhar");
		for (Person person : persons) {
			System.out.println(person.getName() + "  " + person.getAge());
		}

	}

}
