package dinamica.guid;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Random;
//import java.util.UUID;
//import org.apache.commons.id.LongIdentifierGenerator;
//import org.apache.commons.id.random.SessionIdGenerator;
//import org.apache.commons.id.serial.AlphanumericGenerator;
//import org.apache.commons.id.serial.LongGenerator;

import dinamica.util.DateHelper;

public class Guid {


	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 1000000; i++) {
			System.out.println(Guid.getUniqueIdWithNaos());
		}
	}

	public static String getUniqueId() 
	{
		IdWorker iw1 = new IdWorker(1);
		try {
			return new Long(iw1.nextId()).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	//不适合分布式
	public static String getUniqueIdOld() 
	{
		double i=Math.random();//
		double i2=i*10000000;
		int rad=(int)i2;
		String timestamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()).toString();
		String guid=timestamp+rad;
		////System.out.println(guid);
		return guid;
	}
	
	/*
	 * 
	 */
	public static String getUniqueIdWithNaos() 
	{
		String timestamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()).toString();
		String guid=timestamp+DateHelper.getNanos();
		return guid;
	}
	
	
	public  static String  genRandom(int num)
	{
		StringBuffer result=new StringBuffer("");
		Random rd = new Random();
		for(int i=0;i<num;i++)
		{
			result.append(rd.nextInt(10) + "");
		}
		return result.toString();
	}
	

}
