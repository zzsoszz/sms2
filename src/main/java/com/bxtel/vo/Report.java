package com.bxtel.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Report")
public class Report {
	String MsgID;
	String Mobile;
	String Status;
	public String getMsgID() {
		return MsgID;
	}
	public void setMsgID(String msgID) {
		MsgID = msgID;
	}
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
}
