package dinamica.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.net.*;


/**
 * Core-level framework class: String and Date basic utility methods.
 * <br><br>
 * Encapsulates utility methods for everyday programming tasks
 * with Strings, Dates and other common stuff.
 * <br>
 * Creation date: 18/09/2003<br>
 * Last Update: 18/09/2003<br>
 * (c) 2003 Martin Cordova<br>
 * This code is released under the LGPL license<br>
 * @author Martin Cordova (some code written by Carlos Pineda)
 */
public class StringUtil
{

	/**
	 * 字符串转换unicode
	 */
	public static String string2Unicode(String string) {
	 
	    StringBuffer unicode = new StringBuffer();
	 
	    for (int i = 0; i < string.length(); i++) {
	 
	        // 取出每一个字符
	        char c = string.charAt(i);
	 
	        // 转换为unicode
	        unicode.append("\\u" + Integer.toHexString(c));
	    }
	 
	    return unicode.toString();
	}

	/**
	 * unicode 转字符串
	 */
	public static String unicode2String(String unicode) {
	 
	    StringBuffer string = new StringBuffer();
	 
	    String[] hex = unicode.split("\\\\u");
	 
	    for (int i = 1; i < hex.length; i++) {
	 
	        // 转换出每一个代码点
	        int data = Integer.parseInt(hex[i], 16);
	 
	        // 追加成string
	        string.append((char) data);
	    }
	 
	    return string.toString();
	}
	
	
	/**
	 * Replace ALL occurrences of [old value] with [new value]<br>
	 * This method was written by Carlos Pineda.
	 * @param source String to manipulate
	 * @param pattern Old value
	 * @param newText New value
	 * @return String with replaced text
	 */
	public static String replace(String source, String pattern, String newText)
	{

		if (pattern == null || pattern.length() == 0 )
			return source;

		StringBuffer buf = new StringBuffer(2*source.length());

		int previndex=0;
		int index=0;
		int flen = pattern.length();
		while (true) {
			index = source.indexOf(pattern, previndex);
			if (index == -1) {
				buf.append(source.substring(previndex));
				break;
			}
			buf.append( source.substring(previndex, index)).append( newText );
			previndex = index + flen;
		}
		return buf.toString();
		
	}
	
	/**
	 * Format date using a mask and the default locale
	 * @param d Date object
	 * @param format Date mask, like yyyy-MM-dd or any valid java format
	 * @return String representing the formatted string
	 * @throws Throwable
	 */
	public static String formatDate(java.util.Date d, String format) throws Throwable
	{
		SimpleDateFormat f = new SimpleDateFormat();
		f.applyPattern(format);
		return f.format(d);
	}
	
	/**
	 * Format date using a mask and locale
	 * @param d Date object
	 * @param format Date mask, like yyyy-MM-dd or any valid java format
	 * @param loc Custom Locale
	 * @return String representing the formatted string
	 * @throws Throwable
	 */
	public static String formatDate(java.util.Date d, String format, Locale loc) throws Throwable
	{
		SimpleDateFormat f = new SimpleDateFormat(format, loc);
		return f.format(d);
	}	

	/**
	 * Create a java.util.Date object from a String value and a format mask.<br>
	 * The java date formats are supported, for more information please consult the
	 * reference guide for the class <a href="http://java.sun.com/j2se/1.4.1/docs/api/java/text/SimpleDateFormat.html">SimpleDateFormat</a>.<br>
	 * <br>
	 * Sample code:<br>
	 * <pre>
	 * java.util.Date d = StringUtil.getDateObject("2003-12-07 17:00:00","yyyy-MM-dd HH:mm:ss");
	 * </pre>  
	 * @param dateValue A String containg a valid date corresponding to dateFormat mask
	 * @param dateFormat The date format used to represent the date in dateValue
	 * @return A java.util.Date object representing the dateValue parameter
	 * @throws Throwable if dateValue is not represented in dateFormat
	 */
	public static java.util.Date getDateObject(String dateValue, String dateFormat) throws Throwable
	{
		SimpleDateFormat x = new SimpleDateFormat(dateFormat);
		x.setLenient(false);
		return x.parse(dateValue);
	}

