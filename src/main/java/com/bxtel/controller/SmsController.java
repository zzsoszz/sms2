

package com.bxtel.controller;

import javax.jms.JMSException;

import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bxtel.bo.SmsBO;
import com.bxtel.model.Sms;
import com.bxtel.vo.Report;
import com.bxtel.vo.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.thoughtworks.xstream.XStream;

import dinamica.util.DateHelper;
import dinamica.util.JsonHelper;


/*
 * 
 * http://119.254.84.182:8080/sms2/send
 * 
 * 
 * 
 *  SpringMVC4零配置
 *  http://hanqunfeng.iteye.com/blog/2113820
	spring4中提供了大量的注解来支持零配置，简要说明如下：
	@Configuration ： 类似于spring配置文件，负责注册bean，对应的提供了@Bean注解。需要org.springframework.web.context.support.AnnotationConfigWebApplicationContext注册到容器中。
	@ComponentScan ： 注解类查找规则定义 <context:component-scan/>
	@EnableAspectJAutoProxy ： 激活Aspect自动代理 <aop:aspectj-autoproxy/>
	@Import @ImportResource: 关联其它spring配置  <import resource="" />
	@EnableCaching ：启用缓存注解  <cache:annotation-driven/>
	@EnableTransactionManagement ： 启用注解式事务管理 <tx:annotation-driven />
	@EnableWebMvcSecurity ： 启用springSecurity安全验证
	
	
	Spring学习笔记（十一）事务管理
	http://my.oschina.net/u/1413049/blog/205094
	org.springframework.orm.jpa.JpaTransactionManager  JPA事务管理类
	org.springframework.orm.hibernate3.HibernateTransactionManager Hibernate3.0事务管理类
	org.springframework.orm.jdbc.datasource.DataSourceTransactionManager 使用JDBC、MyBatis等基于数据源的持久化技术的事务管理类
	org.springframework.orm.jdo.JdoTransactionManager 使用JDO进行持久化时，使用该事务管理器
	org.springframework.transaction.jta.JtaTransactionManager 需要跨数据源时使用该事务管理类
	
	全面分析 Spring 的编程式事务管理及声明式事务管理
	http://www.ibm.com/developerworks/cn/education/opensource/os-cn-spring-trans/
	
	
	【spring-boot】spring aop 面向切面编程初接触
	http://www.cnblogs.com/lic309/p/4079194.html
	
	How does Spring @Transactional Really Work?
	http://www.codingpedia.org/jhadesdev/how-does-spring-transactional-really-work/
	
	log4jdbc
	http://qiita.com/ksby/items/7a2cb97215b252bf41b1
	https://github.com/making/spring-boot-blank
 */


@RestController
public class SmsController {
	@Autowired
	SmsBO smsbo;
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	
	@RequestMapping("/send")
	@ResponseBody
	public String send() throws JsonProcessingException
	{
//		try {
//			Thread.sleep(100000000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		//{"data":"272486570","returnmsg":"","returncode":"00000000","url":"http://211.151.85.133:8080/sendsms.asp"}
//		<?xml version="1.0" encoding="UTF-8" ?>
//		<Response>
//		  <Report>
//		    <MsgID>272486570</MsgID>
//		    <Mobile>13730666347</Mobile>
//		    <Status>DELIVRD</Status>
//		  </Report>
//		  <Report>
//		    <MsgID>272486570</MsgID>
//		    <Mobile>13730666347</Mobile>
//		    <Status>DELIVRD</Status>
//		  </Report>
//		</Response>
//		
		Sms sms=new Sms();
		sms.setMobile("13730666347");
		sms.setContent("【返利宝会员管家】验证码123456   发送时间："+DateHelper.getTimeString());
		String content=JsonHelper.getObjectMapperInstance().writeValueAsString(sms);
		jmsTemplate.send("smsQueue", new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                TextMessage message = session.createTextMessage(content);
                return message;
            }
        });
		return "success";
	}
	
	
	@RequestMapping("/report")
	@ResponseBody
	public String report(@RequestParam("xml")String xml) throws JsonProcessingException
	{
		try{
			
			XStream xstream = new XStream();
			xstream.ignoreUnknownElements();
			xstream.processAnnotations(Response.class);
			xstream.autodetectAnnotations(true);
			xstream.addImplicitCollection(Response.class, "reportlist");
			Response resp=(Response)xstream.fromXML(xml);
			for(Report one:resp.getReportlist())
			{
				smsbo.updateStatus(one.getMsgID(), one.getStatus().equals("DELIVRD")?"0":"2");
			}
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println(xml);
		}
		return "0";
	}
	
}
