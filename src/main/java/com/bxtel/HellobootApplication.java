package com.bxtel;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
//import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/*
 * plugin --install mobz/elasticsearch-head
 * http://localhost:9200/_plugin/head/
 * 
 * plugin -install royrusso/elasticsearch-HQ
 * http://www.elastichq.org/support_plugin.html
 * http://localhost:9200/_plugin/HQ/
 * 
 */
//@ImportResource(value="classpath:applicationContext-bean.xml")
//TomcatEmbeddedServletContainer
@SpringBootApplication
@EnableScheduling
//@EnableElasticsearchRepositories(basePackages = "com/bxtel/search")
@EnableJpaRepositories(basePackages = "com/bxtel/dao")
public class HellobootApplication extends SpringBootServletInitializer{
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(HellobootApplication.class);
	}
    public static void main(String[] args) {
        SpringApplication.run(HellobootApplication.class, args);
    }
}