	/**
	 * Format a number using a valid Java format mask and the default Locale
	 * @param value Double, Integer or another numeric value
	 * @param numberFormat Java numeric format mask like #,##0.00
	 * @return String representing a formatted number acording to the numberFormat
	 * @throws Throwable
	 */
	public static String formatNumber(Object value, String numberFormat) throws Throwable
	{
		DecimalFormat fmt = (DecimalFormat) NumberFormat.getInstance();
		fmt.applyPattern(numberFormat);	
		return fmt.format(value);
	}

	/**
	 * Format a number using a valid Java format mask and a custom Locale
	 * @param value Double, Integer or another numeric value
	 * @param numberFormat Java numeric format mask like #,##0.00
	 * @param loc Custom Locale to use when formatting the number
	 * @return String representing a formatted number acording to the numberFormat
	 * @throws Throwable
	 */
	public static String formatNumber(Object value, String numberFormat, Locale loc) throws Throwable
	{
		DecimalFormat fmt = (DecimalFormat) NumberFormat.getInstance(loc);
		fmt.applyPattern(numberFormat);	
		return fmt.format(value);
	}

	/**
	 * Create an array of items from a string with delimiters to separate the items.
	 * This is a very simple wrapper around the native String.split method
	 * @param s String to split or separate in its parts
	 * @param separator Delimiter string, like a pipe or a tabulator
	 * @return Array of strings containing the separated items
	 */
	public static String[] split(String s, String separator) 
	{
		separator = "\\" + separator;
		return s.split(separator);
	}

	/**
	 * Loads a text resource stored into the Web Application context paths
	 * @param path Path to the resource
	 * @return String containing the resource contents
	 * @throws Exception
	 */
	public static String getResource(javax.servlet.ServletContext ctx, String path) throws Throwable
	{
		return getResource(ctx, path, System.getProperty("file.encoding", "ISO8859_1"));
	}

	/**
	 * Append message to file, this method is usually 
	 * used to save log messages
	 * @param path File name
	 * @param message String to append to file
	 */
	public static synchronized void saveMessage(String path, String message) 
	{

		FileOutputStream fos = null;
		PrintWriter pw = null;

		try {
			fos = new FileOutputStream(new File(path), true);
			pw = new PrintWriter(fos, false);
			pw.println(message);
		}
		catch (IOException e) {
			
			try
			{
				String d = StringUtil.formatDate(new java.util.Date(), "yyyy-MM-dd HH:mm:ss");
				System.err.println("ERROR [dinamica.StringUtil.saveMessage@" + d + "]: " + e.getMessage());
			}
			catch (Throwable e1)
			{
			}
		}
		finally {
			
			try {
				if (pw != null)
					pw.close();
				if (fos != null)
					fos.close();
			}
			catch (IOException e) {
			}
			
		}
	}

	/**
	 * Retrieve a text-based document using HTTP GET method.<br>
	 * May be used to retrieve XML documents, news feeds, etc.
	 * @param url A valid URL
	 * @param logStdout if TRUE then this method will print
	 * a tracelog via STDOUT
	 * @return a String containing the whole document
	 * @throws Throwable
	 */
	public static String httpGet(String url, boolean logStdout) throws Throwable
	{

		final int bufferSize = 4096;
		BufferedReader br = null;
		HttpURLConnection urlc = null;
		StringBuffer buffer = new StringBuffer();
		URL page = new URL(url); 
    		
		try
		{
			
			if (logStdout)
				System.err.println("Waiting for reply...:" + url);
			
			urlc = (HttpURLConnection)page.openConnection();          
			urlc.setUseCaches(false);
			
			if (logStdout)
			{
				System.err.println("Content-type = " + urlc.getContentType()); 
				System.err.println("Content-length = " + urlc.getContentLength()); 
				System.err.println("Response-code = " + urlc.getResponseCode());
				System.err.println("Response-message = " + urlc.getResponseMessage());
			}
			
			int retCode = urlc.getResponseCode();
			String retMsg = urlc.getResponseMessage();
			if (retCode>=400)
				throw new Throwable("HTTP Error: " + retCode + " - " + retMsg + " - URL:" + url);
																								   
			br = new BufferedReader(new InputStreamReader(urlc.getInputStream()), bufferSize);
			char buf[] = new char[bufferSize];
			int bytesRead = 0;
			
			while (bytesRead!=-1) 
			{
				bytesRead = br.read(buf);
				if (bytesRead>0)
					buffer.append(buf,0,bytesRead);
			}
			
			if (logStdout)
			{
				System.err.println("Document received.");
			}
			
			return buffer.toString();
		}
		catch (Throwable e)
		{
			throw e;
		}
		finally
		{
			if (br != null)
				br.close();
			
			if (urlc!=null)
				urlc.disconnect();				
		}

	}

