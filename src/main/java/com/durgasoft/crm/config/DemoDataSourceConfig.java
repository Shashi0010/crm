package com.durgasoft.crm.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Configuration
public class DemoDataSourceConfig {

	@Value("${spring.datasource.url}")
	private String dbUrl;

	
	@Bean
	public DataSource dataSource() {
      HikariConfig config = new HikariConfig();
      config.setJdbcUrl(dbUrl);
      return new HikariDataSource(config);
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean 
	entityManagerFactory(EntityManagerFactoryBuilder builder, DataSource dataSource) {
		
		return builder.dataSource(dataSource).build();
	}
	
	@Bean
	public DataSource securityDataSource() {
		return DataSourceBuilder.create().build();
	}
}
