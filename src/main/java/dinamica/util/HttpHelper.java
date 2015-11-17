package dinamica.util;


/*
 * httpclient 4.3.3
 * http://hc.apache.org/httpcomponents-client-4.3.x/tutorial/html/index.html
 * http://hc.apache.org/httpcomponents-client-4.3.x/tutorial/html/connmgmt.html#d5e363
 */
import java.io.File;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.charset.CodingErrorAction;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.*;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import dinamica.guid.Guid;

/*
 * HttpClient 4.3中Https的使用方法
 * http://www.nitrohsu.com/http-client-4-3-zhong-https-de-shi-yong-fang-fa.html
 */
public class HttpHelper    
{      

	private static final Log logger = LogFactory.getLog(HttpHelper.class);
	
	public  static void main(String args[]) throws Exception
	{
//		System.out.println(WeiXinPayClient.class.getResource("apiclient_cert.p12").getPath());
//		KeyStore trustStore=CertHeper.readP12(WeiXinPayClient.class.getResource("apiclient_cert.p12").getPath(),Configure.mchID);
//      	
		
		
//		System.out.println(dinamica.util.URLEncoder.encode("http://www.ly.com/flight/flight-neBookPort.aspx?type=1&goguid=75791c5b-ff65-4f29-b706-72f052e05554&goflightno=KN5218&gocabincode=T&gofzproductid=133&KeyCode=9fef9b49d37695115ba64c762c014a73&refid=26875429"));;
//		HashMap resp = HttpHelper.sendBySavedFile("/logs/362687476600410112.req.txt");
//		System.out.println(resp.get("data"));;
		//String json="";
		
//		System.out.println(HttpHelper.sendJson("http://www.baidu.com", "{}", "192.168.1.116", 808));;
//		String md5=HttpHelper.getFileMd5("http://tcopenapi.17usoft.com/static/xml/hotel/hotellist.xml");
//		//logger.debug(md5);
		//eefb6287476bacca36fb7ee5d10b2009
		//eefb6287476bacca36fb7ee5d10b2009
//		
//		URL url = new URL("http://tcopenapi.17usoft.com/static/xml/hotel/hotellist.xml");
//		InputStream is = url.openStream();
//		MessageDigest md = MessageDigest.getInstance("MD5");
//		String digest = getDigest(is, md, 2048);
//		//logger.debug("MD5 Digest:" + digest);
//		
		//108bef2b3d9c7673477b9ecce368a688
		
		
		//String file=TongchengImp.class.getResource(".").getFile();
//		//copy(new FileInputStream(file),new FileOutputStream("c:/aa.xml"),1024);
//		FileHelper.saveStreamToFile(new FileInputStream(file),new File("c:/aa.xml"));
//		//logger.debug(file);
//		//logger.debug(FileHelper.getDigest(new File(file)));
		////logger.debug(file);
		//downloadAndSaveToFile("http://tcopenapi.17usoft.com/static/xml/hotel/hotellist.xml",new File("c:/hotellist.xml"));
		//HttpHelper433.sendPost("http:
		//www.baidu.com", null);
		//ExecutorService pool=Executors.newFixedThreadPool(1000);
//		for(int i=0;i<10;i++)
//		{
//			DealThread dh=new DealThread();
//			new Thread(dh).start();
//			//pool.execute(dh);
//		}
		
//		HashMap str=HttpHelper.sendGet("https://api.test.lohoo.com/rest?format=json&method=hotel.order.create&user=Agent1419328190&timestamp=1431328165&signature=be70ef82a8ef1d4ea0c6fa0467bc85a7&data=%7B%22Local%22%3A%22zh_CN%22%2C%22Request%22%3A%7B%22AffiliateConfirmationId%22%3A%22my-order-id-2%22%2C%22ArrivalDate%22%3A%222015-05-12+15%3A09%3A24%22%2C%22ConfirmationType%22%3A%22NotAllowedConfirm%22%2C%22Contact%22%3A%7B%22Mobile%22%3A%2218600000001%22%2C%22Name%22%3A%22Ms+White%22%7D%2C%22CreditCard%22%3A%7B%22ExpirationMonth%22%3A10%2C%22ExpirationYear%22%3A2016%2C%22HolderName%22%3A%22de97d8227b8c677bb1638ef3d3ff8bf8cbe5423dcb32294f4dde2b6367797f34%22%2C%22IdNo%22%3A%22de97d8227b8c677b113f0a514c2f9cb6359ee1af0ce5d40b8f2e0e4097ca9c36%22%2C%22IdType%22%3A%22IdentityCard%22%2C%22Number%22%3A%22de97d8227b8c677b799367a17d86b7465442d788c76f026c60956bc89edb80d2%22%2C%22cVV%22%3A%22de97d8227b8c677b89dbc57a8038617e%22%7D%2C%22CurrencyCode%22%3A%22HKD%22%2C%22CustomerIPAddress%22%3A%22211.151.230.21%22%2C%22CustomerType%22%3A%22OtherForeign%22%2C%22DepartureDate%22%3A%222015-05-13+15%3A09%3A24%22%2C%22EarliestArrivalTime%22%3A%222015-05-12+15%3A00%3A00%22%2C%22HotelId%22%3A%2210101129%22%2C%22IsForceGuarantee%22%3Afalse%2C%22IsGuaranteeOrCharged%22%3Afalse%2C%22IsNeedInvoice%22%3Afalse%2C%22IsNewPaymentFlow%22%3Afalse%2C%22LatestArrivalTime%22%3A%222015-05-12+17%3A00%3A00%22%2C%22NoteToElong%22%3A%22%22%2C%22NumberOfCustomers%22%3A1%2C%22NumberOfRooms%22%3A1%2C%22OrderRooms%22%3A%5B%7B%22Customers%22%3A%5B%7B%22Name%22%3A%22Jack+White%22%7D%5D%7D%5D%2C%22PaymentType%22%3A%22SelfPay%22%2C%22RatePlanId%22%3A145742%2C%22RoomTypeId%22%3A%220010%22%2C%22TotalPrice%22%3A600%7D%2C%22Version%22%3A1.1%7D", null, "119.254.84.180", 8888);
//		System.out.println(str.get("returncode"));
//		System.out.println(str.get("returnmsg"));
//		System.out.println(str.get("data"));
		
		//http://www.eehuisdfsdfas.com/
		//http://www.baiduxxx.com
		
//		
//		HashMap aaa = HttpHelper.sendGet("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superplus/js/lib/jquery-1.10.2_d88366fd.js","119.254.84.180",8777);
//		System.out.println(aaa);
//		
//		
//		HashMap bbb = HttpHelper.sendPost("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superplus/js/lib/jquery-1.10.2_d88366fd.js", new HashMap(), "UTF-8", "119.254.84.180", 8777);
//		System.out.println(bbb);
//		
		
		
		
//		短信运营海艳 2015-9-15 16:27:59
//		管理后台：http://www.ydqxt.com
//		接口文档：http://www.ydqxt.com:8080/help.asp
//
//		用户名：AYH
//		密码：888888
//		扩展号：2132
//		测试条数：20条（仅供接口测试使用）
//		发送类型：行业应用
//		发送要求：需提前报备签名，不报备人工审核，审核人员手动报备。
//		你测试一下这个的响应速度呢
		
		
		HashMap param = new HashMap();
		param.put("username", "AYH");
		param.put("password", "888888");
		//param.put("passwordMd5", "");
		param.put("mobile", "13730666347");
		param.put("message", "【返利宝会员管家】验证码123456   发送时间："+DateHelper.getTimeString());
		param.put("Ext", "2132");
		HashMap bbb = HttpHelper.sendPost("http://211.151.85.133:8080/sendsms.asp",param , "GBK");
		System.out.println(JsonHelper.getObjectMapperInstance().writeValueAsString(bbb));
		//{"data":"272337380","returnmsg":"","returncode":"00000000","url":"http://211.151.85.133:8080/sendsms.asp"}
	}
	
	
	static CloseableHttpClient  httpClient;
	static int connectTimeout=60000;
	static int socketTimeout=60000;
	org.apache.http.client.CookieStore cookieStore = new BasicCookieStore();
	
