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

	@Bean
	private DataSource dataSource() throws URISyntaxException {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		URI uri = new URI(environment.getProperty("DATABASE_URL"));
		String user = uri.getUserInfo().split(":")[0];
		String password = uri.getUserInfo().split(":")[1];
		String url = "jdbc:mysql://"+uri.getHost()+":"+uri.getPort()+uri.getPath();
		
		dataSource.setUsername(user);
		dataSource.setPassword(password);
		dataSource.setUrl(url);
		dataSource.setDriverClassName("org.postgresql.Driver");
		
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
