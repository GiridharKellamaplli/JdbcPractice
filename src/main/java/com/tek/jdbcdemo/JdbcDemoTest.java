package com.tek.jdbcdemo;

import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tek.jdbcdemo.config.BeansConfig;
import com.tek.jdbcdemo.dao.JdbcDemoDAO;
import com.tek.jdbcdemo.model.Person;

public class JdbcDemoTest {

	public static void main(String[] args) {
		JdbcDemoTest demoTest = new JdbcDemoTest();
		demoTest.insertAndGetPerson();
	}

	private void insertAndGetPerson() {

//		ApplicationContext context = new ClassPathXmlApplicationContext("beansConfig.xml");
		ApplicationContext context = new AnnotationConfigApplicationContext(BeansConfig.class);

		JdbcDemoDAO demoDAO = context.getBean("jdbcDemoDAO", JdbcDemoDAO.class);

		List<Person> persons = demoDAO.getPersons();
		for (Person person : persons) {
			System.out.println(person);
			System.out.println(person.getName() + "   " + person.getAge());
		}
		System.out.println("----------------Person By Name------------------------------");
		List<Person> personsListByName = demoDAO.getPersonByName("Giridhar");
		for (Person person : personsListByName) {
			System.out.println(person);
			System.out.println(person.getName() + "   " + person.getAge());
		}

		System.out.println("-------Person By Id-----------------");
		Person person = demoDAO.getPersonById(1);
		System.out.println(person.getName() + "   " + person.getAge());

		System.out.println("----------Persons Count--------------");
		System.out.println(demoDAO.getPersonsCount());

		System.out.println("-------------Person By Name-----------");
		System.out.println(demoDAO.getPersonNameById(1));

		demoDAO.savePersonUsingNamedParamJdbcTemplate(new Person("Divya", 23));
	}

}
