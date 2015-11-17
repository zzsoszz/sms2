package dinamica.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;


import java.text.SimpleDateFormat;   
import java.util.ArrayList;   
import java.util.Calendar;
import java.util.Date;   
import java.util.GregorianCalendar;
import java.util.List;   
import java.util.UUID;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class DateHelper {   

	public static void main(String args[]) throws ParseException
    {
		dinamica.util.DateHelper.getTimeString();
//    	
//    	String insertSql="insert jiankongcollect (nowtime,collectdata) values('"+DateHelper.getDateString("yyyy-MM-dd HH:mm:ss")+"','"+"ddddd"+"')";
//    	//System.out.println(insertSql);
   // 	//System.out.println(getDateString("yyyy-MM-dd HH:mm:ss"));
   // 	;
//    	String nString=addDay("2014-05-06",0);
//    	Date aa=addHour(convertStringToDate("01:10","HH:mm"),3);
//    	System.out.println(aa);
//    	
//    	for (int i=0;i<100000;i++)
//    	{
//    		System.out.println(getDateStringWithNanos("yyyy-mm-dd"));
//    	}
    	//System.out.println(nString);
    	
    	//System.out.println(getWeekOfDate2(DateHelper.addDay(DateHelper.getDate(),10)));
    	
    	System.out.println(DateHelper.getYear());
    }
	
	//http://zh.wikipedia.org/wiki/%E8%94%A1%E5%8B%92%E5%85%AC%E5%BC%8F
	public void getWeek(Date date)
	{
		//w=y+[y/4]+[c/4]-2c+[26(m+1)/10]+d-1;
	}
	
	public static Date getDate() 
    {
		try {
			return getDate("yyyy-MM-dd");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
    }
	//EEE, dd MMM yyyy HH:mm:ss zzz
	public static Date getYesterday() throws ParseException
    {
		return addDay(getDate("yyyy-MM-dd"),-1);
    }
	
	public static Date getTime() 
    {
		try {
			return getDate("yyyy-MM-dd HH:mm:ss");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
    }
	
	
	public static String getTimeString() 
    {
		return getDateString("yyyy-MM-dd HH:mm:ss");
    }
	public static String getTime3String() 
    {
		return getDateString("yyyy-MM-dd HH:mm:ss.SSS");
    }
	
	public static Timestamp getTimestamp() throws ParseException
    {
		Timestamp ts = new Timestamp(getDate("yyyy-MM-dd HH:mm:ss").getTime());
		return ts;
    }
	
	public static Timestamp convertStringToTimestamp(String str) throws Exception
	{
		//"2011-05-09 11:49:45";
		Timestamp ts= new Timestamp(System.currentTimeMillis());
		try{
			ts = Timestamp.valueOf(str);
		}catch(Exception ex)
		{
			throw new Exception(ex.getMessage()+str);
		}
		return ts;
	}
	
	

	public static String convertTimestampToString(Timestamp str)
	{
		return convertTimestampToString(str,"yyyy-MM-dd HH:mm:ss");
	}
	
	public  static String convertTimestampToString(Timestamp str,String format)
	{
		//Timestamp ts = new Timestamp(System.currentTimeMillis());
		String tsStr = "";
		DateFormat sdf = new SimpleDateFormat(format);
		tsStr = sdf.format(str);
		//System.out.println(tsStr);
		return tsStr;
	}
	
	public  static Date convertTimestampToDate(Timestamp timestamp)
	{
		Date date = timestamp;
		return date;
	}
	
	public  static Timestamp convertDateToTimestamp(Date date)
	{
		Timestamp ts = new Timestamp(date.getTime());
		return ts;
	}
	
	
	public static Date getDate(String format) throws ParseException
    {
        SimpleDateFormat df = new SimpleDateFormat(format);   
        Date date =new Date(System.currentTimeMillis());
        return convertStringToDate(df.format(date),format);
    }
	
	
	
	public static String getDateString()
    {
        return getDateString("yyyy-MM-dd");
    }
	public static String getYear()
    {
        return getDateString("yyyy");
    }
	public static String getDateString(int after)
    {
        return addDay(getDateString("yyyy-MM-dd"),after);
    }
	
	
    public static String getDateString(String format)
    {
        SimpleDateFormat df = new SimpleDateFormat(format);   
        Date date =new Date(System.currentTimeMillis());
        String dateStr = df.format(date);   
        return dateStr;
    }
    
    
    public static String getDateStringWithNanos(String format)
    {
        SimpleDateFormat df = new SimpleDateFormat(format);   
        Date date =new Date(System.currentTimeMillis());
        //String dateStr = df.format(date);
        NumberFormat nf = new DecimalFormat("000000000");
        String dateStr = df.format(date.getTime()) +"."+ nf.format(System.nanoTime());
        return dateStr;
    }
    
//    
//    暂时不用
//    public static String getDateStringWithNanos(String dateformat)
//    {
//    	final long timeUS = HiresTimer.currentTimeUS();
//        return HiresTimer.toString(dateformat,timeUS);
//    }
    
    
    public static String getNanos()
    {
        NumberFormat nf = new DecimalFormat("000000000");
        String dateStr =  nf.format(System.nanoTime());
        return dateStr;
    }
    
   
    
    
  /**  
    * 字符串转日期类型  
    *   
    * @author HeCheng  
    * @time 2010-12-08 18:31:46  
    * @return  
 * @throws ParseException 
    */  
   public static Date convertStringToDate(String time, String format) throws ParseException {   
	   if (format == null) {   
           format = "yyyy-MM-dd";   
       }
       SimpleDateFormat sdf = new SimpleDateFormat(format);   
       return sdf.parse(time);   
   }
   public static String convertDateToString(Date date, String format) {   
       if (format == null) {   
           format = "yyyy-MM-dd";   
       }
       SimpleDateFormat sdf = new SimpleDateFormat(format);   
       try {   
           return sdf.format(date);  
       } catch (Exception e) {   
           return null;   
       }
   } 
   
   /**  
    * 取近一年  
    *   
    * @author HeCheng  
    * @time 2010-12-08 21:27:41  
    * @return  
    */  
   public static String getLastOneYearDay() {   
       return getLastNumberDayBeforeYesterDay(365);   
   }   
      
   /**  
    * 取近三个月  
    *   
    * @author HeCheng  
    * @time 2010-12-08 21:27:41  
    * @return  
    */  
   public static String getLastThreeMonthDay() {   
       return getLastNumberDayBeforeYesterDay(90);   
   }   
      
   /**  
    * 取近一个月  
    *   
    * @author HeCheng  
    * @time 2010-12-08 21:27:41  
    * @return  
    */  
   public static String getLastMonthDay() {   
       return getLastNumberDayBeforeYesterDay(30);   
   }   
      
   /**  
    * 取近一周  
    *   
    * @author HeCheng  
    * @time 2010-12-08 21:27:41  
    * @return  
    */  
   public static String getLastWeekDay() {   
       return getLastNumberDayBeforeYesterDay(7);   
   }   
 
   /**  
    * 取昨天的前XX天  
    *   
    * @author HeCheng  
    * @time 2010-12-08 21:06:08  
    * @param number  
    * @return  
    */  
   public static String getLastNumberDayBeforeYesterDay(int number) {   
       String yesterDay = getYesterdayOrTomorrow(getNowDate(), -1);   
       return getLastNumberDay(yesterDay, number);   
   }   
 
   /**  
    * 取之前XX天  
    *   
    * @author HeCheng  
    * @time 2010-12-08 21:06:08  
    */  
   @SuppressWarnings("deprecation")   
   public static String getLastNumberDay(String day, int number) {   
       String ntime = "";   
       try {   
           SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");   
           Date startDate = df.parse(day);   
           Date endDate = null;   
           endDate = new Date(startDate.getTime()   
                    - (((long) number * (long) 24 * (long) 3600 * (long) 1000)));   
            ntime = endDate.getYear() + 1900 + "-" + (endDate.getMonth() + 1)   
                    + "-" + endDate.getDate();   
        } catch (Exception e) {   
            //System.out.println(e);   
        }   
        return ntime;   
    }   
  
    /**  
     * 取上个月的昨天  
     *   
     * @author HeCheng  
     * @time 2010-12-08 18:41:14  
     * @param yesterday  
     * @return  
     */  
    @SuppressWarnings("deprecation")   
    public static String getYesterdayOnLastMonth(String yesterday) {   
        String ntime = "";   
        try {   
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");   
            Date startDate = df.parse(yesterday);   
            Date endDate = null;   
            endDate = new Date(startDate.getTime()   
                    - (((long) 30 * (long) 24 * (long) 3600 * (long) 1000)));   
            ntime = endDate.getYear() + 1900 + "-" + (endDate.getMonth() + 1)   
                    + "-" + endDate.getDate();   
        } catch (Exception e) {   
            //System.out.println(e);   
        }   
        return ntime;   
    }   
  
    /**  
     * 取今年第一天  
     *   
     * @author HeCheng  
     * @time 2010-12-08 18:44:12  
     * @param nowDate  
     * @return  
     */  
    @SuppressWarnings("deprecation")   
    public static String getFirstDayInYear(String nowDate) {   
        String ntime = "";   
        try {   
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");   
            Date startDate = df.parse(nowDate);   
            ntime = startDate.getYear() + 1900 + "-01-01";   
        } catch (Exception e) {   
            //System.out.println(e);   
        }   
        return ntime;   
    }   
  
    /**  
     * 取本季度第一天  
     *   
     * @author HeCheng  
     * @time 2010-12-08 18:46:33  
     * @param nowDate  
     * @return  
     */  
    @SuppressWarnings("deprecation")   
    public static String getFirstDayInThisQuarter(String nowDate) {   
        String ntime = "";   
        int nowMonth;   
        int nowYear;   
        try {   
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");   
            Date startDate = df.parse(nowDate);   
            nowMonth = startDate.getMonth() + 1;   
            nowYear = startDate.getYear() + 1900;   
            while (true) {   
                if (nowMonth >= 10) {   
                    ntime = nowYear + "-10-01";   
                    break;   
                }   
                if (nowMonth >= 7) {   
                    ntime = nowYear + "-07-01";   
                    break;   
                }   
                if (nowMonth >= 4) {   
                    ntime = nowYear + "-04-01";   
                    break;   
                }   
                if (nowMonth >= 1) {   
                    ntime = nowYear + "-01-01";   
                    break;   
                }   
            }   
        } catch (Exception e) {   
            //System.out.println(e);   
        }   
        return ntime;   
    }   
  
    /**  
     * 取昨天  
     *   
     * @author HeCheng  
     * @time 2010-12-08 20:27:21  
     * @return  
     */  
    public static String getYesterDay() {   
        return getYesterdayOrTomorrow(getNowDate(), -1);   
    }   
  
    /**  
     * 取明天  
     *   
     * @author HeCheng  
     * @time 2010-12-08 20:27:21  
     * @return  
     */  
    public static String getTomorrow() {   
        return getYesterdayOrTomorrow(getNowDate(), 1);   
    }   
  
    /**  
     * 取今天  
     *   
     * @return  
     */  
    @SuppressWarnings("deprecation")   
    public static String getNowDate() {   
        Date date = new Date();   
        int nowMonth = date.getMonth() + 1;   
        int nowYear = date.getYear() + 1900;   
        int day = date.getDate();   
        String startTime = nowYear + "-" + nowMonth + "-" + day;   
        return startTime;   
    }   
  
    /**  
     * 取时间段  
     *   
     * @author HeCheng  
     * @time 2010-12-08 18:34:22  
     * @param startTime  
     * @param endTime  
     * @return  
     */  
    @SuppressWarnings("deprecation")   
    public static List<String> getTimes(String startTime, String endTime) {   
        List<String> dayList = new ArrayList<String>();   
        try {   
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");   
            Date startDate = df.parse(startTime);   
            Date endDate = df.parse(endTime);   
            String now = "";   
            for (long i = startDate.getTime(); i <= endDate.getTime(); i += (long) 24  
                    * (long) 3600 * (long) 1000) {   
                Date date = new Date(i);   
                now = date.getYear() + 1900 + "-" + (date.getMonth() + 1) + "-"  
                        + date.getDate();   
                dayList.add(now);   
            }   
        } catch (Exception e) {   
            //System.out.println(e);   
        }   
        return dayList;   
    }   
  
    /**  
     * 取当前月第一天  
     *   
     * @author HeCheng  
     * @time 2010-12-08 18:34:29  
     *   
     * @return  
     */  
    @SuppressWarnings("deprecation")   
    public static String getMonthFirstDay() {   
        Date date = new Date();   
        int nowMonth = date.getMonth() + 1;   
        int nowYear = date.getYear() + 1900;   
        String startTime = nowYear + "-" + nowMonth + "-1";   
        return startTime;   
    }   
  
    /**  
     * 取明天或昨天  
     *   
     * @param nowDate  
     * @param con  
     * @return  
     */  
    @SuppressWarnings("deprecation")   
    public static String getYesterdayOrTomorrow(String nowDate, int con) {   
        String ntime = "";   
        try {   
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");   
            Date startDate = df.parse(nowDate);   
            Date endDate = null;   
            if (con == -1) {   
                endDate = new Date(startDate.getTime() - (long) 24  
                        * (long) 3600 * (long) 1000);   
            } else {   
                endDate = new Date(startDate.getTime() + (long) 24  
                        * (long) 3600 * (long) 1000);   
            }   
            ntime = endDate.getYear() + 1900 + "-" + (endDate.getMonth() + 1)   
                    + "-" + endDate.getDate();   
        } catch (Exception e) {   
            //System.out.println(e);   
        }   
        return ntime;   
    }   
  
    /**  
     * 取月的最后一天  
     *   
     * @param time  
     * @return  
     */  
    @SuppressWarnings("deprecation")   
    public static String getMonthEndDay(String time) {   
        String ntime = "";   
        try {   
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");   
            Date startDate = df.parse(time);   
            int nowMonth = startDate.getMonth() + 1;   
            int nextMonth = nowMonth + 1;   
            int nowYear = startDate.getYear() + 1900;   
            String nextTime = nowYear + "-" + nextMonth + "-1";   
            Date tmpDate = df.parse(nextTime);   
            Date endDate = new Date(tmpDate.getTime() - 24 * 3600 * 1000);   
            ntime = endDate.getYear() + 1900 + "-" + (endDate.getMonth() + 1)   
                    + "-" + endDate.getDate();   
        } catch (Exception e) {   
            //System.out.println(e);   
        }   
        return ntime;   
    }
    
    
	public static JdbcTemplate getJdbcTemplate(String datasource) {
		 	Context initContext;
		 	JdbcTemplate jdbcTemplate = null;
		 	DataSource ds = null;
			try {
					initContext = new InitialContext();
				    Context envContext = (Context)initContext.lookup("java:/comp/env");
				    if (datasource == null)
				    {
				        ds = (DataSource) envContext.lookup("jdbc/jk");
				    }
				    else
				    {
				        ds = (DataSource) envContext.lookup(datasource);
				    }
				    jdbcTemplate=new JdbcTemplate(ds);
			} catch (NamingException e) {
				e.printStackTrace();
			}
			return jdbcTemplate;
	}
	
	
	public static JdbcTemplate getJdbcTemplate() {
		return getJdbcTemplate("jdbc/jk");
	}

	
	public static String getDaySub(String beginDateStr, String endDateStr) {
		long day = 0;
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Date beginDate;
		java.util.Date endDate;
		try {
			beginDate = format.parse(beginDateStr);
			endDate = format.parse(endDateStr);
			day = (endDate.getTime() - beginDate.getTime())
					/ (24 * 60 * 60 * 1000);

			// //System.out.println("相隔的天数="+day);
		} catch (ParseException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return new Long(day).toString();
	}
	
	public static long getSubDay(String beginDateStr, String endDateStr) {
		long day = 0;
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Date beginDate;
		java.util.Date endDate;
		try {
			beginDate = format.parse(beginDateStr);
			endDate = format.parse(endDateStr);
			day = (endDate.getTime() - beginDate.getTime())
					/ (24 * 60 * 60 * 1000);
		} catch (ParseException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return day;
	}
	
	
	
	public static void handleEveryDay(String beginDateStr, String endDateStr,DealHandler handler) {
		long day = 0;
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Date beginDate;
		java.util.Date endDate;
		try {
			beginDate = format.parse(beginDateStr);
			endDate = format.parse(endDateStr);
			
			day = (endDate.getTime() - beginDate.getTime())/ (24 * 60 * 60 * 1000);
			for(int i=0;i<day;i++)
			{
				//addDay(beginDateStr, 1)
				handler.doAny(addDay(beginDate, i));
			}
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
	}
	
	
	public static String getHourSub(String beginDateStr, String endDateStr) {
		long hour = 0;
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date beginDate;
		java.util.Date endDate;
		try{
			beginDate = format.parse(beginDateStr);
			endDate = format.parse(endDateStr);
			hour = (endDate.getTime() - beginDate.getTime()) / ( 60 * 60 * 1000);
			// //System.out.println("相隔的天数="+day);
		} catch (ParseException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return new Long(hour).toString();
	}
	
	
	public static String getHourSub(Date beginDate, Date endDate) {
		long hour = 0;
		hour = (endDate.getTime() - beginDate.getTime()) / ( 60 * 60 * 1000);
		return new Long(hour).toString();
	}
	
	public static long getSubDay(Date beginDate, Date endDate) {
		long day = 0;
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		day = (endDate.getTime() - beginDate.getTime())
				/ (24 * 60 * 60 * 1000);
		return new Long(day);
	}
	
	public static String getDaySub(Date beginDate, Date endDate) {
		long day = 0;
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		day = (endDate.getTime() - beginDate.getTime())
				/ (24 * 60 * 60 * 1000);
		return new Long(day).toString();
	}
	
	
	public static ArrayList<String> getDayListBySub(String beginDateStr, String endDateStr) {
		ArrayList<String> list=new ArrayList<String>();
		long day = 0;
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Date beginDate;
		java.util.Date endDate;
		try {
			beginDate = format.parse(beginDateStr);
			endDate = format.parse(endDateStr);
			day = (endDate.getTime() - beginDate.getTime())/ (24 * 60 * 60 * 1000);
			// //System.out.println("相隔的天数="+day);
			for(int i=0;i<=day;i++)
			{
				String newdate=addDay(beginDateStr,i);
				list.add(newdate);
			}
		} catch (ParseException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return list;
	}
	
	public static String addDay(String beginDateStr, int adddaycount) {
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		try {
			Date  beginDate= format.parse(beginDateStr);
			Calendar cal=Calendar.getInstance();  
			cal.setTime(beginDate);
			cal.add(Calendar.DATE, adddaycount);
			Date enddate=cal.getTime();
			return format.format(enddate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String addDayByDate(String beginDateStr, int adddaycount){
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date beginDate;
		try {
			beginDate = format.parse(beginDateStr);
			Calendar cal=Calendar.getInstance();  
			cal.setTime(beginDate);
			cal.add(Calendar.DATE, adddaycount);
			Date enddate=cal.getTime();
			return format.format(enddate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public static String addHour(String beginDateStr, int adddaycount) {
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		try {
			Date  beginDate= format.parse(beginDateStr);
			Calendar cal=Calendar.getInstance();  
			cal.setTime(beginDate);
			cal.add(Calendar.HOUR_OF_DAY, adddaycount);
			Date enddate=cal.getTime();
			return format.format(enddate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static Date addHour(Date beginDate, int count) {
		Calendar cal=Calendar.getInstance();  
		cal.setTime(beginDate);
		cal.add(Calendar.HOUR_OF_DAY, count);
		Date enddate=cal.getTime();
		return enddate;
	}
	
	
	
	
	public static String addMonth(String beginDateStr, int addcount) {
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		try {
			Date  beginDate= format.parse(beginDateStr);
			Calendar cal=Calendar.getInstance();  
			cal.setTime(beginDate);
			cal.add(Calendar.MONTH, addcount);
			Date enddate=cal.getTime();
			return format.format(enddate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	public static Date addDay(Date beginDate, int addcount) {
		Calendar cal=Calendar.getInstance();  
		cal.setTime(beginDate);
		cal.add(Calendar.DAY_OF_YEAR, addcount);
		Date enddate=cal.getTime();
		return enddate;
	}
	
	public static Date addSecond(Date beginDate, int addcount) {
			Calendar cal=Calendar.getInstance();  
			cal.setTime(beginDate);
			cal.add(Calendar.SECOND, addcount);
			Date enddate=cal.getTime();
			return enddate;
	}
	
	public static String addYEAR(String beginDateStr, int addcount) {
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		try {
			Date  beginDate= format.parse(beginDateStr);
			Calendar cal=Calendar.getInstance();  
			cal.setTime(beginDate);
			cal.add(Calendar.YEAR, addcount);
			Date enddate=cal.getTime();
			return format.format(enddate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getWeekOfDate(Date date) { 
		String[] weekDaysName = { "日", "一", "二", "三", "四", "五", "六" }; 
		String[] weekDaysCode = { "0", "1", "2", "3", "4", "5", "6" }; 
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date); 
		int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1; 
		return weekDaysName[intWeek];
	} 
	public static String getWeekOfDate1(Date date) { 
		String[] weekDaysCode = { "7","1", "2", "3", "4", "5", "6"}; 
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date); 
		int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1; 
		return weekDaysCode[intWeek];
	} 

	public static String getWeekOfDate2(Date date) { 
		String[] weekDaysCode = { "0", "1", "2", "3", "4", "5", "6" }; 
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date); 
		int intWeek = calendar.get(Calendar.DAY_OF_WEEK);
		if(intWeek==1)
		{
			intWeek=7;
		}else{
			intWeek--;
		}
//		
//		1=7
//	    2=1
//	    3=2
//	    4=3
//	    5=4
//	    6=5
//	    7=6
	    
		return new Integer(intWeek).toString();
	} 
	
	
	/**
	 * 把日期格式的字符串转换为Date类型
	 * @author 廖庆
	 * @param strDate 传入yyyy-MM-dd格式的字符串
	 * @return 返回转换后的日期
	 * @throws ParseException 
	 */
	public static Date StringToDate(String strDate) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(strDate);
		return new java.sql.Date(date.getTime());
		
	}
	public static Date StringToTime(String strDate) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Date date = sdf.parse(strDate);
		return new java.sql.Date(date.getTime());
		
	}

	/**
	 * 把秒格式转化为日期字符串
	 * @author 张晓东
	 * @param str 传入秒
	 * @return 返回转化后的日期字符串 
	 */
	public static String millisecondToDate(String str){
		long ms=Long.parseLong(str);
		Date dat=new Date(ms*1000);
//		//System.out.println(dat);
		GregorianCalendar gc = new GregorianCalendar();   
        gc.setTime(dat);  
//        //System.out.println(gc.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String sb=format.format(gc.getTime());  
		return sb;
	}
	
	public static Date StringToDate1(String strDate) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse(strDate);
		return new java.util.Date(date.getTime());
		
	}
	  public static int compare_date(Date dt1, Date dt2) {
	         
	        
	        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	         try {
//	             Date dt1 = df.parse(DATE1);
//	             Date dt2 = df.parse(DATE2);
	             if (dt1.getTime() > dt2.getTime()) {
	   //              System.out.println("dt1 在dt2前");
	                 return 1;
	             } else if (dt1.getTime() < dt2.getTime()) {
	    //             System.out.println("dt1在dt2后");
	                 return -1;
	             } else {
	                 return 0;
	             }
	         } catch (Exception exception) {
	             exception.printStackTrace();
	         }
	         return 0;
	     }
}  



//
//
//java.text
//Class SimpleDateFormat
//
//    java.lang.Object
//        java.text.Format
//            java.text.DateFormat
//                java.text.SimpleDateFormat 
//
//    All Implemented Interfaces:
//        Serializable, Cloneable
//
//
//    public class SimpleDateFormat
//    extends DateFormat
//
//    SimpleDateFormat is a concrete class for formatting and parsing dates in a locale-sensitive manner. It allows for formatting (date -> text), parsing (text -> date), and normalization.
//
//    SimpleDateFormat allows you to start by choosing any user-defined patterns for date-time formatting. However, you are encouraged to create a date-time formatter with either getTimeInstance, getDateInstance, or getDateTimeInstance in DateFormat. Each of these class methods can return a date/time formatter initialized with a default format pattern. You may modify the format pattern using the applyPattern methods as desired. For more information on using these methods, see DateFormat.
//    Date and Time Patterns
//
//    Date and time formats are specified by date and time pattern strings. Within date and time pattern strings, unquoted letters from 'A' to 'Z' and from 'a' to 'z' are interpreted as pattern letters representing the components of a date or time string. Text can be quoted using single quotes (') to avoid interpretation. "''" represents a single quote. All other characters are not interpreted; they're simply copied into the output string during formatting or matched against the input string during parsing.
//
//    The following pattern letters are defined (all other characters from 'A' to 'Z' and from 'a' to 'z' are reserved):
//
//        Letter 	Date or Time Component 	Presentation 	Examples
//        G 	Era designator 	Text 	AD
//        y 	Year 	Year 	1996; 96
//        Y 	Week year 	Year 	2009; 09
//        M 	Month in year 	Month 	July; Jul; 07
//        w 	Week in year 	Number 	27
//        W 	Week in month 	Number 	2
//        D 	Day in year 	Number 	189
//        d 	Day in month 	Number 	10
//        F 	Day of week in month 	Number 	2
//        E 	Day name in week 	Text 	Tuesday; Tue
//        u 	Day number of week (1 = Monday, ..., 7 = Sunday) 	Number 	1
//        a 	Am/pm marker 	Text 	PM
//        H 	Hour in day (0-23) 	Number 	0
//        k 	Hour in day (1-24) 	Number 	24
//        K 	Hour in am/pm (0-11) 	Number 	0
//        h 	Hour in am/pm (1-12) 	Number 	12
//        m 	Minute in hour 	Number 	30
//        s 	Second in minute 	Number 	55
//        S 	Millisecond 	Number 	978
//        z 	Time zone 	General time zone 	Pacific Standard Time; PST; GMT-08:00
//        Z 	Time zone 	RFC 822 time zone 	-0800
//        X 	Time zone 	ISO 8601 time zone 	-08; -0800; -08:00
//
//    Pattern letters are usually repeated, as their number determines the exact presentation:
//        Text: For formatting, if the number of pattern letters is 4 or more, the full form is used; otherwise a short or abbreviated form is used if available. For parsing, both forms are accepted, independent of the number of pattern letters.
//
//        Number: For formatting, the number of pattern letters is the minimum number of digits, and shorter numbers are zero-padded to this amount. For parsing, the number of pattern letters is ignored unless it's needed to separate two adjacent fields.
//
//        Year: If the formatter's Calendar is the Gregorian calendar, the following rules are applied.
//            For formatting, if the number of pattern letters is 2, the year is truncated to 2 digits; otherwise it is interpreted as a number.
//            For parsing, if the number of pattern letters is more than 2, the year is interpreted literally, regardless of the number of digits. So using the pattern "MM/dd/yyyy", "01/11/12" parses to Jan 11, 12 A.D.
//            For parsing with the abbreviated year pattern ("y" or "yy"), SimpleDateFormat must interpret the abbreviated year relative to some century. It does this by adjusting dates to be within 80 years before and 20 years after the time the SimpleDateFormat instance is created. For example, using a pattern of "MM/dd/yy" and a SimpleDateFormat instance created on Jan 1, 1997, the string "01/11/12" would be interpreted as Jan 11, 2012 while the string "05/04/64" would be interpreted as May 4, 1964. During parsing, only strings consisting of exactly two digits, as defined by Character.isDigit(char), will be parsed into the default century. Any other numeric string, such as a one digit string, a three or more digit string, or a two digit string that isn't all digits (for example, "-1"), is interpreted literally. So "01/02/3" or "01/02/003" are parsed, using the same pattern, as Jan 2, 3 AD. Likewise, "01/02/-3" is parsed as Jan 2, 4 BC. 
//        Otherwise, calendar system specific forms are applied. For both formatting and parsing, if the number of pattern letters is 4 or more, a calendar specific long form is used. Otherwise, a calendar specific short or abbreviated form is used.
//
//        If week year 'Y' is specified and the calendar doesn't support any week years, the calendar year ('y') is used instead. The support of week years can be tested with a call to getCalendar().isWeekDateSupported().
//
//        Month: If the number of pattern letters is 3 or more, the month is interpreted as text; otherwise, it is interpreted as a number.
//
//        General time zone: Time zones are interpreted as text if they have names. For time zones representing a GMT offset value, the following syntax is used:
//
//             GMTOffsetTimeZone:
//                     GMT Sign Hours : Minutes
//             Sign: one of
//                     + -
//             Hours:
//                     Digit
//                     Digit Digit
//             Minutes:
//                     Digit Digit
//             Digit: one of
//                     0 1 2 3 4 5 6 7 8 9
//
//        Hours must be between 0 and 23, and Minutes must be between 00 and 59. The format is locale independent and digits must be taken from the Basic Latin block of the Unicode standard.
//
//        For parsing, RFC 822 time zones are also accepted.
//
//        RFC 822 time zone: For formatting, the RFC 822 4-digit time zone format is used:
//
//             RFC822TimeZone:
//                     Sign TwoDigitHours Minutes
//             TwoDigitHours:
//                     Digit Digit
//
//        TwoDigitHours must be between 00 and 23. Other definitions are as for general time zones.
//
//        For parsing, general time zones are also accepted.
//        ISO 8601 Time zone: The number of pattern letters designates the format for both formatting and parsing as follows:
//
//             ISO8601TimeZone:
//                     OneLetterISO8601TimeZone
//                     TwoLetterISO8601TimeZone
//                     ThreeLetterISO8601TimeZone
//             OneLetterISO8601TimeZone:
//                     Sign TwoDigitHours
//                     Z
//             TwoLetterISO8601TimeZone:
//                     Sign TwoDigitHours Minutes
//                     Z
//             ThreeLetterISO8601TimeZone:
//                     Sign TwoDigitHours : Minutes
//                     Z
//
//        Other definitions are as for general time zones or RFC 822 time zones.
//
//        For formatting, if the offset value from GMT is 0, "Z" is produced. If the number of pattern letters is 1, any fraction of an hour is ignored. For example, if the pattern is "X" and the time zone is "GMT+05:30", "+05" is produced.
//
//        For parsing, "Z" is parsed as the UTC time zone designator. General time zones are not accepted.
//
//        If the number of pattern letters is 4 or more, IllegalArgumentException is thrown when constructing a SimpleDateFormat or applying a pattern. 
//    SimpleDateFormat also supports localized date and time pattern strings. In these strings, the pattern letters described above may be replaced with other, locale dependent, pattern letters. SimpleDateFormat does not deal with the localization of text other than the pattern letters; that's up to the client of the class.
//
//    Examples
//    The following examples show how date and time patterns are interpreted in the U.S. locale. The given date and time are 2001-07-04 12:08:56 local time in the U.S. Pacific Time time zone.
//
//        Date and Time Pattern 	Result
//        "yyyy.MM.dd G 'at' HH:mm:ss z" 	2001.07.04 AD at 12:08:56 PDT
//        "EEE, MMM d, ''yy" 	Wed, Jul 4, '01
//        "h:mm a" 	12:08 PM
//        "hh 'o''clock' a, zzzz" 	12 o'clock PM, Pacific Daylight Time
//        "K:mm a, z" 	0:08 PM, PDT
//        "yyyyy.MMMMM.dd GGG hh:mm aaa" 	02001.July.04 AD 12:08 PM
//        "EEE, d MMM yyyy HH:mm:ss Z" 	Wed, 4 Jul 2001 12:08:56 -0700
//        "yyMMddHHmmssZ" 	010704120856-0700
//        "yyyy-MM-dd'T'HH:mm:ss.SSSZ" 	2001-07-04T12:08:56.235-0700
//        "yyyy-MM-dd'T'HH:mm:ss.SSSXXX" 	2001-07-04T12:08:56.235-07:00
//        "YYYY-'W'ww-u" 	2001-W27-3
//
//    Synchronization
//
//    Date formats are not synchronized. It is recommended to create separate format instances for each thread. If multiple threads access a format concurrently, it must be synchronized externally.
//
//    See Also:
//        Java Tutorial, Calendar, TimeZone, DateFormat, DateFormatSymbols, Serialized Form
//
//
//        
//        
//http://www.cnblogs.com/longdouhzt/archive/2013/06/19/3143484.html
//
//Java SimpleDateFormat[转]
//
//[补充] [转] http://stackoverflow.com/questions/2603638/why-cant-this-simpledateformat-parse-this-date-string
//
//you'll need to supply a Locale.ENGLISH, else it won't work properly in machines with a different default locale.
//
//1 SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy h:mm:ss:SSSa", Locale.ENGLISH);
//2 System.out.println(sdf.parse("Mar 30 2010 5:27:40:140PM"));
//
//----------------------------------------
//
//
//
//[转] http://www.apihome.cn/api/java/SimpleDateFormat.html
//java.text
//类 SimpleDateFormat
//
//所有已实现的接口：
//   Serializable, Cloneable
//
//public class SimpleDateFormatextends DateFormat
//
//SimpleDateFormat 是一个以与语言环境有关的方式来格式化和解析日期的具体类。它允许进行格式化（日期 -> 文本）、解析（文本 -> 日期）和规范化。
//
//SimpleDateFormat 使得可以选择任何用户定义的日期-时间格式的模式。但是，仍然建议通过 DateFormat 中的 getTimeInstance、getDateInstance 或 getDateTimeInstance来创建日期-时间格式器。每一个这样的类方法都能够返回一个以默认格式模式初始化的日期/时间格式器。可以根据需要使用 applyPattern 方法来修改格式模式。有关使用这些方法的更多信息，请参阅 DateFormat。
//日期和时间模式
//
//日期和时间格式由日期和时间模式 字符串指定。在日期和时间模式字符串中，未加引号的字母 'A' 到 'Z' 和 'a' 到 'z' 被解释为模式字母，用来表示日期或时间字符串元素。文本可以使用单引号 (') 引起来，以免进行解释。"''" 表示单引号。所有其他字符均不解释；只是在格式化时将它们简单复制到输出字符串，或者在解析时与输入字符串进行匹配。
//
//定义了以下模式字母（所有其他字符 'A' 到 'Z' 和 'a' 到 'z' 都被保留）：
//
//   字母	日期或时间元素	表示	示例
//   G 	Era 标志符 	Text 	AD
//   y 	年 	Year 	1996; 96
//   M 	年中的月份 	Month 	July; Jul; 07
//   w 	年中的周数 	Number 	27
//   W 	月份中的周数 	Number 	2
//   D 	年中的天数 	Number 	189
//   d 	月份中的天数 	Number 	10
//   F 	月份中的星期 	Number 	2
//   E 	星期中的天数 	Text 	Tuesday; Tue
//   a 	Am/pm 标记 	Text 	PM
//   H 	一天中的小时数（0-23） 	Number 	0
//   k 	一天中的小时数（1-24） 	Number 	24
//   K 	am/pm 中的小时数（0-11） 	Number 	0
//   h 	am/pm 中的小时数（1-12） 	Number 	12
//   m 	小时中的分钟数 	Number 	30
//   s 	分钟中的秒数 	Number 	55
//   S 	毫秒数 	Number 	978
//   z 	时区 	General time zone 	Pacific Standard Time; PST; GMT-08:00
//   Z 	时区 	RFC 822 time zone 	-0800
//
//模式字母通常是重复的，其数量确定其精确表示：
//
//   Text: 对于格式化来说，如果模式字母的数量大于等于 4，则使用完全形式；否则，在可用的情况下使用短形式或缩写形式。对于解析来说，两种形式都是可接受的，与模式字母的数量无关。
//   Number: 对于格式化来说，模式字母的数量是最小的数位，如果数位不够，则用 0 填充以达到此数量。对于解析来说，模式字母的数量被忽略，除非必须分开两个相邻字段。
//   Year: 如果格式器的 Calendar 是格里高利历，则应用以下规则。
//       对于格式化来说，如果模式字母的数量为 2，则年份截取为 2 位数,否则将年份解释为 number。
//       对于解析来说，如果模式字母的数量大于 2，则年份照字面意义进行解释，而不管数位是多少。因此使用模式 "MM/dd/yyyy"，将 "01/11/12" 解析为公元 12 年 1 月 11 日。
//       在解析缩写年份模式（"y" 或 "yy"）时，SimpleDateFormat 必须相对于某个世纪来解释缩写的年份。这通过将日期调整为 SimpleDateFormat 实例创建之前的 80 年和之后 20 年范围内来完成。例如，在 "MM/dd/yy" 模式下，如果 SimpleDateFormat 实例是在 1997 年 1 月 1 日创建的，则字符串 "01/11/12" 将被解释为 2012 年 1 月 11 日，而字符串 "05/04/64" 将被解释为 1964 年 5 月 4 日。在解析时，只有恰好由两位数字组成的字符串（如 Character.isDigit(char) 所定义的）被解析为默认的世纪。其他任何数字字符串将照字面意义进行解释，例如单数字字符串，3 个或更多数字组成的字符串，或者不都是数字的两位数字字符串（例如"-1"）。因此，在相同的模式下， "01/02/3" 或 "01/02/003" 解释为公元 3 年 1 月 2 日。同样，"01/02/-3" 解析为公元前 4 年 1 月 2 日。
//   否则，则应用日历系统特定的形式。对于格式化和解析，如果模式字母的数量为 4 或大于 4，则使用日历特定的 long form。否则，则使用日历特定的 short or abbreviated form。
//   Month: 如果模式字母的数量为 3 或大于 3，则将月份解释为 text；否则解释为 number。
//   General time zone: 如果时区有名称，则将它们解释为 text。对于表示 GMT 偏移值的时区，使用以下语法：
//
//        GMTOffsetTimeZone:
//                GMT Sign Hours : Minutes
//        Sign: one of
//                + -
//        Hours:
//                Digit
//                Digit Digit
//        Minutes:
//                Digit Digit
//        Digit: one of
//                0 1 2 3 4 5 6 7 8 9
//
//   Hours 必须在 0 到 23 之间，Minutes 必须在 00 到 59 之间。格式是与语言环境无关的，并且数字必须取自 Unicode 标准的 Basic Latin 块。
//
//   对于解析来说，RFC 822 time zones 也是可接受的。
//   RFC 822 time zone: 对于格式化来说，使用 RFC 822 4-digit 时区格式：
//
//        RFC822TimeZone:
//                Sign TwoDigitHours Minutes
//        TwoDigitHours:
//                Digit Digit
//
//   TwoDigitHours 必须在 00 和 23 之间。其他定义请参阅 general time zones。
//
//   对于解析来说，general time zones 也是可接受的。
//
//SimpleDateFormat 还支持本地化日期和时间模式 字符串。在这些字符串中，以上所述的模式字母可以用其他与语言环境有关的模式字母来替换。SimpleDateFormat 不处理除模式字母之外的文本本地化；而由类的客户端来处理。
//
//
//示例
//
//以下示例显示了如何在美国语言环境中解释日期和时间模式。给定的日期和时间为美国太平洋时区的本地时间 2001-07-04 12:08:56。
//
//   日期和时间模式	结果
//   "yyyy.MM.dd G 'at' HH:mm:ss z" 	2001.07.04 AD at 12:08:56 PDT
//   "EEE, MMM d, ''yy" 	Wed, Jul 4, '01
//   "h:mm a" 	12:08 PM
//   "hh 'o''clock' a, zzzz" 	12 o'clock PM, Pacific Daylight Time
//   "K:mm a, z" 	0:08 PM, PDT
//   "yyyyy.MMMMM.dd GGG hh:mm aaa" 	02001.July.04 AD 12:08 PM
//   "EEE, d MMM yyyy HH:mm:ss Z" 	Wed, 4 Jul 2001 12:08:56 -0700
//   "yyMMddHHmmssZ" 	010704120856-0700
//   "yyyy-MM-dd'T'HH:mm:ss.SSSZ" 	2001-07-04T12:08:56.235-0700
//
//同步
//
//日期格式是不同步的。建议为每个线程创建独立的格式实例。如果多个线程同时访问一个格式，则它必须是外部同步的。
//
//
//
//
//
//另请参见：
//   Java Tutorial, Calendar, TimeZone, DateFormat, DateFormatSymbols, 序列化表格
//   
//   
//
//日期格式化{0:yyyy-MM-dd HH:mm:ss.fff}和{0:yyyy-MM-dd hh:mm:ss.fff}的区别
//{0:yyyy-MM-dd HH:mm:ss.fff}:使用24小时制格式化日期
//{0:yyyy-MM-dd hh:mm:ss.fff}:使用12小时制格式化日期
//
//以下同理,从左至右分别为-年-月-日 时:分:秒.毫秒
//{0:yyyy-MM-dd HH:mm:ss zzz}
//{0:yyyy-MM-dd HH:mm:ss.ff zzz}
//{0:yyyy-MM-dd HH:mm:ss.fff zzz}
//{0:yyyy-MM-dd HH:mm:ss.ffff zzz}
//
//以下测试代码
////---假设时间为-2009-03-17 16:50:49.92
//object objValue2 = Business.Services.ExecuteScalar(sqliteconnstring, "Select LastUpdate From CmItemClass2 order by LastUpdate desc limit 0,1");
//string lastUpdate2 = objValue2 == null ? string.Empty : string.Format("{0:yyyy-MM-dd HH:mm:ss.fff}", objValue2); //--输出2009-03-17 16:50:49.920
//string lastUpdate3 = objValue2 == null ? string.Empty : string.Format("{0:yyyy-MM-dd hh:mm:ss.fff}", objValue2); //--输出2009-03-17 04:50:49.920
//
//
////--------------------
//y 将指定 DateTime 对象的年份部分显示为位数最多为两位的数字。忽略年的前两位数字。如果年份是一位数字 (1-9)，则它显示为一位数字。
//yy 将指定 DateTime 对象的年份部分显示为位数最多为两位的数字。忽略年的前两位数字。如果年份是一位数字 (1-9)，则将其格式化为带有前导 0 (01-09)。
//yyyy 显示指定 DateTime 对象的年份部分（包括世纪）。如果年份长度小于四位，则按需要在前面追加零以使显示的年份长度达到四位。
//
//z 仅以整小时数为单位显示系统当前时区的时区偏移量。偏移量总显示为带有前导或尾随符号（零显示为“+0”），指示早于格林威治时间 (+) 或迟于格林威治时间 (-) 的小时数。值的范围是 –12 到 +13。如果偏移量为一位数 (0-9)，则将其显示为带合适前导符号的一位数。该时区的设置指定为 +X 或 –X，其中 X 是相对 GMT 以小时为单位的偏移量。所显示的偏移量受夏时制的影响。
//zz 仅以整小时数为单位显示系统当前时区的时区偏移量。偏移量总显示为带有前导或尾随符号（零显示为“+00”），指示早于格林威治时间 (+) 或迟于格林威治时间 (-) 的小时数。值范围为 –12 到 +13。如果偏移量为单个数字 (0-9)，则将其格式化为前面带有 0 (01-09) 并带有适当的前导符号。该时区的设置指定为 +X 或 –X，其中 X 是相对 GMT 以小时为单位的偏移量。所显示的偏移量受夏时制的影响。
//zzz, zzz（外加任意数量的附加“z”字符）以小时和分钟为单位显示系统当前时区的时区偏移量。偏移量总是显示为带有前导或尾随符号（零显示为“+00:00”），指示早于格林威治时间 (+) 或迟于格林威治时间 (-) 的小时和分钟数。值范围为 –12 到 +13。如果偏移量为单个数字 (0-9)，则将其格式化为前面带有 0 (01-09) 并带有适当的前导符号。该时区的设置指定为 +X 或 –X，其中 X 是相对 GMT 以小时为单位的偏移量。所显示的偏移量受夏时制的影响。
//
//
//: 时间分隔符。
/// 日期分隔符。
//" 带引号的字符串。显示转义符 (/) 之后两个引号之间的任何字符串的文本值。
//' 带引号的字符串。显示两个“'”字符之间的任何字符串的文本值。
//%c 其中 c 是标准格式字符，显示与格式字符关联的标准格式模式。
//\c 其中 c 是任意字符，转义符将下一个字符显示为文本。在此上下文中，转义符不能用于创建转义序列（如“\n”表示换行）。
//任何其他字符 其他字符作为文本直接写入输出字符串。
//
//向 DateTime.ToString 传递自定义模式时，模式必须至少为两个字符长。如果只传递“d”，则公共语言运行库将其解释为标准格式说明符，这是因为所有单个格式说明符都被解释为标准格式说明符。如果传递单个“h”，则引发异常，原因是不存在标准的“h”格式说明符。若要只使用单个自定义格式进行格式化，请在说明符的前面或后面添加一个空格。例如，格式字符串“h”被解释为自定义格式字符串。
//
//下表显示使用任意值 DateTime.Now（该值显示当前时间）的示例。示例中给出了不同的区域性和时区设置，以阐释更改区域性的影响。可以通过下列方法更改当前区域性：更改 Microsoft Windows 的“日期/时间”控制面板中的值，传递您自己的 DateTimeFormatInfo 对象，或将 CultureInfo 对象设置传递给不同的区域性。此表是说明自定义日期和时间说明符如何影响格式化的快速指南。请参阅该表下面阐释这些说明符的代码示例部分。
//
//格式说明符 当前区域性 时区 输出
//d, M en-US GMT 12, 4
//d, M es-MX GMT 12, 4
//d MMMM en-US GMT 12 April
//d MMMM es-MX GMT 12 Abril
//dddd MMMM yy gg en-US GMT Thursday April 01 A.D.
//dddd MMMM yy gg es-MX GMT Jueves Abril 01 DC
//h , m: s en-US GMT 6 , 13: 12
//hh,mm:ss en-US GMT 06,13:12
//HH-mm-ss-tt en-US GMT 06-13-12-AM
//hh:mm, G\MT z  en-US GMT 05:13 GMT +0
//hh:mm, G\MT z  en-US GMT +10:00 05:13 GMT +10
//hh:mm, G\MT zzz en-US GMT 05:13 GMT +00:00

//
//
//
///**
// * 获取现在时间,这个好用
// *
// * @return返回长时间格式 yyyy-MM-dd HH:mm:ss
// */
//public static Date getSqlDate() {
//    Date sqlDate = new java.sql.Date(new Date().getTime());
//    return sqlDate;
//}
//
///**
// * 获取现在时间
// *
// * @return返回长时间格式 yyyy-MM-dd HH:mm:ss
// */
//public static Date getNowDate() {
//    Date currentTime = new Date();
//    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    String dateString = formatter.format(currentTime);
//    ParsePosition pos = new ParsePosition(8);
//    Date currentTime_2 = formatter.parse(dateString, pos);
//    return currentTime_2;
//}
//
///**
// * 获取现在时间
// *
// * @return返回短时间格式 yyyy-MM-dd
// */
//public static Date getNowDateShort() {
//    Date currentTime = new Date();
//    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//    String dateString = formatter.format(currentTime);
//    ParsePosition pos = new ParsePosition(8);
//    Date currentTime_2 = formatter.parse(dateString, pos);
//    return currentTime_2;
//}
//
///**
// * 获取现在时间
// *
// * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
// */
//public static String getStringDate() {
//    Date currentTime = new Date();
//    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    String dateString = formatter.format(currentTime);
//    return dateString;
//}
//
///**
// * 获取现在时间
// *
// * @return 返回短时间字符串格式yyyy-MM-dd
// */
//public static String getStringDateShort() {
//    Date currentTime = new Date();
//    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//    String dateString = formatter.format(currentTime);
//    return dateString;
//}
//
///**
// * 获取时间 小时:分;秒 HH:mm:ss
// *
// * @return
// */
//public static String getTimeShort() {
//    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
//    Date currentTime = new Date();
//    String dateString = formatter.format(currentTime);
//    return dateString;
//}
//
///**
// * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
// *
// * @param strDate
// * @return
// */
//public static Date strToDateLong(String strDate) {
//    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    ParsePosition pos = new ParsePosition(0);
//    Date strtodate = formatter.parse(strDate, pos);
//    return strtodate;
//}
//
///**
// * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
// *
// * @param dateDate
// * @return
// */
//public static String dateToStrLong(java.util.Date dateDate) {
//    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    String dateString = formatter.format(dateDate);
//    return dateString;
//}
//
///**
// * 将短时间格式时间转换为字符串 yyyy-MM-dd
// *
// * @param dateDate
// * @param k
// * @return
// */
//public static String dateToStr(java.util.Date dateDate) {
//    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//    String dateString = formatter.format(dateDate);
//    return dateString;
//}
//
///**
// * 将短时间格式字符串转换为时间 yyyy-MM-dd
// *
// * @param strDate
// * @return
// */
//public static Date strToDate(String strDate) {
//    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//    ParsePosition pos = new ParsePosition(0);
//    Date strtodate = formatter.parse(strDate, pos);
//    return strtodate;
//}
//
///**
// * 得到现在时间
// *
// * @return
// */
//public static Date getNow() {
//    Date currentTime = new Date();
//    return currentTime;
//}
//
///**
// * 提取一个月中的最后一天
// *
// * @param day
// * @return
// */
//public static Date getLastDate(long day) {
//    Date date = new Date();
//    long date_3_hm = date.getTime() - 3600000 * 34 * day;
//    Date date_3_hm_date = new Date(date_3_hm);
//    return date_3_hm_date;
//}
//
///**
// * 得到现在时间
// *
// * @return 字符串 yyyyMMdd HHmmss
// */
//public static String getStringToday() {
//    Date currentTime = new Date();
//    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmmss");
//    String dateString = formatter.format(currentTime);
//    return dateString;
//}
//
///**
// * 得到现在小时
// */
//public static String getHour() {
//    Date currentTime = new Date();
//    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    String dateString = formatter.format(currentTime);
//    String hour;
//    hour = dateString.substring(11, 13);
//    return hour;
//}
//
///**
// * 得到现在分钟
// *
// * @return
// */
//public static String getTime() {
//    Date currentTime = new Date();
//    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    String dateString = formatter.format(currentTime);
//    String min;
//    min = dateString.substring(14, 16);
//    return min;
//}
//
///**
// * 根据用户传入的时间表示格式，返回当前时间的格式 如果是yyyyMMdd，注意字母y不能大写。
// *
// * @param sformat
// *            yyyyMMddhhmmss
// * @return
// */
//public static String getUserDate(String sformat) {
//    Date currentTime = new Date();
//    SimpleDateFormat formatter = new SimpleDateFormat(sformat);
//    String dateString = formatter.format(currentTime);
//    return dateString;
//}
//
///**
// * 二个小时时间间的差值,必须保证二个时间都是"HH:MM"的格式，返回字符型的分钟
// */
//public static String getTwoHour(String st1, String st2) {
//    String[] kk = null;
//    String[] jj = null;
//    kk = st1.split(":");
//    jj = st2.split(":");
//    if (Integer.parseInt(kk[0]) < Integer.parseInt(jj[0]))
//        return "0";
//    else {
//        double y = Double.parseDouble(kk[0]) + Double.parseDouble(kk[1])
//                / 60;
//        double u = Double.parseDouble(jj[0]) + Double.parseDouble(jj[1])
//                / 60;
//        if ((y - u) > 0)
//            return y - u + "";
//        else
//            return "0";
//    }
//}
//
///**
// * 得到二个日期间的间隔天数
// */
//public static String getTwoDay(String sj1, String sj2) {
//    SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
//    long day = 0;
//    try {
//        java.util.Date date = myFormatter.parse(sj1);
//        java.util.Date mydate = myFormatter.parse(sj2);
//        day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
//    } catch (Exception e) {
//        return "";
//    }
//    return day + "";
//}
//
///**
// * 时间前推或后推分钟,其中JJ表示分钟.
// */
//public static String getPreTime(String sj1, String jj) {
//    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    String mydate1 = "";
//    try {
//        Date date1 = format.parse(sj1);
//        long Time = (date1.getTime() / 1000) + Integer.parseInt(jj) * 60;
//        date1.setTime(Time * 1000);
//        mydate1 = format.format(date1);
//    } catch (Exception e) {
//    }
//    return mydate1;
//}
//
///**
// * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
// */
//public static String getNextDay(String nowdate, String delay) {
//    try {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        String mdate = "";
//        Date d = strToDate(nowdate);
//        long myTime = (d.getTime() / 1000) + Integer.parseInt(delay) * 24
//                * 60 * 60;
//        d.setTime(myTime * 1000);
//        mdate = format.format(d);
//        return mdate;
//    } catch (Exception e) {
//        return "";
//    }
//}
//
///**
// * 判断是否润年
// *
// * @param ddate
// * @return
// */
//public static boolean isLeapYear(String ddate) {
//    /**
//     * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
//     * 3.能被4整除同时能被100整除则不是闰年
//     */
//    Date d = strToDate(ddate);
//    GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
//    gc.setTime(d);
//    int year = gc.get(Calendar.YEAR);
//    if ((year % 400) == 0)
//        return true;
//    else if ((year % 4) == 0) {
//        if ((year % 100) == 0)
//            return false;
//        else
//            return true;
//    } else
//        return false;
//}
//
///**
// * 返回美国时间格式 26 Apr 2006
// *
// * @param str
// * @return
// */
//public static String getEDate(String str) {
//    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//    ParsePosition pos = new ParsePosition(0);
//    Date strtodate = formatter.parse(str, pos);
//    String j = strtodate.toString();
//    String[] k = j.split(" ");
//    return k[2] + k[1].toUpperCase() + k[5].substring(2, 4);
//}
//
///**
// * 获取一个月的最后一天
// *
// * @param dat
// * @return
// */
//public static String getEndDateOfMonth(String dat) {// yyyy-MM-dd
//    String str = dat.substring(0, 8);
//    String month = dat.substring(5, 7);
//    int mon = Integer.parseInt(month);
//    if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8
//            || mon == 10 || mon == 12) {
//        str += "31";
//    } else if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {
//        str += "30";
//    } else {
//        if (isLeapYear(dat)) {
//            str += "29";
//        } else {
//            str += "28";
//        }
//    }
//    return str;
//}
//
///**
// * 判断二个时间是否在同一个周
// *
// * @param date1
// * @param date2
// * @return
// */
//public static boolean isSameWeekDates(Date date1, Date date2) {
//    Calendar cal1 = Calendar.getInstance();
//    Calendar cal2 = Calendar.getInstance();
//    cal1.setTime(date1);
//    cal2.setTime(date2);
//    int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
//    if (0 == subYear) {
//        if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
//                .get(Calendar.WEEK_OF_YEAR))
//            return true;
//    } else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
//        // 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周
//        if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
//                .get(Calendar.WEEK_OF_YEAR))
//            return true;
//    } else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
//        if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
//                .get(Calendar.WEEK_OF_YEAR))
//            return true;
//    }
//    return false;
//}
//
///**
// * 产生周序列,即得到当前时间所在的年度是第几周
// *
// * @return
// */
//public static String getSeqWeek() {
//    Calendar c = Calendar.getInstance(Locale.CHINA);
//    String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
//    if (week.length() == 1)
//        week = "0" + week;
//    String year = Integer.toString(c.get(Calendar.YEAR));
//    return year + week;
//}
//
///**
// * 获得一个日期所在的周的星期几的日期，如要找出2002年2月3日所在周的星期一是几号
// *
// * @param sdate
// * @param num
// * @return
// */
//public static String getWeek(String sdate, String num) {
//    // 再转换为时间
//    Date dd = strToDate(sdate);
//    Calendar c = Calendar.getInstance();
//    c.setTime(dd);
//    if (num.equals("1")) // 返回星期一所在的日期
//        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
//    else if (num.equals("2")) // 返回星期二所在的日期
//        c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
//    else if (num.equals("3")) // 返回星期三所在的日期
//        c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
//    else if (num.equals("4")) // 返回星期四所在的日期
//        c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
//    else if (num.equals("5")) // 返回星期五所在的日期
//        c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
//    else if (num.equals("6")) // 返回星期六所在的日期
//        c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
//    else if (num.equals("0")) // 返回星期日所在的日期
//        c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
//    return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
//}
//
///**
// * 根据一个日期，返回是星期几的字符串
// *
// * @param sdate
// * @return
// */
//public static String getWeek(String sdate) {
//    // 再转换为时间
//    Date date = strToDate(sdate);
//    Calendar c = Calendar.getInstance();
//    c.setTime(date);
//    // int hour=c.get(Calendar.DAY_OF_WEEK);
//    // hour中存的就是星期几了，其范围 1~7
//    // 1=星期日 7=星期六，其他类推
//    return new SimpleDateFormat("EEEE").format(c.getTime());
//}
//
//public static String getWeekStr(String sdate) {
//    String str = "";
//    str = getWeek(sdate);
//    if ("1".equals(str)) {
//        str = "星期日";
//    } else if ("2".equals(str)) {
//        str = "星期一";
//    } else if ("3".equals(str)) {
//        str = "星期二";
//    } else if ("4".equals(str)) {
//        str = "星期三";
//    } else if ("5".equals(str)) {
//        str = "星期四";
//    } else if ("6".equals(str)) {
//        str = "星期五";
//    } else if ("7".equals(str)) {
//        str = "星期六";
//    }
//    return str;
//}
//
///**
// * 两个时间之间的天数
// *
// * @param date1
// * @param date2
// * @return
// */
//public static long getDays(String date1, String date2) {
//    if (date1 == null || date1.equals(""))
//        return 0;
//    if (date2 == null || date2.equals(""))
//        return 0;
//    // 转换为标准时间
//    SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
//    java.util.Date date = null;
//    java.util.Date mydate = null;
//    try {
//        date = myFormatter.parse(date1);
//        mydate = myFormatter.parse(date2);
//    } catch (Exception e) {
//    }
//    long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
//    return day;
//}
//
///**
// * 形成如下的日历 ， 根据传入的一个时间返回一个结构 星期日 星期一 星期二 星期三 星期四 星期五 星期六 下面是当月的各个时间
// * 此函数返回该日历第一行星期日所在的日期
// *
// * @param sdate
// * @return
// */
//public static String getNowMonth(String sdate) {
//    // 取该时间所在月的一号
//    sdate = sdate.substring(0, 8) + "01";
//    // 得到这个月的1号是星期几
//    Date date = strToDate(sdate);
//    Calendar c = Calendar.getInstance();
//    c.setTime(date);
//    int u = c.get(Calendar.DAY_OF_WEEK);
//    String newday = getNextDay(sdate, (1 - u) + "");
//    return newday;
//}
//
///**
// * 取得数据库主键 生成格式为yyyymmddhhmmss+k位随机数
// *
// * @param k
// *            表示是取几位随机数，可以自己定
// */
//public static String getNo(int k) {
//    return getUserDate("yyyyMMddhhmmss") + getRandom(k);
//}
//
///**
// * 返回一个随机数
// *
// * @param i
// * @return
// */
//public static String getRandom(int i) {
//    Random jjj = new Random();
//    // int suiJiShu = jjj.nextInt(9);
//    if (i == 0)
//        return "";
//    String jj = "";
//    for (int k = 0; k < i; k++) {
//        jj = jj + jjj.nextInt(9);
//    }
//    return jj;
//}
//
///**
// * @param args
// */
//public static boolean RightDate(String date) {
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//    ;
//    if (date == null)
//        return false;
//    if (date.length() > 10) {
//        sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//    } else {
//        sdf = new SimpleDateFormat("yyyy-MM-dd");
//    }
//    try {
//        sdf.parse(date);
//    } catch (ParseException pe) {
//        return false;
//    }
//    return true;
//}
//
///***************************************************************************
// * //nd=1表示返回的值中包含年度 //yf=1表示返回的值中包含月份 //rq=1表示返回的值中包含日期 //format表示返回的格式 1
// * 以年月日中文返回 2 以横线-返回 // 3 以斜线/返回 4 以缩写不带其它符号形式返回 // 5 以点号.返回
// **************************************************************************/
//public static String getStringDateMonth(String sdate, String nd, String yf,
//        String rq, String format) {
//    Date currentTime = new Date();
//    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//    String dateString = formatter.format(currentTime);
//    String s_nd = dateString.substring(0, 4); // 年份
//    String s_yf = dateString.substring(5, 7); // 月份
//    String s_rq = dateString.substring(8, 10); // 日期
//    String sreturn = "";
//    if (sdate == null || sdate.equals("") || !Isdate(sdate)) { // 处理空值情况
//        if (nd.equals("1")) {
//            sreturn = s_nd;
//            // 处理间隔符
//            if (format.equals("1"))
//                sreturn = sreturn + "年";
//            else if (format.equals("2"))
//                sreturn = sreturn + "-";
//            else if (format.equals("3"))
//                sreturn = sreturn + "/";
//            else if (format.equals("5"))
//                sreturn = sreturn + ".";
//        }
//        // 处理月份
//        if (yf.equals("1")) {
//            sreturn = sreturn + s_yf;
//            if (format.equals("1"))
//                sreturn = sreturn + "月";
//            else if (format.equals("2"))
//                sreturn = sreturn + "-";
//            else if (format.equals("3"))
//                sreturn = sreturn + "/";
//            else if (format.equals("5"))
//                sreturn = sreturn + ".";
//        }
//        // 处理日期
//        if (rq.equals("1")) {
//            sreturn = sreturn + s_rq;
//            if (format.equals("1"))
//                sreturn = sreturn + "日";
//        }
//    } else {
//        // 不是空值，也是一个合法的日期值，则先将其转换为标准的时间格式
//        // sdate = roc.util.RocDate.getOKDate(sdate);
//        s_nd = sdate.substring(0, 4); // 年份
//        s_yf = sdate.substring(5, 7); // 月份
//        s_rq = sdate.substring(8, 10); // 日期
//        if (nd.equals("1")) {
//            sreturn = s_nd;
//            // 处理间隔符
//            if (format.equals("1"))
//                sreturn = sreturn + "年";
//            else if (format.equals("2"))
//                sreturn = sreturn + "-";
//            else if (format.equals("3"))
//                sreturn = sreturn + "/";
//            else if (format.equals("5"))
//                sreturn = sreturn + ".";
//        }
//        // 处理月份
//        if (yf.equals("1")) {
//            sreturn = sreturn + s_yf;
//            if (format.equals("1"))
//                sreturn = sreturn + "月";
//            else if (format.equals("2"))
//                sreturn = sreturn + "-";
//            else if (format.equals("3"))
//                sreturn = sreturn + "/";
//            else if (format.equals("5"))
//                sreturn = sreturn + ".";
//        }
//        // 处理日期
//        if (rq.equals("1")) {
//            sreturn = sreturn + s_rq;
//            if (format.equals("1"))
//                sreturn = sreturn + "日";
//        }
//    }
//    return sreturn;
//}
//
//public static String getNextMonthDay(String sdate, int m) {
//    sdate = getOKDate(sdate);
//    int year = Integer.parseInt(sdate.substring(0, 4));
//    int month = Integer.parseInt(sdate.substring(5, 7));
//    month = month + m;
//    if (month < 0) {
//        month = month + 12;
//        year = year - 1;
//    } else if (month > 12) {
//        month = month - 12;
//        year = year + 1;
//    }
//    String smonth = "";
//    if (month < 10)
//        smonth = "0" + month;
//    else
//        smonth = "" + month;
//    return year + "-" + smonth + "-10";
//}
//
//public static String getOKDate(String sdate) {
//    if (sdate == null || sdate.equals(""))
//        return getStringDateShort();
//    if (!Isdate(sdate)) {
//        sdate = getStringDateShort();
//    }
//    // 将“/”转换为“-”
//    sdate = sdate.replace("/", "-");
//    // 如果只有8位长度，则要进行转换
//    if (sdate.length() == 8)
//        sdate = sdate.substring(0, 4) + "-" + sdate.substring(4, 6) + "-"
//                + sdate.substring(6, 8);
//    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//    ParsePosition pos = new ParsePosition(0);
//    Date strtodate = formatter.parse(sdate, pos);
//    String dateString = formatter.format(strtodate);
//    return dateString;
//}
//
//public static void main(String[] args) throws Exception {
//    try {
//        // System.out.print(Integer.valueOf(getTwoDay("2006-11-03 12:22:10",
//        // "2006-11-02 11:22:09")));
//    } catch (Exception e) {
//        throw new Exception();
//    }
//    // System.out.println("sss");
//} 