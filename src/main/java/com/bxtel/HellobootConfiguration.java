package com.bxtel;

import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

@Configuration
public class HellobootConfiguration {
	
	@Autowired
    private DataSource dataSource;
	
	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
	    TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
	    factory.setPort(9000);//119.254.84.182
	    factory.setSessionTimeout(10, TimeUnit.MINUTES);
	    factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/notfound.html"));
	    return factory;
	}
	
//	@Autowired
//	EntityManagerFactory emf;
//	
//	@Autowired
//	PlatformTransactionManager platformTransactionManager;
//	
//	

	
//	@Bean(name = "transactionManager")
//	public PlatformTransactionManager transactionManager() {
//	  JpaTransactionManager tm =   new JpaTransactionManager();
//      tm.setEntityManagerFactory(emf);
//      tm.setDataSource(dataSource);
//	  return tm;
//	}
//	
//	
//	@Bean(name = "transactionInterceptor")
//	public TransactionInterceptor transactionInterceptor() {
//	  org.springframework.transaction.interceptor.TransactionInterceptor tm =   new org.springframework.transaction.interceptor.TransactionInterceptor();
//	  org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource nameMatchTransactionAttributeSource =   new org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource();
//	  tm.setTransactionManager(platformTransactionManager);
//	  return tm;
//	}
	
}

//@Bean
//public User getPerson()
//{
//	return new User("qingtian","123456");
//}
//
//@Bean
//public FilterRegistrationBean mdcFilterRegistrationBean() {
//	FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//	MDCInsertingServletFilter loggingFilter = new MDCInsertingServletFilter();
//	registrationBean.setFilter(loggingFilter);
//	registrationBean.setOrder(Integer.MAX_VALUE - 1);
//	return registrationBean;
//}
//@Bean(name = "entityManagerFactory")
//public LocalContainerEntityManagerFactoryBean emf() {
//  LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
//  emf.setDataSource(dataSource);
//  emf.setPackagesToScan(
//      new String[] {"your.package"});
//  emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//  return emf;
//}
//
//@Autowired
//EntityManagerFactory emf;
//@Bean(name = "transactionManager")
//public PlatformTransactionManager transactionManager() {
//  JpaTransactionManager tm = 
//      new JpaTransactionManager();
//      tm.setEntityManagerFactory(emf);
//      tm.setDataSource(dataSource);
//  return tm;
//}
//

//http://stackoverflow.com/questions/26286736/how-to-verify-spring-aspecj-transactions
//@Configuration
//@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
//@ComponentScan
//@EnableAsync
//@EnableScheduling
//@EnableEntityLinks
//@EnableAspectJAutoProxy
//@EnableApiResources(apiPrefix = "")
//@EnableJpaRepositories(transactionManagerRef = "transactionManager")
//@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
//@EnableConfigurationProperties
//@EnableAutoConfiguration
//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
//@EnableWebSocketMessageBroker
//@Slf4j

//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
//@EnableAutoConfiguration
//@ComponentScan(basePackages = { "com.geopro" })
//@EnableJpaRepositories(basePackages = { "com.geopro.repositories" })
//@EntityScan(basePackages = { "com.geopro.entities" })
//@EnableTransactionManagement(proxyTargetClass=true)//(mode=AdviceMode.ASPECTJ)//
//public class AppConfig {
//    @Bean
//    public HibernateJpaSessionFactoryBean sessionFactory(EntityManagerFactory emf) {
//         HibernateJpaSessionFactoryBean factory = new HibernateJpaSessionFactoryBean();
//         factory.setEntityManagerFactory(emf);
//         return factory;
//    }
//}
