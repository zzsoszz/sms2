//package com.bxtel.search;
//
//import org.springframework.data.domain.Page;
//
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.elasticsearch.annotations.Query;
//import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
///*
// * elasticsearch geo filter
// * Geo Distance Filter
// * https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-geo-distance-filter.html
// * http://docs.spring.io/spring-data/elasticsearch/docs/1.1.0.RC1/reference/html/
// * 
// * http://localhost:9200/_plugin/inquisitor/#/queries
// * 
// */
//public interface SmsSearchRepository extends ElasticsearchRepository<Sms, String>{
////	 
////	 @Query("{\"bool\" : {\"must\" : {\"field\" : {\"name\" : \"?0\"}}}}")
////	 Page<Sms> findByStatus(String status,Pageable pageable);
////	
//	
//	@Query("{\"bool\" : {\"must\" : {\"field\" : {\"name\" : \"?0\"}}}}")
//	Page<Sms> findByStatus(String status,Pageable pageable);
//}