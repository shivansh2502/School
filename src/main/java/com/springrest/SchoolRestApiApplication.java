package com.springrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableJpaRepositories({"com.springrest.dao.mysqldao"})
@EnableElasticsearchRepositories({"com.springrest.dao.elasticdao"})
@EnableMongoRepositories({"com.springrest.dao.mongodao"})
@EnableCaching
public class SchoolRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolRestApiApplication.class, args);
	}

}
