package com.tek.jdbcdemo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.tek.jdbcdemo.model.Person;

@Component
public class JdbcDemoDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	// public List<Map<String, Object>> getPersons() {
	// return jdbcTemplate.queryForList(SQLQueries.SELECT_ALL_PERSONS);
	// }

	public List<Person> getPersons() {
		return jdbcTemplate.query(SQLQueries.SELECT_ALL_PERSONS, personRowMapper);
	}

	public void createTablePerson() {
		jdbcTemplate.execute(SQLQueries.CREATE_TABLE_PERSON);
	}

	/**
	 * 
	 * Insert the {@link Person} object into the DB by using
	 * {@link JdbcTemplate} update().
	 * 
	 * @param person
	 */
	public void savePerson(Person person) {
		int rowsEffected = jdbcTemplate.update(SQLQueries.INSERT_PERSON, person.getName(), person.getAge());
	}

	public void savePersonUsingNamedParamJdbcTemplate(Person person) {
		String sql = "INSERT INTO person (name,age) value (:name,:age)";
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource("age", person.getAge()).addValue("name",
				person.getName());
		namedParameterJdbcTemplate.update(sql, sqlParameterSource);
	}

	public void updatePersonById(int id) {
		int rowsEffected = jdbcTemplate.update(SQLQueries.UPDATE_PERSON, id);
	}

	public void deletePersonById(int id) {
		int rowsEffected = jdbcTemplate.update(SQLQueries.DELETE_PERSON, id);
	}

	// public List<Map<String, Object>> getPersonsByName(String name) {
	// return jdbcTemplate.queryForList(SQLQueries.SELECT_PERSON_BY_NAME, name);
	// }

	public List<Person> getPersonByName(String name) {
		return jdbcTemplate.query(SQLQueries.SELECT_PERSON_BY_NAME, personRowMapper, name);
	}

	public Person getPersonById(int id) {
		// return
		// jdbcTemplate.queryForObject(SQLQueries.SELECT_PERSON_BY_ID,personRowMapper,id);
		// //or
		return jdbcTemplate.queryForObject(SQLQueries.SELECT_PERSON_BY_ID, new Object[] { id }, personRowMapper);
	}

	public int getPersonsCount() {
		String sql = "SELECT COUNT(*) FROM person";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public void savePersons(final List<Person> persons) {
		jdbcTemplate.batchUpdate(SQLQueries.INSERT_PERSON, new BatchPreparedStatementSetter() {

			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Person person = persons.get(i);
				ps.setString(1, person.getName());
				ps.setInt(2, person.getAge());
			}

			public int getBatchSize() {
				return persons.size();
			}
		});
	}

	public String getPersonNameById(int id) {
		String sql = "SELECT name FROM person where id=?";
		return jdbcTemplate.queryForObject(sql, String.class, id);
	}

	private static final RowMapper<Person> personRowMapper = new RowMapper<Person>() {

		public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
			Person person = new Person();
			person.setName(rs.getString("name"));
			person.setAge(rs.getInt("age"));
			return person;
		}
	};

	// OR
	private static final class PersonRowMapper implements RowMapper<Person> {

		public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
			Person person = new Person();
			person.setName(rs.getString("name"));
			person.setAge(rs.getInt("age"));
			return person;
		}

	}

}
