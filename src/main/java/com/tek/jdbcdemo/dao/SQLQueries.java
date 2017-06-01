package com.tek.jdbcdemo.dao;

public class SQLQueries {

	final protected static String CREATE_TABLE_PERSON = "CREATE TABLE person(id INT NOT NULL AUTO_INCREMENT, name VARCHAR(200) NOT NULL, age INT, PRIMARY KEY(id));";
	final protected static String INSERT_PERSON = "INSERT INTO person (name,age) values(?,?);";
	final protected static String SELECT_PERSON_BY_NAME = "SELECT * FROM person where name=?;";

	/*
	 * MySql database connection properties.
	 */
	final protected static String DRIVER_NAME = "com.mysql.jdbc.Driver";
	final protected static String URL = "jdbc:mysql://localhost:3306/sample";
	final protected static String UNAME = "root";
	final protected static String PASSWORD = "root";

}