	/**
	 * Loads a text resource stored into the Web Application context paths
	 * <br>
	 * PATCH 2005-02-17 (v2.0.3) - encoding support
	 * @param ctx Servlet context 
	 * @param path Path to the resource
	 * @param encoding Canonical name of the encoding to be used to read the resource
	 * @return String containing the resource contents
	 * @throws Exception
	 */
	public static String getResource(javax.servlet.ServletContext ctx, String path, String encoding) throws Throwable
	{
		
		StringBuffer buf = new StringBuffer(5000);
		byte[] data = new byte[5000];

		InputStream in = null;
		
		in = ctx.getResourceAsStream(path);
        
		try
		{
			if (in!=null)
			{
				while (true)
				{
					int len = in.read(data);
					if (len!=-1)
					{
						buf.append( new String(data, 0, len, encoding) );
					}
					else
					{
						break;
					}
				}

				return buf.toString();

			}
			else
			{
				throw new Throwable("Invalid path to resource: " + path);
			}
            
		}
		catch (Throwable e)
		{
			throw e;
		}
		finally
		{
			if (in!=null)
			{
				try{
					in.close();
				} catch (Exception e){}
			}
		}
        
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	/**
	 * 检测字符串是否为空
	 * 
	 * @param str
	 *            需要检测的字符组
	 * @return true/false
	 */
	public final static boolean isEmptyOrWhitespace(String str) {
		if (str == null || str.trim().length() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * MD5加密
	 * 
	 * @param 传入需要加密的字符串
	 * @return
	 */
	public final static String md5(String str) {
		if (str == null || str.length() == 0) {
			return "";
		} else {
			StringBuffer sb = new StringBuffer();
			try {
				MessageDigest algorithm = MessageDigest.getInstance("MD5");
				algorithm.reset();
				algorithm.update(str.getBytes());
			
				byte[] md5 = algorithm.digest();
				String singleByteHex = "";
				for (int i = 0; i < md5.length; i++) {
					singleByteHex = Integer.toHexString(0xFF & md5[i]);
					if (singleByteHex.length() == 1) {
						sb.append("0");
					}
					sb.append(singleByteHex.toUpperCase());
				}
			} catch (NoSuchAlgorithmException ex) {
				ex.printStackTrace();
			}
			return sb.toString();
		}
	}
	
	/**
	 * 过滤注入字符
	 * 
	 * @param str
	 *            需要过滤的字符串
	 * @return
	 */
	public final static String inSql(String str) {
		return str.replaceAll(".*([';]+|(--)+).*", "");
	}

	/**
	 * 字符串转换到时间格式
	 * @param dateValue 需要转换的字符串
	 * @param strFormat 需要格式的目标字符串  举例 yyyy-MM-dd
	 * @return 返回转换后的时间
	 */
	public static Date parseDate(String dateValue, String strFormat) {

		if (dateValue == null)
			return null;

		if (strFormat == null)
			strFormat = "yyyy-MM-dd";

		SimpleDateFormat dateFormat = new SimpleDateFormat(strFormat);
		Date newDate = null;

		try {
			newDate = dateFormat.parse(dateValue);
		} catch (ParseException pe) {
			newDate = null;
		}

		return newDate;
	}
	
	public static Date strToDate(String date)
	{
		if (date == null)
		{
			return null;
		}
		Date realDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date newDate = sdf.parse(date);
			String str = sdf.format(newDate);
			realDate = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return realDate;
	}
	
	public static String getDateStr(String format)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date1 = new Date();
		String date = sdf.format(date1);
		return date;
	}
	
	
	
	
	
	
	public static String warpDataForValue(String field)
	{
		if(field==null)
		{
			return " null ";
		}
		return field.toString();
	}
	
	
	public static String warpData(String field)
	{
		if(field==null)
		{
			return " null ";
		}
		return field.toString();
	}
	
	public static String warpData(Float field)
	{
		if(field==null)
		{
			return " null ";
		}
		return field.toString();
	}
	
	public static String warpData(Double field)
	{
		if(field==null)
		{
			return " null ";
		}
		return field.toString();
	}
	
	public static String warpData(Integer field)
	{
		if(field==null)
		{
			return " null ";
		}
		return field.toString();
	}
	
	public static String warpData(Date field)
	{
		if(field==null)
		{
			return " null ";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date1 = new Date();
		String date = sdf.format(date1);
		return " to_date('"+date+"','yyyy-MM-dd HH:mm:ss') ";
	}
	
	
	public static String nullToEmpty(Object field)
	{
		return field==null?"":field.toString();
	}
	
	public static void warpDataForWhere(StringBuffer sql,String fieldname,String field)
	{
		if(field!=null)
		{
			sql.append("and "+fieldname+"='"+field+"'");
		}
	}
	
	public static void warpDataForQuery(StringBuffer sql,String fieldname,Float field)
	{
		if(field!=null)
		{
			sql.append("and "+fieldname+"="+field);
		}
	}
	public static void warpDataForQuery(StringBuffer sql,String fieldname,Long field)
	{
		if(field!=null)
		{
			sql.append("and "+fieldname+"="+field);
		}
	}
	public static void warpDataForQuery(StringBuffer sql,String fieldname,Double field)
	{
		if(field!=null)
		{
			sql.append("and "+fieldname+"="+field);
		}
	}
	public static void warpDataForQuery(StringBuffer sql,String fieldname,Integer field)
	{
		if(field!=null)
		{
			sql.append("and "+fieldname+"="+field);
		}
	}
	
	public static void warpDataForQuery(StringBuffer sql,String fieldname,Date field)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date1 = new Date();
		String date = sdf.format(date1);
		if(field!=null)
		{
			sql.append("and "+fieldname+"="+" to_date('"+date+"','yyyy-MM-dd HH:mm:ss') ");
		}
	}
	
	
	/*
	public static String warpData(Float field)
	{
		return field.toString();
	}
	
	public static String warpData(Long field)
	{
		return field.toString();
	}
	
	public static String warpData(Double field)
	{
		return field.toString();
	}
	
	public static String warpData(Integer field)
	{
		return field.toString();
	}
	
	public static String warpData(Date field)
	{
		if(field==null)
		{
			return " null ";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date1 = new Date();
		String date = sdf.format(date1);
		return " to_date('"+field+"','yyyy-MM-dd HH:mm:ss') ";
	}
	*/
	
	
	
	public static String cutString(String s,int len)
	{
		String t = s.length() > len ? s.substring(1,len-1) : s;
        return t;
	}
	
	/**
	 * 日期格式化
	 * @param strMat 格式化后的格式 yyyy-MM-dd HH:mm:ss 或者yyyy-MM-dd
	 * @param strDate 需要格式化的日期
	 * @return
	 */
	public static Date getFormatDate(String strMat,String strDate){
		if("yyyy-MM-dd HH:mm:ss".equalsIgnoreCase(strMat) && strDate.length() == 10){
			strDate = strDate+" 00:00:00";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(strMat);
		try {
			return sdf.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 日期格式化 默认格式化为 yyyy-MM-dd HH:mm:ss
	 * @param strDate 需要格式化的日期
	 * @return
	 */
	public static Date getFormatDate(String strDate){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 日期格式化为字符串
	 * @param strMat 格式化后的字符串格式
	 * @param strDate 日期
	 * @return
	 */
	public static String getFormatDate(String strMat,Date strDate){
		if(strDate == null){
			return null;
		}else{
			SimpleDateFormat sdf = new SimpleDateFormat(strMat);
			return sdf.format(strDate);
		}
	}
	/**
	 * 直接获取"yyyy-MM-dd HH:mm:ss"的时间
	 * @return
	 */
	public static String getDateStr(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date1 = new Date();
		String date = sdf.format(date1);
		return date;
	}
	 /**
     * 日期加减多少天
     * @param strDate 日期
     * @param d 加减的天数 如 1或-1
     * @return
     */
    public static String dateCpp(String strDate,int d){
    	Calendar cal3 = Calendar.getInstance(); 
    	cal3.setTime(getFormatDate("yyyy-MM-dd",strDate));
    	cal3.add(cal3.DATE, d); 
    	return  getFormatDate("yyyy-MM-dd",cal3.getTime()); 
    }
    
    
    public static String truncate(String str,int len)
    {
    	if(str!=null)
    	{
    		if(str.length()>len)
    		{
    			return str.substring(0, len);
    		}
    	}
		return str;
    }
    
    public static String upperCaseFirst(String str)
    {
    	if(str==null && "".equals(str.trim()))
    	{
    		return str;
    	}
    	char c = str.charAt(0);// 大写第一个字母
    	return String.valueOf(c).toUpperCase().concat(str.substring(1));
    }
    
    public static String lowerCaseFirst(String str)
    {
    	if(str==null && "".equals(str.trim()))
    	{
    		return str;
    	}
    	char c = str.charAt(0);// 大写第一个字母
    	return String.valueOf(c).toLowerCase().concat(str.substring(1));
    }
    
    
    public static String removeLastWith(String str,String endstr)
    {
    	if(str.toLowerCase().endsWith(endstr.toLowerCase()))
    	{
    		return str.substring(0,str.toLowerCase().lastIndexOf(endstr.toLowerCase()));
    	}
		return str;
    }
    
    /*
	 *  String pattern = "{:name} - {:age} - {:name}";
    	Map<String,Object> arguments = new HashMap<String, Object>(){{
        put("name", "tom");
        put("age", 23);
        String msg = MyMessageFormat.format(pattern, arguments);
    	//System.out.println(msg);
    }};
	 */
    
    public static String format(String pattern, Map<String,Object> arguments){
        String formatedStr = pattern;
        for (String key : arguments.keySet()) {
            formatedStr = formatedStr.replaceAll("\\{:"+key+"\\}", arguments.get(key).toString());
        }
        return formatedStr;
    }
    
    
    public static String format2(String pattern, Map<String,String> arguments){
        String formatedStr = pattern;
        for (String key : arguments.keySet()) {
            formatedStr = formatedStr.replaceAll("\\{"+key+"\\}", arguments.get(key).toString());
        }
        return formatedStr;
    }
    
	/**
	 * 将字符串转成unicode
	 * 
	 * @param str
	 *            待转字符串
	 * @return unicode字符串
	 */
	public static String stringToUnicode(String str) {
		str = (str == null ? "" : str);
		String tmp;
		StringBuffer sb = new StringBuffer(1000);
		char c;
		int i, j;
		sb.setLength(0);
		for (i = 0; i < str.length(); i++) {
			c = str.charAt(i);
			sb.append("\\u");
			j = (c >>> 8); // 取出高8位
			tmp = Integer.toHexString(j);
			if (tmp.length() == 1)
				sb.append("0");
			sb.append(tmp);
			j = (c & 0xFF); // 取出低8位
			tmp = Integer.toHexString(j);
			if (tmp.length() == 1)
				sb.append("0");
			sb.append(tmp);
		}
		return (new String(sb));
	}

	/**
	 * 将unicode 字符串
	 * 
	 * @param str
	 *            待转字符串
	 * @return 普通字符串
	 */
	public static String unicodeToString(String str) {
		str = (str == null ? "" : str);
		if (str.indexOf("\\u") == -1)// 如果不是unicode码则原样返回
			return str;
		StringBuffer sb = new StringBuffer(1000);
		for (int i = 0; i < str.length() - 6;) {
			String strTemp = str.substring(i, i + 6);
			String value = strTemp.substring(2);
			int c = 0;
			for (int j = 0; j < value.length(); j++) {
				char tempChar = value.charAt(j);
				int t = 0;
				switch (tempChar) {
				case 'a':
					t = 10;
					break;
				case 'b':
					t = 11;
					break;
				case 'c':
					t = 12;
					break;
				case 'd':
					t = 13;
					break;
				case 'e':
					t = 14;
					break;
				case 'f':
					t = 15;
					break;
				default:
					t = tempChar - 48;
					break;
				}

				c += t * ((int) Math.pow(16, (value.length() - j - 1)));
			}
			sb.append((char) c);
			i = i + 6;
		}
		return sb.toString();
	}
	
	

    
	
	//支持全角空格
	public static boolean isBlank(CharSequence str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	public static boolean isWhitespace(char ch) {
		if (ch == '\u00a0') {
			return true;
		}
		if (Character.isWhitespace(ch)) {
			return true;
		}
		return false;
	}

	//取得最后几位
	public static  String getLast(String str,int  count)
	{
		if(str==null)
		{
			return null;
		}
		System.out.println(str.length());
		if(str.length()>count)
		{
			System.out.println(str.length()-count-1);
			return str.substring(str.length()-count,str.length());
		}
		return str;
	}
	
	
	
	public static  String[] splitByLastSeparator(String s,String separator)
	{
		int i = s.lastIndexOf(separator);
		String[] a =  {s.substring(0, i), s.substring(i+1)};
		return  a;
	}
	
	
	public static void main(String args[])
	{
		
		
		//System.out.println(StringUtil.getLast("60222222222211111234",4));
//    	//System.out.println(StringUtil.removeLastWith("aaaa,b","b"));
//    	//System.out.println(truncate("国内发行银联卡 万事达（Master） 威士（VISA） 运通（AMEX） 大来（Diners Club） JCB卡",3));
//    	System.out.print(upperCaseFirst("aaaC"));
//		try {
//			//System.out.println(StringUtil.formatDate(new Date(),"yyyy-mm-dd hh:mm:ss"));
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
		
		//System.out.print(StringUtil.stringToUnicode("璧山县 ").replaceAll("[\\x00a0]",""));
		//System.out.println("璧山县 ".replaceAll("[\\u00a0]",""));
		//System.out.println(StringUtil.isBlank(" "));
		
		
//		String str[]=StringUtil.splitByLastSeparator("refund_id_01", "_");
//		for(int i=0;i<str.length;i++)
//		{
//			System.out.println(str[i]);
//		}
//		
		
		
		String test = "最代码网站地址:www.zuidaima.com";
		 
	    String unicode = string2Unicode(test);
	     
	    String string = unicode2String(unicode) ;
	     
	    System.out.println(unicode);
	     
	    System.out.println(string);
	    
	}
//java过滤特殊字符串 
// http://p-x1984.iteye.com/blog/418132	
//	\\ 反斜杠 
//	\t 间隔 \u0009
//	\n 换行 \u000A
//	\r 回车 \u000D
//	\d 数字 等价于[0-9] 
//	\D 非数字 等价于[^0-9] 
//	\s 空白符号 [\t\n\x0B\f\r] 
//	\S 非空白符号 [^\t\n\x0B\f\r] 
//	\w 单独字符 [a-zA-Z_0-9] 
//	\W 非单独字符 [^a-zA-Z_0-9] 
//	\f 换页符 
//	\e Escape 
//	\b 一个单词的边界 
//	\B 一个非单词的边界 
//	\G 前一个匹配的结束 

}
