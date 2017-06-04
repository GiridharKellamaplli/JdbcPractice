package com.tek.jdbcdemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan(basePackages = { "com.tek.jdbcdemo" })
@PropertySource("classpath:config.properties")
public class BeansConfig {

	private @Value("${jdbc.driverClassName}") String jdbcDriverClass;

	private @Value("${jdbc.url}") String jdbcUrl;

	@Value("${jdbc.userName}")
	private String username;

	@Value("${jdbc.passWord}")
	private String password;

	@Bean
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource(jdbcUrl,username,password);
		dataSource.setDriverClassName(jdbcDriverClass);
		return dataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}

	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
		return new NamedParameterJdbcTemplate(dataSource());
	}
}
