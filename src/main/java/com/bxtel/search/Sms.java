//package com.bxtel.search;
//
//import javax.validation.constraints.NotNull;
//
//import org.springframework.data.annotation.Id;
//import org.springframework.data.elasticsearch.annotations.Document;
//
//
//@Document(indexName = "smsdatabase", type = "sms", shards = 1, replicas = 0, refreshInterval = "-1")
//public class Sms {
//	
//	@Id
//	private String indexid;
//	
//	@NotNull
//	private String mobile;
//	
//	@NotNull
//	private String content;
//	
//	@NotNull
//	private String msgid;
//	
//	@NotNull
//	private String status;// 0.发送成功   1.已提交到第三方平台待发送    2.发送失败   3.重发中
//	
//	public String getStatus() {
//		return status;
//	}
//	
//	public void setStatus(String status) {
//		this.status = status;
//	}
//
//	public String getMsgid() {
//		return msgid;
//	}
//
//	public void setMsgid(String msgid) {
//		this.msgid = msgid;
//	}
//
//	public String getMobile() {
//		return mobile;
//	}
//
//	public void setMobile(String mobile) {
//		this.mobile = mobile;
//	}
//
//	public String getContent() {
//		return content;
//	}
//
//	public void setContent(String content) {
//		this.content = content;
//	}
//}