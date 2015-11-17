//package com.bxtel.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.PagingAndSortingRepository;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jms.annotation.JmsListener;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
///*
// * 
// * http://projects.spring.io/spring-boot/
// * 
// * https://github.com/spring-projects/spring-boot/tree/master/spring-boot-samples/spring-boot-sample-data-jpa/src/main/java/sample/data/jpa
// * 
// * Spring Data JPA——参考文档 
// * http://jpa.coding.io/#project
// * 
// * jpa 规范
// * http://www.yiibai.com/jpa/jpa_criteria_api.html
// * 
// * http://mvnrepository.com/artifact/org.hibernate/hibernate-validator
// * 
// * SPRING DATA JPA 中几种缓存的配置
// * http://ju.outofmemory.cn/entry/42566
// * 
// * Spring事务管理中@Transactional的参数配置 
// *  @Stateless
//	@Transactional
//	@PersistenceContext
// * http://blog.csdn.net/zsm653983/article/details/8103466
// */
//
//@RestController
//public class UserController {
//	
//	@Autowired
//	JmsTemplate jmsTemplate;
//	
////	@Autowired
////	JdbcTemplate jdbcTemplate;
////	
//	
////	@Autowired
////	PagingAndSortingRepository userRepository;
//	@Autowired
//	CrudRepository userRepository;
//	
//	@JmsListener(destination = "longtongdataQueue")
//    public void processMessage(String content) {
//		 System.out.println("content:"+content);
//    }
//	
//}
