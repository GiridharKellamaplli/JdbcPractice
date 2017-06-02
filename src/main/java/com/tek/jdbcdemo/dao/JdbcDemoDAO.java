package com.tek.jdbcdemo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tek.jdbcdemo.model.Person;

@Component
public class JdbcDemoDAO {

	@Autowired
	private DataSource dataSource;

	public Connection establishConnection() {

		Connection connection = null;
		try {
			connection = dataSource.getConnection();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;

	}

	public void createTablePerson() {
		PreparedStatement preparedStatement = null;
		Connection connection = establishConnection();
		try {
			preparedStatement = connection.prepareStatement(SQLQueries.CREATE_TABLE_PERSON);
			preparedStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public int savePerson(String name, int age) {

		PreparedStatement preparedStatement = null;
		Connection connection = establishConnection();
		int isInserted = 0;
		try {
			preparedStatement = connection.prepareStatement(SQLQueries.INSERT_PERSON);
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, age);
			isInserted = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return isInserted;

	}

	public List<Person> getPersonsByName(String name) {

		PreparedStatement preparedStatement = null;
		Connection connection = establishConnection();
		List<Person> persons = new ArrayList<Person>();
		try {
			preparedStatement = connection.prepareStatement(SQLQueries.SELECT_PERSON_BY_NAME);
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int age = resultSet.getInt("age");
				Person person = new Person(name, age);
				persons.add(person);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return persons;

	}

}
