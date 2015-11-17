package com.bxtel.bo;

import java.util.HashMap;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import com.bxtel.dao.SmsRepository;
import com.bxtel.model.Sms;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dinamica.util.DateHelper;
import dinamica.util.HttpHelper;
import dinamica.util.JsonHelper;


@Service
public class SmsBO {
	
	
	public static void main(String[] args) throws JsonProcessingException {
		HashMap param = new HashMap();
		HashMap bbb = HttpHelper.sendPost("http://127.0.0.1:9000/send",param , "GBK");
		System.out.println(JsonHelper.getObjectMapperInstance().writeValueAsString(bbb));
	}
	
//	
//	public static void main(String[] args) throws JsonProcessingException {
//		HashMap param = new HashMap();
//		param.put("username", "AYH");
//		param.put("password", "bx123456");
//		param.put("Ext", "2132");
//		//param.put("passwordMd5", "");
//		param.put("mobile", "15928122856");
//		//param.put("message", "【返利宝会员管家】440746(返利宝验证码),为了安全起见,验证短信请勿转发他人.");
//		String sms="114百信代发工资员工：这里含泪遗憾告诉大家，截至目前，号百商旅公司至今未将9月你们的工资、社保等费用打给我司，导致我司不能第一时间为你们进行工资代发、社保和公积金代购买等，影响了你们的生活，我们的心情与你们一样十分难过、伤心、愤怒，我们公司上下很多人已多次交涉并函催号百商旅公司要考虑员工利益尽快打款，但请大家原谅：我司影响力及能力有限，无法说服他们能考虑你们的权益，催款目前无果。###按照约定，你们作为商旅号百公司四川114员工，你们能否在114上班、怎么管理、怎么考核、什么岗位、发多少、什么时候发、能否发都由号百商旅公司负责，你们受他们管理，为他们产生经济效益，他们该为你们负责也必须对你们负责，我司仅为你们提供合同的代签、按号百商旅提供的工资表等号百商旅费用到账我司之后进行第一时间为你们代发工资以及社保等的代买等服务工作，我司无权利无义务无责任更无能力负责你们工资的垫付；在这里我司也只能做到下面的承诺：我们会全力替你们加大对号百商旅公司的催款力度，并安排人员做好准备待号百商旅公司钱款到账之后第一时间为你们进行工资的代发等工作；这里让我们一同期盼中国电信的下属企业会模范履行社会责任和企业责任，尽快打款。同时我们始终坚信一点：在强势不太讲理的国企面前，我们一个无权无势小民企是很弱小，但你们集体的力量那是很强大的，所以也请你们直接与号百商旅四川负责人周柯总或者号百商旅集团陈之超、魏朝晖总经理联系衔接，也可以与114品牌所有方四川省电信公司（文庙前街75号）联系，强烈反应你们的诉求并督促加快解决进度。四川百信";
//		String[] array = sms.split("###");
//		for(String str:array)
//		{
//			System.out.println(str);
//			param.put("message", "【四川百信】"+str);//返利宝会员管家
//			HashMap bbb = HttpHelper.sendPost("http://211.151.85.133:8080/sendsms.asp",param , "GBK");
//			System.out.println(JsonHelper.getObjectMapperInstance().writeValueAsString(bbb));
//		}
//	}
//	
	
	@Autowired
	SmsRepository  repository;
	
	
	@JmsListener(destination = "smsQueue")
    public void processMessage(String content) throws Exception {
		ObjectMapper om=new ObjectMapper();
		try {
			Sms sms=om.readValue(content, Sms.class);
			send(sms);
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
    }
	
	
	@Transactional
	public void send(Sms sms) throws Exception {
		try {
			
			sms.setStatus("-1");
			Sms smsnew=repository.save(sms);
			
			HashMap param = new HashMap();
			param.put("username", "AYH");
			param.put("password", "bx123456");
			param.put("Ext", "2132");
			//param.put("passwordMd5", "");
			param.put("mobile", sms.getMobile());
			param.put("message","【返利宝会员管家】"+sms.getContent());
			HashMap bbb = HttpHelper.sendPost("http://211.151.85.133:8080/sendsms.asp",param , "GBK");
			System.out.println(JsonHelper.getObjectMapperInstance().writeValueAsString(bbb));
			if(bbb.get("returncode").equals("00000000"))
			{
				String msgid=(String)bbb.get("data");
				if(new Integer(msgid)>0)
				{
//					sms.setMsgid(msgid);
//					sms.setStatus("1");
//					repository.save(sms);
					
					smsnew.setMsgid(msgid);
					smsnew.setStatus("1");
					repository.save(smsnew);
				}
				else
				{
					throw new Exception((String) errorMap.get(msgid));
				}
			}else{
				throw new Exception((String)bbb.get("returnmsg"));
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
    }
	
	public void updateStatus(String msgid,String status)
	{
		Sms sms=repository.findByMsgid(msgid);
		sms.setStatus(status);
		repository.save(sms);
	}
	
	
	
	static HashMap errorMap=new HashMap();
	static {
		errorMap.put("-1", "账号或者密码错误");
		errorMap.put("-2", "缺少企业账号");
		errorMap.put("-3", "缺少密码");
		errorMap.put("-4", "缺少短信内容");
		errorMap.put("-5", "缺少目标号码");
		errorMap.put("-7", "短信内容过长(小灵通最大56个字)");
		errorMap.put("-8", "含有非法字符，第二行返回非法关键词");
		errorMap.put("-9", "目标号码格式错误，或者包含错误的手机号码");
		errorMap.put("-10", "超过规定发送时间，禁止提交发送");
		errorMap.put("-12", "余额不足");
		errorMap.put("-14", "号码超过发送数量限制");
		errorMap.put("-15", "发送内容前面需加签名，例如【XXX公司】，签名必须放在最前面，如果内容的编码错误，出现乱码，识别不出签名【XXX公司】，也会返回-15");
		errorMap.put("-16", "提交号码数量小于最小提交量限制");
		errorMap.put("-20", "未开通接口");
		errorMap.put("-22", "短信内容签名不正确");
		errorMap.put("-99", "连接失败");
		errorMap.put("-100", "系统内部错误");
	}
}