	static {
		//http://hadoop.cf/visit-https-without-auth/
		//http://blog.csdn.net/chaijunkun/article/details/40145685
		//http://blog.csdn.net/trbbadboy/article/details/11562511
		
		
		
       Registry<ConnectionSocketFactory> socketFactoryRegistry=null;
       try {
    	   
    	   
    	   X509HostnameVerifier hostnameVerifier = new X509HostnameVerifier() {
			@Override
			public boolean verify(String arg0, SSLSession arg1) {
				return true;
			}
			@Override
			public void verify(String arg0, SSLSocket arg1) throws IOException {
				
			}
			@Override
			public void verify(String arg0, X509Certificate arg1)
					throws SSLException {
			}
			@Override
			public void verify(String arg0, String[] arg1, String[] arg2)
					throws SSLException {
			}
           };
           
           
         //实现X509TrustManager接口，如下三个方法为必须实现，将其实现为什么都不做
           X509TrustManager tm = new X509TrustManager() {
              public void checkClientTrusted(X509Certificate[] xcs, String string) {
              }

              public void checkServerTrusted(X509Certificate[] xcs, String string) {
              }

              public X509Certificate[] getAcceptedIssuers() {
                  return null;
              }
          };
          
//          
//           
//	       	
//          	KeyStore trustStore=CertHeper.readP12(WeiXinPayClient.class.getResource("apiclient_cert.p12").getPath(),Configure.mchID);
//          	
//            // Trust own CA and all self-signed certs
//            SSLContext sslcontext = SSLContexts.custom()
//                    .loadKeyMaterial(trustStore, Configure.mchID.toCharArray())
//                    .build();
//            
//            // Allow TLSv1 protocol only
//            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
//                    sslcontext,
//                    new String[] { "TLSv1" },
//                    null,
//                    SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
//            
          
	       	socketFactoryRegistry = RegistryBuilder
	                .<ConnectionSocketFactory> create()
	                .register("http", PlainConnectionSocketFactory.INSTANCE)
	                .build();
	                //.register("https", sslsf).build();
	       	
		}catch (Exception e) {
			e.printStackTrace();
		}
       
       
       
        
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		cm.setMaxTotal(200);
		cm.setDefaultMaxPerRoute(20);
		
		HttpRequestRetryHandler myRetryHandler = new HttpRequestRetryHandler() {
		    public boolean retryRequest(
		            IOException exception,
		            int executionCount,
		            HttpContext context) {
		        if (executionCount >= 5) {
		            // Do not retry if over max retry count
		        	//// 如果超过最大重试次数，那么就不要继续了 
		            return false;
		        }
		        if (exception instanceof java.io.InterruptedIOException) {
		            // Timeout
		            return false;
		        }
		        if (exception instanceof UnknownHostException) {
		            // Unknown host
		            return false;
		        }
		    	if (exception instanceof NoHttpResponseException) { 
		    		// // 如果服务器丢掉了连接，那么就重试 
		    		return true; 
		    	} 
		        if (exception instanceof ConnectTimeoutException) {
		            // Connection refused
		            return false;
		        }
		        if (exception instanceof SSLException) {
		            // // 不要重试SSL握手异常 
		            return false;
		        }
		        HttpClientContext clientContext = HttpClientContext.adapt(context);
		        HttpRequest request = clientContext.getRequest();
		        boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
		        if (idempotent) {
		            // Retry if the request is considered idempotent
		        	//// 如果请求被认为是幂等的，那么就重试 
		            return true;
		        }
		        return false;
		    }

		};
		
		
		
		 ConnectionConfig connectionConfig = ConnectionConfig.custom()
		            .setMalformedInputAction(CodingErrorAction.IGNORE)
		            .setUnmappableInputAction(CodingErrorAction.IGNORE)
		            .setCharset(Consts.UTF_8)
		         // .setMessageConstraints(messageConstraints)
		            .build();
		 
		 
		 cm.setDefaultConnectionConfig(connectionConfig);
		  
			RequestConfig globalConfig = RequestConfig.custom()
			        .setCookieSpec(CookieSpecs.BEST_MATCH)
			        .build();
			RequestConfig localConfig = RequestConfig.copy(globalConfig)
			        .setCookieSpec(CookieSpecs.BROWSER_COMPATIBILITY)
			        .build();
			
			
		
		//配置TOMCAT及httpClient的keepalive以高效利用长连接  
		//http://backend.blog.163.com/blog/static/2022941262014029105618173/
		ConnectionKeepAliveStrategy myStrategy = new ConnectionKeepAliveStrategy() {
		    public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
		        // Honor 'keep-alive' header
		        HeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator(HTTP.CONN_KEEP_ALIVE));
		        while (it.hasNext()) {
		            HeaderElement he = it.nextElement();
		            String param = he.getName();
		            String value = he.getValue();
		            if (value != null && param.equalsIgnoreCase("timeout")) {
		                try {
		                    return Long.parseLong(value) * 1000;
		                } catch(NumberFormatException ignore) {

		                }
		            }
		        }
		        HttpHost target = (HttpHost) context.getAttribute( HttpClientContext.HTTP_TARGET_HOST);
		        if ("www.naughty-server.com".equalsIgnoreCase(target.getHostName())) {
		            // Keep alive for 5 seconds only
		            return 5 * 1000;
		        } else {
		        	return 30 * 1000;
		        }
		    }
		};
		
		
		HttpClientBuilder builder = 
				HttpClients.custom()
				.setKeepAliveStrategy(myStrategy)
				.setDefaultRequestConfig(localConfig);//.setDefaultCookieStore(cookieStore);;
		httpClient = builder.setConnectionManager(cm).setRetryHandler(myRetryHandler).build();

		new IdleConnectionMonitorThread(cm).start();
	}
	
	
	public static Map<String, String> getQueryMap(String query)
	{
	    String[] params = query.split("&");
	    Map<String, String> map = new HashMap<String, String>();
	    for (String param : params)
	    {
	        String name = param.split("=")[0];
	        String value = param.split("=")[1];
	        map.put(name, value);
	    }
	    return map;
	}
	
	public static class IdleConnectionMonitorThread extends Thread {
		// private final HttpClientConnectionManager  connMgr;
	    private final PoolingHttpClientConnectionManager  connMgr;
	    private volatile boolean shutdown=false;
	    
	    public IdleConnectionMonitorThread(PoolingHttpClientConnectionManager connMgr) {
	        super();
	        this.connMgr = connMgr;
	    }
	    
	    @Override
	    public void run() {
	        try {
	            while (!shutdown) {
	                synchronized (this) {
	                    wait(5000);
	                    //Available Gets the number idle persistent connections.
	                    //Leased Gets the number of persistent connections tracked by the connection manager currently being used to execute requests.
	                    //Max   Gets the maximum number of allowed persistent connections.
	                    //Pending  Gets the number of connection requests being blocked awaiting a free connection.
	                    logger.debug("before Available:"+connMgr.getTotalStats().getAvailable()+" Leased:"+connMgr.getTotalStats().getLeased()+" Max:"+connMgr.getTotalStats().getMax()+" Pending:"+connMgr.getTotalStats().getPending());
	                    //System.out.println("before Available:"+connMgr.getTotalStats().getAvailable()+" Leased:"+connMgr.getTotalStats().getLeased()+" Max:"+connMgr.getTotalStats().getMax()+" Pending:"+connMgr.getTotalStats().getPending());
	                    connMgr.closeExpiredConnections();
	                    connMgr.closeIdleConnections(1, TimeUnit.SECONDS);
	                    logger.debug("after Available:"+connMgr.getTotalStats().getAvailable()+" Leased:"+connMgr.getTotalStats().getLeased()+" Max:"+connMgr.getTotalStats().getMax()+" Pending:"+connMgr.getTotalStats().getPending());
	                    //System.out.println("after Available:"+connMgr.getTotalStats().getAvailable()+" Leased:"+connMgr.getTotalStats().getLeased()+" Max:"+connMgr.getTotalStats().getMax()+" Pending:"+connMgr.getTotalStats().getPending());
	                }
	            }
	        } catch (InterruptedException ex) {
	        	ex.printStackTrace();
	        }
	    }
	    public void shutdown() {
	        shutdown = true;
	        synchronized (this) {
	            notifyAll();
	        }
	    }
	}
	
	
	/*
	 * 
HttpClient 4.3超时设置  
http://www.open-open.com/lib/view/open1383751765321.html
您的评价: 	
还行
	
 收藏该经验    

HttpClient 4.3。HttpClient这货和Lucene一样，每个版本的API都变化很大，这有点让人头疼。就好比创建一个HttpClient对象吧，每一个版本的都不一样，

3.X是这样的
1	HttpClient httpClient=new DefaultHttpClient();
4.3是这样的
1	CloseableHttpClient httpClient = HttpClients.createDefault();
当然，上面这些变化只不过是一些小变化，大家看看API大家就都会了。

我要讲的是超时设置,HttpClient有三种超时设置，最近比较忙，没时间具体归纳总结，以后再补上，我这里就讲一些最简单最易用的超时设置方法。

这是个3.X的超时设置方法
1	HttpClient client = new HttpClient();
2	client.setConnectionTimeout(30000); 
3	client.setTimeout(30000);
1	HttpClient httpClient= new HttpClient(); 
2	httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
4.X版本的超时设置(4.3后已过时)
1	HttpClient httpClient=new DefaultHttpClient();
2	httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,2000);//连接时间
3	httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,2000);//数据传输时间
4.3版本超时设置
1	CloseableHttpClient httpClient = HttpClients.createDefault();
2	HttpGet httpGet=new HttpGet("http://www.baidu.com");//HTTP Get请求(POST雷同)
3	RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();//设置请求和传输超时时间
4	httpGet.setConfig(requestConfig);
5	httpClient.execute(httpGet);//执行请求
BTW,4.3版本不设置超时的话，一旦服务器没有响应，等待时间N久(>24小时)。 
	 */
	

	
	
	public static String convertHashMapToURL(String path,Map<String, String> params, String enc) throws UnsupportedEncodingException 
    {
        StringBuilder sb = new StringBuilder(path);   
        if(params!=null && params.size()>0 )
        {
        	sb.append('?');
        	for(Map.Entry<String, String> entry : params.entrySet())   
	        {   
	        		 sb.append(entry.getKey()).append('=').append(URLEncoder.encode(entry.getValue(), enc)).append('&');   
	        }
        	sb.deleteCharAt(sb.length()-1);   
        }
		return sb.toString();
    }
	public static HashMap sendGet(String path) 
    {
		return sendGet( path, new HashMap(), new HashMap(),"UTF-8");
    }
	public static HashMap sendGet(String path, Map<String, String> params, String enc) 
    {
		return sendGet( path, new HashMap(),params,enc);
    }
	public static HashMap sendGet(String path, String proxyhost,int proxyport) 
    {
		return sendGet( path, new HashMap(),new HashMap(),"UTF-8",proxyhost,proxyport);
    }
	public static HashMap sendGet(String path, Map<String, String> params, String proxyhost,int proxyport) 
    {
		return sendGet( path, new HashMap(),params,"UTF-8",proxyhost,proxyport);
    }
	public static HashMap sendGet(String path, Map<String, String> headers,Map<String, String> params, String enc) 
	{
		return sendGet( path,  headers, params,  enc, null, 0) ;
	}
	public static HashMap sendGet(String path,Map<String, String> params, String enc,String proxyhost,int proxyport) 
	{
		return sendGet( path,  new HashMap(), params,  enc, proxyhost,proxyport) ;
	}
    public static HashMap sendGet(String path, Map<String, String> headers,Map<String, String> params, String enc,String proxyhost,int proxyport) 
    {
    	HashMap map=new HashMap();
    	HttpGet method=null;
    	CloseableHttpResponse response =null;
    	InputStream is=null;
		try{
	        StringBuilder sb = new StringBuilder(path);   
	        
	        if(params!=null && params.size()>0 )
	        {
	        	sb.append('?');
	        	for(Map.Entry<String, String> entry : params.entrySet())   
		        {   
		        		 sb.append(entry.getKey()).append('=').append(URLEncoder.encode(entry.getValue(), enc)).append('&');   
		        }
	        	sb.deleteCharAt(sb.length()-1);   
	        }
	        
	        System.out.println("sendGet:"+sb.toString());
	        map.put("returncode", "11111111");
	        map.put("returnmsg", "");
	        map.put("data", "");
	        map.put("url",sb.toString());
	        method = new HttpGet(sb.toString());
	        
	        
	        if(headers!=null)
	        {
	        	  Header[] hds=new Header[headers.size()];
	  	        int i=0;
	  	        for(String key:headers.keySet())
	  	        {
	  	        	hds[i++]=new BasicHeader(key,headers.get(key));
	  	        }
	  			method.setHeaders(hds);
	        }
	      
			
			HttpClientContext  context = HttpClientContext.create();
			Builder builder = RequestConfig.custom();
			if(proxyhost!=null && proxyport>0)
			{
				HttpHost proxy = new HttpHost(proxyhost,proxyport, "http");
				builder.setProxy(proxy);
			}
			RequestConfig requestConfig = builder.setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
	    	method.setConfig(requestConfig);
	    	
	    	//RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
	    	//method.setConfig(requestConfig);
			//WebClientDevWrapper.wrapClient(httpClient);
	    	response= httpClient.execute( method, context);
	    	
	    	is = response.getEntity().getContent();
	    	
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String dc  = Charset.defaultCharset().name();  
				BufferedReader br = new BufferedReader(new UnicodeReader(is,enc));    
				String str = IOUtils.toString(br);
				EntityUtils.consume(response.getEntity());
				map.put("data", str);
				map.put("returncode", "00000000");
				return map;
			} else {
				method.abort();
				map.put("returncode", "00000001");
				map.put("returnmsg", new Integer(response.getStatusLine().getStatusCode()).toString());
			}
		}catch(Exception ex)
		{
			map.put("returncode", "00000001");
			map.put("returnmsg", ex.getMessage());
		}finally 
		{
			if(is!=null)
			{
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(method!=null )    
			{
				method.releaseConnection();    
			}
			if(response!=null)
			{
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return map;
    }
    
    
    
    
    public static InputStream sendGetForInputStream(String path,Map<String, String> params,String enc) throws ClientProtocolException, IOException 
    {
    	HashMap map=new HashMap();
    	HttpGet method=null;
    	CloseableHttpResponse response =null;
        StringBuilder sb = new StringBuilder(path);   
        
        if(params!=null && params.size()>0 )
        {
        	sb.append('?');
        	for(Map.Entry<String, String> entry : params.entrySet())   
	        {   
	        		 sb.append(entry.getKey()).append('=').append(URLEncoder.encode(entry.getValue(), enc)).append('&');   
	        }
        	sb.deleteCharAt(sb.length()-1);   
        }
        
        System.out.println("sendGet:"+sb.toString());
        map.put("returncode", "11111111");
        map.put("returnmsg", "");
        map.put("data", "");
        map.put("url",sb.toString());
        method = new HttpGet(sb.toString());
        
		
		HttpClientContext  context = HttpClientContext.create();
		Builder builder = RequestConfig.custom();
		RequestConfig requestConfig = builder.setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
    	method.setConfig(requestConfig);
    	
    	//RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
    	//method.setConfig(requestConfig);
		//WebClientDevWrapper.wrapClient(httpClient);
    	response= httpClient.execute( method, context);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			String dc  = Charset.defaultCharset().name();  
			HttpEntity entity = response.getEntity();
			return entity.getContent();
		}
		return null;
    }
    
    
    public static HashMap sendPost(String path,Map<String,String>  params,String enc)   
    {
    	Map<String,String[]> paramresult=new HashMap<String,String[]>();
    	for(String key:params.keySet())
    	{
    		String value=params.get(key);
    		String[] valuearray=new String[]{value};
			paramresult.put(key, valuearray);
    	}
    	return sendPost(path,null,paramresult,enc,null,0);
    }
    public static HashMap sendPost(String path,Map<String,String>  headers, Map<String, String> params, String enc)   
    {
    	Map<String,String[]> paramresult=new HashMap<String,String[]>();
    	for(String key:params.keySet())
    	{
    		String value=params.get(key);
    		String[] valuearray=new String[]{value};
			paramresult.put(key, valuearray);
    	}
    	return sendPost(path,headers,paramresult,enc,null,0);
    }
    public static HashMap sendPost(String path,Map<String,String>  params,String enc,String proxyhost,int proxyport)   
    {
    	Map<String,String[]> paramresult=new HashMap<String,String[]>();
    	for(String key:params.keySet())
    	{
    		Object value=params.get(key);
    		String[] valuearray=new String[]{value.toString()};
			paramresult.put(key, valuearray);
    	}
    	return sendPost(path,null,paramresult,enc,proxyhost,proxyport);
    }
    public static HashMap sendPost(String path,Map<String,String>  headers,InputStream is)
    {
    	return sendPost(path,headers,is,null,0);
    }
    public static HashMap sendPost(String path,Map<String, String> headers,InputStream isinput,String proxyhost,int proxyport)   
    {
    	HashMap map=new HashMap();
    	map.put("returncode", "00000001");
        map.put("returnmsg", "");
        map.put("data", "");
        map.put("url", path);
        HttpPost method =null;
        CloseableHttpResponse response=null;
        InputStream is=null;
        try 
		{
        	InputStreamEntity httpentity = new InputStreamEntity(isinput, isinput.available());  
	        method = new HttpPost(path);
	        if(headers.size()>0)
	        {
	        	Header[] hds=new Header[]{};
	 	        int i=0;
	 	        for(String key:headers.keySet())
	 	        {
	 	        	hds[i++]=new BasicHeader(key,headers.get(key));
	 	        }
	 			method.setHeaders(hds);
	        }
	        HttpClientContext  context = HttpClientContext.create();
			Builder builder = RequestConfig.custom();
			if(proxyhost!=null && proxyport>0)
			{
				HttpHost proxy = new HttpHost(proxyhost,proxyport, "http");
				builder.setProxy(proxy);
			}
			RequestConfig requestConfig = builder.setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
	    	method.setConfig(requestConfig);
	    	//RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
	    	method.setConfig(requestConfig);
	    	method.setEntity(httpentity);
	        
			WebClientDevWrapper.wrapClient(httpClient);
			response = httpClient.execute( method, context);
				
			is= response.getEntity().getContent();
			
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String str = EntityUtils.toString(response.getEntity());
				map.put("data", str);
				map.put("returncode", "00000000");
				return map;
			}
			else 
			{
				method.abort();
				map.put("returncode", "00000001");
				map.put("returnmsg", new Integer(response.getStatusLine().getStatusCode()).toString());
			}
		}catch(Exception ex)
		{
			map.put("returncode", "00000001");
			map.put("returnmsg", ex.getMessage());
		}finally 
		{
			if(is!=null)
			{
				try {
					is.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
			if(method !=null )    
			{
				method.releaseConnection();    
			}
			if(response!=null)
			{
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
        return map;   
    }
    
    

    
    public static HashMap sendPost(String path, Map<String, String> headers, Map<String, String[]> params)   
    {
    	return sendPost( path, headers, params, "UTF-8", null,0);
    }
    public static HashMap sendPost(String path, Map<String, String> headers, Map<String, String[]> params, String enc,String proxyhost,int proxyport)   
    {
    	HashMap map=new HashMap();
    	map.put("returncode", "00000001");
        map.put("returnmsg", "");
        map.put("data", "");
        map.put("url",path);
        
        HttpPost method =null;
        CloseableHttpResponse response=null;
        InputStream is=null;
		try 
		{
	        List<NameValuePair> formParams = new ArrayList<NameValuePair>();   
	        if(params!=null)
	        {
	        	for(String key: params.keySet())   
		        {
	        		 Object value=params.get(key);
	        		 if(value instanceof   String[] )
	        		 {
	        			 String valuearray[]=(String[])value;
	        			 if(valuearray.length==1)
	        			 {
	        				 formParams.add(new BasicNameValuePair(key,valuearray[0])); 
	        			 }else{
	        				 for(int i=0;i<valuearray.length;i++)
		        			 {
		        				 formParams.add(new BasicNameValuePair(key+"["+i+"]",valuearray[i])); 
		        			 }
	        			 }
	        		 }
	        		 else
	        		 {
	        			 formParams.add(new BasicNameValuePair(key, (String) value));    
	        		 }
		        }
	        }
	        HttpEntity httpentity = new UrlEncodedFormEntity(formParams, enc);   
	        method = new HttpPost(path);
	        if(headers!=null && headers.size()>0)
	        {
	        	Header[] hds=new Header[]{};
	 	        int i=0;
	 	        for(String key:headers.keySet())
	 	        {
	 	        	hds[i++]=new BasicHeader(key,headers.get(key));
	 	        }
	 			method.setHeaders(hds);
	        }
	        
	        HttpClientContext  context = HttpClientContext.create();
			Builder builder = RequestConfig.custom();
			if(proxyhost!=null && proxyport>0)
			{
				HttpHost proxy = new HttpHost(proxyhost,proxyport, "http");
				builder.setProxy(proxy);
			}
			
			RequestConfig requestConfig = builder.setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
	    	method.setConfig(requestConfig);
	    	//RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
	    	method.setConfig(requestConfig);
	    	method.setEntity(httpentity);
	        
			WebClientDevWrapper.wrapClient(httpClient);
			response = httpClient.execute( method, context);
			
			is=response.getEntity().getContent();
			
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String str = EntityUtils.toString(response.getEntity());
				map.put("data", str);
				map.put("returncode", "00000000");
				return map;
			} else {
				method.abort();
				map.put("returncode", "00000001");
				map.put("returnmsg", new Integer(response.getStatusLine().getStatusCode()).toString() );
			}
			
		} catch (Exception e) {
			map.put("returncode", "00000001");
			map.put("returnmsg", e.getMessage());
		} finally 
		{
			if(is!=null)
			{
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(method !=null )    
			{
				method.releaseConnection();    
			}
			if(response!=null)
			{
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
        return map;   
    }
    
    
//    public static HashMap sendPostZHitong(String path,Map<String,String>  params,String enc,String proxyhost,int proxyport)   
//    {
//    	Map<String,String[]> paramresult=new HashMap<String,String[]>();
//    	for(String key:params.keySet())
//    	{
//    		Object value=params.get(key);
//    		String[] valuearray=new String[]{value.toString()};
//			paramresult.put(key, valuearray);
//    	}
//    	return sendPostZHitong(path,null,paramresult,enc,proxyhost,proxyport);
//    }
//    
//    public static HashMap sendPostZHitong(String path, Map<String, String> headers, Map<String, String[]> params, String enc,String proxyhost,int proxyport)   
//    {
//    	HashMap map=new HashMap();
//    	map.put("returncode", "00000001");
//        map.put("returnmsg", "");
//        map.put("data", "");
//        HttpPost method =null;
//        CloseableHttpResponse response=null;
//        try 
//		{
//	        List<NameValuePair> formParams = new ArrayList<NameValuePair>();   
//	        if(params!=null)
//	        {
//	        	for(String key: params.keySet())   
//		        {
//	        		 Object value=params.get(key);
//	        		 if(value instanceof   String[] )
//	        		 {
//	        			 String valuearray[]=(String[])value;
//	        			 if(valuearray.length==1)
//	        			 {
//	        				 formParams.add(new BasicNameValuePair(key,valuearray[0])); 
//	        			 }else{
//	        				 for(int i=0;i<valuearray.length;i++)
//		        			 {
//		        				 formParams.add(new BasicNameValuePair(key+"["+i+"]",valuearray[i])); 
//		        			 }
//	        			 }
//	        		 }
//	        		 else
//	        		 {
//	        			 formParams.add(new BasicNameValuePair(key, (String) value));    
//	        		 }
//		        }
//	        }
//	        HttpEntity httpentity = new UrlEncodedFormEntity(formParams, enc);   
//	        method = new HttpPost(path);
//	        if(headers!=null && headers.size()>0)
//	        {
//	        	Header[] hds=new Header[]{};
//	 	        int i=0;
//	 	        for(String key:headers.keySet())
//	 	        {
//	 	        	hds[i++]=new BasicHeader(key,headers.get(key));
//	 	        }
//	 			method.setHeaders(hds);
//	        }
//	        
//	        HttpClientContext  context = HttpClientContext.create();
//			Builder builder = RequestConfig.custom();
//			if(proxyhost!=null && proxyport>0)
//			{
//				HttpHost proxy = new HttpHost(proxyhost,proxyport, "http");
//				builder.setProxy(proxy);
//			}
//			
//			RequestConfig requestConfig = builder.setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
//	    	method.setConfig(requestConfig);
//	    	//RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
//	    	method.setConfig(requestConfig);
//	    	method.setEntity(httpentity);
//	        
//			WebClientDevWrapper.wrapClient(httpClient);
//			response = httpClient.execute( method, context);
//			try{
//				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//					String str = EntityUtils.toString(response.getEntity());
//					map.put("data", str);
//					map.put("returncode", "00000000");
//					return map;
//				} else {
//					map.put("returncode", "00000001");
//					map.put("returnmsg", new Integer(response.getStatusLine().getStatusCode()).toString() );
//					return map;
//				}
//			}catch(Exception ex)
//			{
//				map.put("returncode", "00000001");
//				map.put("returnmsg", ex.getMessage());
//			}finally 
//			{
//				if(null!= method)    
//				{
//					method.releaseConnection();    
//				}
//				response.close();
//			}
//			
//			
//		} catch (ClientProtocolException e) {
//			map.put("returnmsg", e.getMessage());
//			e.printStackTrace();
//		} catch (IOException e) {
//			map.put("returnmsg", e.getMessage());
//			e.printStackTrace();
//		} finally 
//		{
//			if(method !=null )    
//			{
//				method.releaseConnection();    
//			}
//			if(response!=null)
//			{
//				try {
//					response.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//        return map;   
//    }
    
    
    public static HashMap sendPost(String path, Map<String, String> params)   
    {
    	return sendPost(path,params,"UTF-8");
    }
    
    
    public static HashMap sendPostForMultipart(String path, Map<String, Object> params, String enc)    
    {
    	HashMap map=new HashMap();
    	map.put("returncode", "00000001");
        map.put("returnmsg", "");
        map.put("data", "");
        HttpPost method =null;
        CloseableHttpResponse response=null;
        InputStream is=null;
    	try 
		{
	        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
	        for(Map.Entry<String, Object> entry : params.entrySet())   
	        {
	        		 if (entry.getValue() instanceof File)
	        		 {
	        			 builder.addPart("param3", new FileBody((File) entry.getValue()));  
	        		 }
	        		 else{
	        			 builder.addPart(entry.getKey(), new StringBody((String) entry.getValue(), Charset.forName(enc)));  
	        		 }
	        }
	        HttpEntity mentity =builder.build();
	        method = new HttpPost(path);
	        HttpContext context = HttpClientContext.create();
	    	RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
	    	method.setConfig(requestConfig);
	    	method.setEntity(mentity); 
			WebClientDevWrapper.wrapClient(httpClient);
			response = httpClient.execute(method,context);
			
			is = response.getEntity().getContent();
	    	
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String str = EntityUtils.toString(response.getEntity());
				map.put("data", str);
				map.put("returncode", "00000000");
				return map;
			} else {
				method.abort();
				map.put("returncode", "00000001");
				map.put("returnmsg", new Integer(response.getStatusLine().getStatusCode()).toString());
			}
		} catch (Exception e) {
			map.put("returncode", "00000001");
			map.put("returnmsg", e.getMessage());
		}finally 
		{
			if(is!=null)
			{
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(method !=null )    
			{
				method.releaseConnection();    
			}
			if(response!=null)
			{
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
    	return map; 
    }
    
    public static HashMap sendPostForMultipart(String path, Map<String, Object> params) throws Exception   
    {
    	return sendPostForMultipart(path, params,"UTF-8");
    }
    
	
	public static HashMap sendJson(String url,String json){
		return sendJson(url,json,null,0);
	}
	
	public static HashMap sendJson(String url,String json,String proxyhost,int proxyport){
		HashMap map=new HashMap();
    	map.put("returncode", "00000001");
        map.put("returnmsg", "");
        map.put("data", "");
		HttpPost method = new HttpPost(url);
		//post.setHeader("Accept", "application/json");
		method.setHeader("Content-Type","application/json");
		method.setHeader("charset",HTTP.UTF_8);
		//post.setHeader("Content-Type","application/json");
		CloseableHttpResponse  response =null;
		InputStream is=null;
		try {
			////logger.debug("json:"+json);
			StringEntity s = new StringEntity(json.toString(),"UTF-8");
			s.setContentEncoding("UTF-8");
			s.setContentType("application/json");
			method.setEntity(s);
			
			
			HttpClientContext  context = HttpClientContext.create();
			Builder builder = RequestConfig.custom();
			if(proxyport>0)
			{
				HttpHost proxy = new HttpHost(proxyhost,proxyport, "http");
				builder.setProxy(proxy);
			}
	    	RequestConfig requestConfig = builder.setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
	    	method.setConfig(requestConfig);
			
//	    	
//	    	org.apache.http.client.CookieStore cookieStore = new BasicCookieStore();
//	    	context.setCookieStore(cookieStore);
	    	
	    	
	    	response = httpClient.execute(method,context);
	    	
	    	is = response.getEntity().getContent();
	    	
			if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				String str = IOUtils.toString(new InputStreamReader(is, "UTF-8"));
				////logger.debug("recivehttp:"+str);
				map.put("data", str);
				map.put("returncode", "00000000");
			}
			else
			{
				method.abort();
				map.put("returncode", "00000001");
				map.put("returnmsg", new Integer(response.getStatusLine().getStatusCode()).toString());
			}
		}  catch (Exception e) {
			e.printStackTrace();
			map.put("returnmsg", e.getMessage());
		}finally 
		{
			if(is!=null)
			{
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(method !=null )    
			{
				method.releaseConnection();    
			}
			if(response!=null)
			{
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return map;
	}
	
	public static HashMap sendJson(String url,Map<String, String> params,String json) throws UnsupportedEncodingException{
		HashMap map=new HashMap();
    	map.put("returncode", "00000001");
        map.put("returnmsg", "");
        map.put("data", "");
		
		
		StringBuilder sb = new StringBuilder(url);
		sb.append('?');
		for (Map.Entry<String, String> entry : params.entrySet()) {
			sb.append(entry.getKey()).append('=')
			.append(URLEncoder.encode(entry.getValue(), "UTF-8"))
			.append('&');
		}
		sb.deleteCharAt(sb.length() - 1);
		
		HttpPost method = new HttpPost(sb.toString());
		CloseableHttpResponse  response =null;
		method.setHeader("Content-Type","application/json");
		method.setHeader("charset",HTTP.UTF_8);
		InputStream is=null;
		try {
			StringEntity s = new StringEntity(json.toString(),"UTF-8");
			s.setContentEncoding("UTF-8");
			s.setContentType("application/json");
			method.setEntity(s);
			
			HttpContext context = HttpClientContext.create();
	    	RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
	    	method.setConfig(requestConfig);
			response= httpClient.execute(method,context);
			
			is= response.getEntity().getContent();
	    	
			if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				String str = IOUtils.toString(new InputStreamReader(is, "UTF-8"));
				map.put("data", str);
				map.put("returncode", "00000000");
			}
			else
			{
				method.abort();
				map.put("returncode", "00000001");
				map.put("returnmsg", new Integer(response.getStatusLine().getStatusCode()).toString());
			}
			
		}catch (Exception e) {
			map.put("returncode", "00000001");
			map.put("returnmsg", e.getMessage());
		}finally 
		{
			if(is!=null)
			{
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(method !=null )    
			{
				method.releaseConnection();    
			}
			if(response!=null)
			{
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return map;
	}
	
//	
//	gzip
//	public static void sendHttpContent(String jsonStr) throws Exception {
//		// ///////////////压缩文本内容////////////////////
//		byte[] reqbytes = GzipUtils.encompressingGZipString(jsonStr);
//		BigDecimal bdec = new BigDecimal(jsonStr.getBytes().length).divide(new BigDecimal(1024)).setScale(2, RoundingMode.HALF_UP);
//		BigDecimal adec = new BigDecimal(reqbytes.length).divide(new BigDecimal(1024)).setScale(2, RoundingMode.HALF_UP);
//		System.out.println("压缩前的数据大小为：" + bdec + "KB,压缩后的数据大小为：" + adec + "KB");
//		// ///////////////压缩////////////////////
//		HttpClient client = new DefaultHttpClient();
//		// /////////////////////设置双向证书验证------自己在写测试时,可去先去掉///////////////
//		KeyStore keyStore = KeyStore.getInstance("PKCS12");
//		FileInputStream instream = new FileInputStream(new File("D:/ssl/client/client.p12"));
//		try {
//			keyStore.load(instream, "hymax1601sp".toCharArray());
//		} finally {
//			instream.close();
//		}
//
//		KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
//		FileInputStream instream2 = new FileInputStream(new File("D:/ssl/client/client.truststore"));
//		try {
//			trustStore.load(instream2, "max1601hysp".toCharArray());
//		} finally {
//			instream.close();
//		}
//		SSLSocketFactory socketFactory = new SSLSocketFactory(keyStore, "hymax1601sp", trustStore);
//		socketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
//		Scheme sch = new Scheme("https", 8443, socketFactory);
//		client.getConnectionManager().getSchemeRegistry().register(sch);
//		// ////////////////////设置双向证书验证------自己在写测试时,可去先去掉///////////////
//		// ///发送http 请求/////
//		HttpPost post = new HttpPost(JKURL);
//		post.setHeader("Content-Encoding", "gzip"); // 设置接受响应消息为gzip
//		ByteArrayEntity bae = new ByteArrayEntity(reqbytes);
//		post.setEntity(bae);
//		HttpResponse response = client.execute(post);
//		String resStr = GzipUtils.getGZipString(response);
//		System.out.println("回调内容:" + resStr);
//	}
//
//	
	
	public static HashMap sendXMl(String url,String xml){
		return sendXMl(url,xml,null,0);
	}
	
	public static HashMap sendXMl(String url,String xml,String proxyhost,int proxyport){
		
		HashMap map=new HashMap();
    	map.put("returncode", "00000001");
        map.put("returnmsg", "");
        map.put("data", "");
		HttpPost method = new HttpPost(url);
		//post.setHeader("Accept", "application/json");
		method.setHeader("Content-Type","text/xml");
		method.setHeader("charset",HTTP.UTF_8);
		//post.setHeader("Content-Type","application/json");
		CloseableHttpResponse  response =null;
		InputStream is = null;
		
		try {
			////logger.debug("json:"+json);
			StringEntity s = new StringEntity(xml.toString(),"UTF-8");
			s.setContentEncoding("UTF-8");
			s.setContentType("text/xml");
			method.setEntity(s);
			
			
			HttpClientContext  context = HttpClientContext.create();
			Builder builder = RequestConfig.custom();
			if(proxyport>0)
			{
				//url.startsWith("https")?"https":
				HttpHost proxy = new HttpHost(proxyhost,proxyport, "http");
				builder.setProxy(proxy);
			}
			
			//HttpClientContext  context = HttpClientContext.create();
	    	//RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
			RequestConfig requestConfig = builder.setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
	    	
			method.setConfig(requestConfig);
	    	
	    	response = httpClient.execute(method,context);
	    	
	    	is=response.getEntity().getContent();
	    	
			if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				String str = IOUtils.toString(new InputStreamReader(is, "UTF-8"));
				map.put("data", str);
				map.put("returncode", "00000000");
			}
			else
			{
				method.abort();
				map.put("returncode", "00000001");
				map.put("returnmsg", new Integer(response.getStatusLine().getStatusCode()).toString());
			}
		}catch (Exception e) {
			map.put("returncode", "00000001");
			map.put("returnmsg", e.getMessage());
		}finally 
		{
			if(is!=null)
			{
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(method !=null )    
			{
				method.releaseConnection();    
			}
			if(response!=null)
			{
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return map;
	}
	
	
	 public static String getFileMd5(String path) throws ClientProtocolException, IOException   
	 {
		 	HttpGet request = new HttpGet(path);
			WebClientDevWrapper.wrapClient(httpClient);
			HttpResponse response = httpClient.execute(request);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				return DigestUtils.md5Hex(response.getEntity().getContent());
			}
			return null;
	 }
	 
	
	
	
//	
//	public static void copy(InputStream fis, OutputStream fos,int bufferSize)
//			   throws IOException
//	{
////		   byte[] buf = new byte[bufferSize];
////		   int len = 0;
////		   while ((len = in.read(buf)) >= 0)
////		   {
////		      out.write(buf, 0, len);
////		   }
//		
//		int readLen = 0;
//		byte[] buf = new byte[1024];
//		while ((readLen = fis.read(buf)) != -1) {
//			//logger.debug(readLen);
//			fos.write(buf, 0, readLen);
//		}
//		fos.flush();
//		fos.close();
//		fis.close();
//		
//	}

	
	
	/*
	 * 进行md5加密生成指纹信息
	 */
	public static String getFileDigest(String path) throws Exception  
	{
		URL url = new URL(path);
		InputStream is = url.openStream();
		MessageDigest md = MessageDigest.getInstance("MD5");
		String digest = getDigest(is, md, 2048);
		return digest;
	}
	
	public static String getDigest(InputStream is, MessageDigest md, int byteArraySize) throws NoSuchAlgorithmException, IOException {
		md.reset();
		byte[] bytes = new byte[byteArraySize];
		int numBytes;
		while ((numBytes = is.read(bytes)) != -1) {
			md.update(bytes, 0, numBytes);
		}
		byte[] digest = md.digest();
		String result = new String(Hex.encodeHex(digest));
		return result;
	}
	
	
	public static HashMap downloadFile(String url, OutputStream target) throws ClientProtocolException, IOException{
		HashMap map=new HashMap();
		map.put("returncode", "11111111");
        map.put("returnmsg", "");
		HttpGet request = new HttpGet(url);
		WebClientDevWrapper.wrapClient(httpClient);
		try {
			HttpResponse response = httpClient.execute(request);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String dc  = Charset.defaultCharset().name();  
				HttpEntity entity = response.getEntity();
				if (entity != null) {
				        InputStream inputStream = entity.getContent();
				        IOUtils.copy(inputStream, target);
				}
				map.put("returncode", "00000000");
				return map;
			} else {
				map.put("returncode", "00000001");
				return map;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

	public static  void downloadAndSaveToFile(String url, File targetFile) throws ClientProtocolException, IOException {
		if(targetFile.exists())
		{
			targetFile.delete();
		}
	    OutputStream outputStream = new FileOutputStream(targetFile);
	    downloadFile(url, outputStream);
	    outputStream.close();
	}
	
	public static  void downloadAndSaveToFile(String url, String targetFile) throws ClientProtocolException, IOException {
		downloadAndSaveToFile(url,new File(targetFile));
	}
	
	
    public static void printHTTP(HttpServletRequest request) throws UnsupportedEncodingException{
    	printHTTP(request,"UTF-8");
    }
	
    
	public static void printHTTP(HttpServletRequest request,String encode) throws UnsupportedEncodingException{
		//String reqpath=saveHttp(request);
		
		if(logger.isDebugEnabled())
		{
			logger.debug("==========================================================================");
			logger.debug("HTTP-request info start");
			logger.debug("getCharacterEncoding:"+request.getCharacterEncoding());
			logger.debug("getContentType:"+request.getContentType());
			logger.debug("getRequestedSessionId:"+request.getRequestedSessionId());
		    logger.debug("getMethod:"+request.getMethod());
		    logger.debug("getPathInfo:"+request.getPathInfo());
		    logger.debug("getQueryString:"+request.getQueryString());
		    logger.debug("getRequestURI:"+request.getRequestURI());
		    logger.debug("getServerPort:"+request.getServerPort());
		    logger.debug("getRequestURL:"+request.getRequestURL().toString());
		    logger.debug("getScheme:"+request.getScheme());
		    logger.debug("getServerName:"+request.getServerName());
		    logger.debug("getContextPath:"+request.getContextPath());
		    logger.debug("getServletPath:"+request.getServletPath());
		    logger.debug("getRemoteAddr:"+request.getRemoteAddr());
		    logger.debug("getRemoteHost:"+request.getRemoteHost());
		    logger.debug("getRemotePort:"+request.getRemotePort());
		    logger.debug("getRemoteUser:"+request.getRemoteUser());
		    logger.debug("getRequestedSessionId:"+request.getRequestedSessionId());
		    logger.debug("HTTP-request info end");
		    
		    
		    logger.debug("HTTP-Header:start");
			request.setCharacterEncoding(encode);
			Enumeration headerNames = request.getHeaderNames();
			while (headerNames.hasMoreElements()) 
			{
				String headerName = (String) headerNames.nextElement();
				String headerValues = request.getHeader(headerName);
				logger.debug("HeaderName:"+headerName+"   HeaderValue:"+headerValues);
			}
			logger.debug("HTTP-Header:end");
			
			
			
			logger.debug("HTTP-Body:start");
			Enumeration paramNames = request.getParameterNames();
			while (paramNames.hasMoreElements()) 
			{
				String paramName = (String) paramNames.nextElement();
				String[] paramValues = request.getParameterValues(paramName);
				for(int i=0;i<paramValues.length;i++)
				{
					////logger.debug("ParamName:"+paramName+"   ParamValue:"+new String(paramValues[i].getBytes("iso-8859-1"),"gbk"));
					logger.debug("ParamName:"+paramName+"   ParamValue:"+paramValues[i]);
				}
			}
			logger.debug("HTTP-Body:end");
			
			
			logger.debug("HTTP-Session:start");
			
			if(request.getSession(false)!=null)
			{
				Enumeration en = request.getSession(false).getAttributeNames();
				while(en.hasMoreElements())
				{
					String str=(String) en.nextElement();
					logger.debug("ParamName:"+str+"   ParamValue:"+request.getSession().getAttribute(str));
				}
			}
			logger.debug("HTTP-Session:end");
			
			
			
			logger.debug("HTTP-Cookie:start");
			if(request.getCookies()!=null)
			{
				Cookie[] cookie = request.getCookies();
				for(Cookie c:cookie)
				{
					logger.debug("   Name:"+c.getName()+"  Secure:"+c.getSecure()+"   Value:"+c.getValue()+"  Comment:"+c.getComment()+"   MaxAge:"+c.getMaxAge()+"   Path:"+c.getPath()+"   Version:"+c.getVersion()+"   Domain:"+c.getDomain());
				}
			}
			
			logger.debug("HTTP-Cookie:end");
			logger.debug("==========================================================================");
		}
	}
	
	
	public static void printHTTP(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
//		//logger.debug("requestcharset:"+request.getCharacterEncoding());
//		//logger.debug("HTTP-Header:start");
		request.setCharacterEncoding("utf-8");
		
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) 
		{
			String headerName = (String) headerNames.nextElement();
			String headerValues = request.getHeader(headerName);
//			//logger.debug("HeaderName:"+headerValues+"   HeaderValue:"+headerValues);
		}
		//logger.debug("HTTP-Header:end");
		
		//logger.debug("HTTP-Body:start");
		Enumeration paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) 
		{
			String paramName = (String) paramNames.nextElement();
			String[] paramValues = request.getParameterValues(paramName);
			for(int i=0;i<paramValues.length;i++)
			{
				////logger.debug("ParamName:"+paramName+"   ParamValue:"+new String(paramValues[i].getBytes("iso-8859-1"),"gbk"));
				//logger.debug("ParamName:"+paramName+"   ParamValue:"+paramValues[i]);
			}
		}
		//logger.debug("HTTP-Body:end");
		
		
		//logger.debug("HTTP-Session:start");
		if(request.getSession(false)!=null)
		{
			Enumeration en = request.getSession(false).getAttributeNames();
			while(en.hasMoreElements())
			{
				String str=(String) en.nextElement();
				//logger.debug("ParamName:"+str+"   ParamValue:"+request.getSession().getAttribute(str));
			}
		}
		//logger.debug("HTTP-Session:end");
		
		//logger.debug("HTTP-Cookie:start");
		if(request.getCookies()!=null)
		{
			Cookie[] cookie = request.getCookies();
			for(Cookie c:cookie)
			{
				//logger.debug("   Name:"+c.getName()+"  Secure:"+c.getSecure()+"   Value:"+c.getValue()+"Comment:"+c.getComment()+"   MaxAge:"+c.getMaxAge()+"   Path:"+c.getPath()+"   Version:"+c.getVersion()+"   Domain:"+c.getDomain());
			}
			
		}
		//logger.debug("HTTP-Cookie:end");
	}
	
	

	public static  String getHTTP(HttpServletRequest request) throws UnsupportedEncodingException{
		return getHTTP(request,"UTF-8");
	}
	
	public static  String getHTTP(HttpServletRequest request,String ecode) throws UnsupportedEncodingException{
		StringBuffer sb=new StringBuffer("");
		sb.append("------------------------------------------------------------------\n");
		sb.append("method:"+request.getMethod()+"   charset:"+request.getCharacterEncoding());
		sb.append("HTTP-Header:start\n");
		request.setCharacterEncoding(ecode);
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) 
		{
			String headerName = (String) headerNames.nextElement();
			String headerValues = request.getHeader(headerName);
			sb.append("HeaderName:"+headerValues+"   HeaderValue:"+headerValues+"\n");
		}
		sb.append("HTTP-Header:end\n");
		
		sb.append("HTTP-Body:start\n");
		Enumeration paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) 
		{
			String paramName = (String) paramNames.nextElement();
			String[] paramValues = request.getParameterValues(paramName);
			for(int i=0;i<paramValues.length;i++)
			{
				////logger.debug("ParamName:"+paramName+"   ParamValue:"+new String(paramValues[i].getBytes("iso-8859-1"),"gbk"));
				sb.append("ParamName:"+paramName+"   ParamValue:"+paramValues[i]+"\n");
			}
		}
		sb.append("HTTP-Body:end"+"\n");
		sb.append("------------------------------------------------------------------\n");
		
		sb.append("HTTP-Session:start\n");
		Enumeration en = request.getSession().getAttributeNames();
		while(en.hasMoreElements())
		{
			String str=(String) en.nextElement();
			sb.append("ParamName:"+str+"   ParamValue:"+request.getSession().getAttribute(str)+"\n");
		}
		sb.append("HTTP-Session:end\n");
		return sb.toString();
	}
	
	public static  String getHTTPJSONString(HttpServletRequest request) throws Exception
	{
		return getHTTPJSONString(request,"UTF-8");
	}
	
	public static  String getHTTPJSONString(HttpServletRequest request,String ecode) throws Exception
	{
		ObjectMapper om=JsonHelper.getObjectMapperInstance();
		HashMap params=new HashMap();
		Enumeration paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) 
		{
			String paramName = (String) paramNames.nextElement();
			String[] paramValues = request.getParameterValues(paramName);
			for(int i=0;i<paramValues.length;i++)
			{
				////logger.debug("ParamName:"+paramName+"   ParamValue:"+new String(paramValues[i].getBytes("iso-8859-1"),"gbk"));
				String value=null;
				if(params.containsKey(paramName))
				{
					String aa=new String(paramValues[i].getBytes("ISO8859-1"),ecode);
					value=params.get(paramName)+aa;
				}else{
					value=new String(paramValues[i].getBytes("ISO8859-1"),ecode);
				}
				params.put(paramName,value);
			}
		}
		String param=om.writeValueAsString(params);
		return param;
	}
	
	
	
	
	public static void printHTTP(HttpServletRequest request,Logger log) throws UnsupportedEncodingException{
		log.error("HTTP-Header:start");
		request.setCharacterEncoding("gbk");
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) 
		{
			String headerName = (String) headerNames.nextElement();
			String headerValues = request.getHeader(headerName);
			log.error("HeaderName:"+headerValues+"   HeaderValue:"+headerValues);
		}
		log.error("HTTP-Header:end");
		
		log.error("HTTP-Body:start");
		Enumeration paramNames = request.getParameterNames();
		
		while (paramNames.hasMoreElements()) 
		{
			String paramName = (String) paramNames.nextElement();
			String[] paramValues = request.getParameterValues(paramName);
			for(int i=0;i<paramValues.length;i++)
			{
				////logger.debug("ParamName:"+paramName+"   ParamValue:"+new String(paramValues[i].getBytes("iso-8859-1"),"gbk"));
				log.error("ParamName:"+paramName+"   ParamValue:"+paramValues[i]);
			}
		}
		log.error("HTTP-Body:end");
	}
	
	public static  String genToken(HttpServletRequest request) {
		 String token=Guid.genRandom(20);
	   	 request.getSession().getServletContext().setAttribute("token",token);
	   	 return token;
	}
	
	public static boolean isInValidToken(HttpServletRequest request,
			String tokenreq) throws Exception {
		if (1 == 1) {
			return false;
		}
		// printHTTP(request);
		String token = (String) request.getSession().getServletContext()
				.getAttribute("token");
		// .getServletContext()
		if (token == null) {
			throw new Exception("token not exist on server");
		}
		if (token.equals(tokenreq)) {
			return true;
		}
		return false;
	}
	
	   
	public static String saveHttp(HttpServletRequest request)
	{
		try {
			String batch=Guid.getUniqueIdWithNaos();
			String reqpath="/logs/"+batch+".req.txt";
			SaveHttpRequest saverequest=new SaveHttpRequest(request);
			ObjectMapper om =JsonHelper.getObjectMapperInstance();
			if("POST".equals(request.getMethod()) && request.getContentLength()>0)
			{
				String datapath="/logs/"+batch+".data.txt";
				om.writeValue(new File(reqpath), saverequest);
				OutputStream output=new FileOutputStream(datapath);
				IOUtils.copy(request.getInputStream(), output);
				saverequest.setDatafilepath(datapath);
			}
			om.writeValue(new File(reqpath),saverequest);
			return reqpath;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static HashMap getHashMap(HttpServletRequest request) throws UnsupportedEncodingException 
	{
		Map<String, String[]> parameters = request.getParameterMap();
	    HashMap<String, String> reslutmap = new HashMap();
	    for(String paramName : parameters.keySet()) {
	    		String[] paramValues = parameters.get(paramName);
	        	StringBuffer valueStr=new StringBuffer();
	        	String[]  strarray=(String[]) paramValues;
	        	for (int i = 0; i < strarray.length; i++) {
	        		valueStr.append(strarray[i]);
	        		if(i!=strarray.length-1)
	        		{
	        			valueStr.append(",");
	        		}
				}
				reslutmap.put(paramName,new String(valueStr));
	    }
	    return reslutmap;
	}
	
	
	public static HashMap getHashMap(HttpServletRequest request,String enc) throws UnsupportedEncodingException 
	{
		return  getHashMap(request,"ISO8859-1",enc);
	}
	
	public static HashMap getHashMap(HttpServletRequest request,String sourceenc,String enc) throws UnsupportedEncodingException 
	{
		Map<String, String[]> parameters = request.getParameterMap();
	    HashMap<String, String> reslutmap = new HashMap();
	    for(String paramName : parameters.keySet()) {
	    		String[] paramValues = parameters.get(paramName);
	        	StringBuffer valueStr=new StringBuffer();
	        	String[]  strarray=(String[]) paramValues;
	        	for (int i = 0; i < strarray.length; i++) {
	        		valueStr.append(strarray[i]);
	        		if(i!=strarray.length-1)
	        		{
	        			valueStr.append(",");
	        		}
				}
				reslutmap.put(paramName,new String(valueStr.toString().getBytes(sourceenc),enc));
	    }
	    return reslutmap;
	}
	
	
	public static  HashMap sendBySavedFile(String reqfilepath) throws IOException
	{
		ObjectMapper om =JsonHelper.getObjectMapperInstance();
		SaveHttpRequest save=om.readValue(new File(reqfilepath), SaveHttpRequest.class);
		if("GET".equals(save.getMethod()))
		{
			return sendGet(save.getRedirectUrl(), save.getHeaders2(),null,"UTF-8");
		}else if("POST".equals(save.getMethod()))
		{
			if(save.getContentLength()==0)
			{
				InputStream is=new FileInputStream(save.getDatafilepath());
				return sendPost(save.getRedirectUrl(),save.getHeaders2(),is);
			}
			else
			{
				return sendPost(save.getRedirectUrl(),save.getHeaders2(),save.getParameterMap());
			}
		}
		return null;
	}
}