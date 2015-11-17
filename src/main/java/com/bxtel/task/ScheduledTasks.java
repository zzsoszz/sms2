package com.bxtel.task;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bxtel.bo.SmsBO;
import com.bxtel.dao.SmsRepository;
import com.bxtel.model.Sms;
//import com.bxtel.search.SmsSearchRepository;

/*
 * Spring Boot:在Spring Boot中使用定时任务
 * http://www.tuicool.com/articles/ayau2i
 */

@Component
public class ScheduledTasks {
	
	@Autowired
	SmsRepository  repository;
//	@Autowired
//	SmsSearchRepository  searchrepository;
//	
	
	
	@Autowired
	SmsBO smsbo;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
//    @Scheduled(fixedRate = 5000)
//    public void reportCurrentTime() {
//        System.out.println("The time is now " + dateFormat.format(new Date()));
//    }
    
    
    @Scheduled(fixedRate = 5000)
    public void resend() throws Exception {
    	 List<Sms> smslist = repository.findALLBySatus("3");
    	 for(Sms sms:smslist)
    	 {
    		 smsbo.send(sms);
    	 }
    }
    
//  static int index;
//  @Scheduled(fixedRate = 5000)
//  public void resend() throws Exception {
//  	 com.bxtel.search.Sms sms=new com.bxtel.search.Sms();
//  	 sms.setContent("hello："+index++);
//	 searchrepository.save(sms);
//  }
  
}