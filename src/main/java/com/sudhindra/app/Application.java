package com.sudhindra.app;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
public class Application {
	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	@Autowired
	private Environment environment;

	public static void main(String[] args) {
		LOGGER.debug("Starting App !");
		SpringApplication.run(Application.class, args);
	}

	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();

		driverManagerDataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
		driverManagerDataSource.setUrl(environment.getProperty("spring.datasource.url"));
		driverManagerDataSource.setUsername(environment.getProperty("spring.datasource.username"));
		driverManagerDataSource.setPassword(environment.getProperty("spring.datasource.password"));

		LOGGER.debug("DataSource: " + driverManagerDataSource);
		return driverManagerDataSource;
	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) throws Exception {
		Properties properties = new Properties();

		properties.put("hibernate.dialect", environment.getProperty("spring.jpa.properties.hibernate.dialect"));
		properties.put("hibernate.show_sql", environment.getProperty("spring.jpa.show-sql"));
		properties.put("current_session_context_class", //
				environment.getProperty("spring.jpa.properties.hibernate.current_session_context_class"));
		properties.put("hibernate.temp.use_jdbc_metadata_defaults", false);

		LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
		localSessionFactoryBean.setPackagesToScan(new String[] { "" });
		localSessionFactoryBean.setDataSource(dataSource);
		localSessionFactoryBean.setHibernateProperties(properties);
		localSessionFactoryBean.afterPropertiesSet();
				
		SessionFactory sessionFactory = localSessionFactoryBean.getObject();
		LOGGER.debug("SessionFactory: " + sessionFactory);
		return sessionFactory;
	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager(sessionFactory);
		return hibernateTransactionManager;
	}

}
