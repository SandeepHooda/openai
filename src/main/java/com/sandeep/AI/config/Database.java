package com.sandeep.AI.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.sandeep.AI.Util.SCV;

@Configuration
public class Database {
	
		
	@Bean
    public DataSource dataSource(){
        
        DriverManagerDataSource source = new DriverManagerDataSource();
        source.setDriverClassName(SCV.getValue(SCV.DB_DRIVER));
        source.setUrl(SCV.getValue(SCV.DB_URL));
        source.setUsername(SCV.getValue(SCV.DB_USER));
        source.setPassword(SCV.getValue(SCV.DB_PWD));
        return source;
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(){
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(this.dataSource());
        return namedParameterJdbcTemplate;
    }
	
	

	
}
