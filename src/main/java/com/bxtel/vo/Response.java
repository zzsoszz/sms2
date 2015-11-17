package com.bxtel.vo;

import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;


@XStreamAlias("Response")
public class Response {
	ArrayList<Report> reportlist;
	public ArrayList<Report> getReportlist() {
		return reportlist;
	}
	public void setReportlist(ArrayList<Report> reportlist) {
		this.reportlist = reportlist;
	}
	public static void main(String[] args) {
		Response resp=new Response();
		ArrayList<Report> list=new ArrayList<Report>();
		Report e=new Report();
		e.setMobile("111");
		list.add(e);
		resp.setReportlist(list);
		XStream xstream = new XStream();
		xstream.ignoreUnknownElements();
		xstream.processAnnotations(Response.class);
		xstream.autodetectAnnotations(true);
		xstream.addImplicitCollection(Response.class, "reportlist");
		System.out.println(xstream.toXML(resp));
	}
}
