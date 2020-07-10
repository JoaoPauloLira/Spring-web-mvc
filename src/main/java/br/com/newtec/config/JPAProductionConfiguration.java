package br.com.newtec.config;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Profile("prod")
public class JPAProductionConfiguration {
	
	@Autowired
	private Environment environment;
	private URI uri;
	private String user;
	private String password;
	private String url;
	
	
	
	public JPAProductionConfiguration() throws URISyntaxException {
		uri = new URI(environment.getProperty("DATABAS_URL"));
		user = uri.getUserInfo().split(":")[0];
		password = uri.getUserInfo().split(":")[1];
		url = "jdbc:mysql://"+uri.getHost()+":"+uri.getPort()+uri.getPath();
	}

	@Bean
	private DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");

		dataSource.setUsername(user);
		dataSource.setPassword(password);
		dataSource.setUrl(url);
		
		return dataSource;
	}
	
	@Bean
	private Properties jpaProperties() {
		Properties jpaProperties = new Properties();
		jpaProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		jpaProperties.setProperty("hibernate.show_sql", "true");
		jpaProperties.setProperty("hibernate.format_sql", "true");
		jpaProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		//jpaProperties.setProperty("log.hibernate", "true");
		
		return jpaProperties;
	}
}
