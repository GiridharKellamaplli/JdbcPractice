package com.tek.jdbcdemo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcDemoDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Map<String, Object>> getPersons() {
		return jdbcTemplate.queryForList(SQLQueries.SELECT_ALL_PERSONS);
	}

	public void createTablePerson() {
		jdbcTemplate.execute(SQLQueries.CREATE_TABLE_PERSON);
	}

	public void savePerson(String name, int age) {
		jdbcTemplate.execute(SQLQueries.INSERT_PERSON);
	}

	public List<Map<String, Object>> getPersonsByName(String name) {
		return jdbcTemplate.queryForList(SQLQueries.SELECT_PERSON_BY_NAME, name);
	}

}
