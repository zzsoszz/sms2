package dinamica.util;
import java.util.ArrayList;

import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.web.PortResolver;
import org.springframework.security.web.savedrequest.SavedCookie;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonIgnore;
public class SaveHttpRequest {
		
		public SaveHttpRequest()
		{
			
		}
	  	protected static final Log logger = LogFactory.getLog(SaveHttpRequest.class);
	    private static final String HEADER_IF_NONE_MATCH = "If-None-Match";
	    private static final String HEADER_IF_MODIFIED_SINCE = "If-Modified-Since";
	    
	    //~ Instance fields ================================================================================================

	    private  ArrayList<SavedCookie> cookies = new ArrayList<SavedCookie>();
	    private  ArrayList<Locale> locales = new ArrayList<Locale>();
	    private  Map<String, List<String>> headers = new TreeMap<String, List<String>>(String.CASE_INSENSITIVE_ORDER);
	    private  Map<String, String> headers2 = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
	    private  Map<String, String[]> parameters = new TreeMap<String, String[]>(String.CASE_INSENSITIVE_ORDER);
	    private  String contextPath;
	    private  String method;
	    private  String pathInfo;
	    private  String queryString;
	    private  String requestURI;
	    private  String requestURL;
	    private  String scheme;
	    private  String serverName;
	    private  String servletPath;
	    private  int serverPort;
	    public String datafilepath;
	    public String contentType;
	    private int contentLength;
	    private String characterEncoding;
	    //~ Constructors ===================================================================================================
	    
		@SuppressWarnings("unchecked")
	    public SaveHttpRequest(HttpServletRequest request) {
			
			
			
	        Assert.notNull(request, "Request required");
	        // Cookies
	        Cookie[] cookies = request.getCookies();

	        if (cookies != null) {
	            for (Cookie cookie : cookies) {
	                this.addCookie(cookie);
	            }
	        }
	        
	        // Headers
	        Enumeration<String> names = request.getHeaderNames();

	        while (names.hasMoreElements()) {
	            String name = names.nextElement();
	            // Skip If-Modified-Since and If-None-Match header. SEC-1412, SEC-1624.
	            if (HEADER_IF_MODIFIED_SINCE.equalsIgnoreCase(name) || HEADER_IF_NONE_MATCH.equalsIgnoreCase(name)) {
	                continue;
	            }
	            Enumeration<String> values = request.getHeaders(name);
	            while (values.hasMoreElements()) {
	                this.addHeader(name, values.nextElement());
	            }
	            String value=request.getHeader(name);
	            this.addHeader2(name,value);
	        }
	        
	        // Locales
	        Enumeration<Locale> locales = request.getLocales();

	        while (locales.hasMoreElements()) {
	            Locale locale = (Locale) locales.nextElement();
	            this.addLocale(locale);
	        }

	        // Parameters
	        Map<String, String[]> parameters = request.getParameterMap();

	        for(String paramName : parameters.keySet()) {
	            Object paramValues = parameters.get(paramName);
	            if (paramValues instanceof String[]) {
	                this.addParameter(paramName, (String[]) paramValues);
	            } else {
	                if (logger.isWarnEnabled()) {
	                    logger.warn("ServletRequest.getParameterMap() returned non-String array");
	                }
	            }
	        }

	        // Primitives
	        this.method = request.getMethod();
	        this.pathInfo = request.getPathInfo();
	        this.queryString = request.getQueryString();
	        this.requestURI = request.getRequestURI();
	        this.serverPort = request.getServerPort();
	        this.requestURL = request.getRequestURL().toString();
	        this.scheme = request.getScheme();
	        this.serverName = request.getServerName();
	        this.contextPath = request.getContextPath();
	        this.servletPath = request.getServletPath();
	        this.contentType=request.getContentType();
	        this.contentLength=request.getContentLength();
	        this.characterEncoding=request.getCharacterEncoding();
	    }
		

	    
	    //~ Methods ========================================================================================================

	    private void addCookie(Cookie cookie) {
	        cookies.add(new SavedCookie(cookie));
	    }

	    private void addHeader(String name, String value) {
	        List<String> values = headers.get(name);

	        if (values == null) {
	            values = new ArrayList<String>();
	            headers.put(name, values);
	        }

	        values.add(value);
	    }

	    
	    public String getCharacterEncoding() {
			return characterEncoding;
		}


		public void setCharacterEncoding(String characterEncoding) {
			this.characterEncoding = characterEncoding;
		}



		private void addHeader2(String name, String value) {
	        headers2.put(name, value);
	    }
	    
	    
	    
	    public Map<String, String> getHeaders2() {
			return headers2;
		}
	    
	    
		private void addLocale(Locale locale) {
	        locales.add(locale);
	    }

	    private void addParameter(String name, String[] values) {
	        parameters.put(name, values);
	    }

	    /**
	     * Determines if the current request matches the <code>DefaultSavedRequest</code>.
	     * <p>
	     * All URL arguments are considered but not cookies, locales, headers or parameters.
	     *
	     * @param request the actual request to be matched against this one
	     * @param portResolver used to obtain the server port of the request
	     * @return true if the request is deemed to match this one.
	     *
	     */
	    public boolean doesRequestMatch(HttpServletRequest request, PortResolver portResolver) {

	        if (!propertyEquals("pathInfo", this.pathInfo, request.getPathInfo())) {
	            return false;
	        }

	        if (!propertyEquals("queryString", this.queryString, request.getQueryString())) {
	            return false;
	        }

	        if (!propertyEquals("requestURI", this.requestURI, request.getRequestURI())) {
	            return false;
	        }

	        if (!"GET".equals(request.getMethod()) && "GET".equals(method)) {
	            // A save GET should not match an incoming non-GET method
	            return false;
	        }

	        if (!propertyEquals("serverPort", Integer.valueOf(this.serverPort), Integer.valueOf(portResolver.getServerPort(request)))) {
	            return false;
	        }

	        if (!propertyEquals("requestURL", this.requestURL, request.getRequestURL().toString())) {
	            return false;
	        }

	        if (!propertyEquals("scheme", this.scheme, request.getScheme())) {
	            return false;
	        }

	        if (!propertyEquals("serverName", this.serverName, request.getServerName())) {
	            return false;
	        }

	        if (!propertyEquals("contextPath", this.contextPath, request.getContextPath())) {
	            return false;
	        }

	        return propertyEquals("servletPath", this.servletPath, request.getServletPath());

	    }

	    public String getContextPath() {
	        return contextPath;
	    }

	    public List<Cookie> getCookies() {
	        List<Cookie> cookieList = new ArrayList<Cookie>(cookies.size());

	        for (SavedCookie savedCookie : cookies) {
	            cookieList.add(savedCookie.getCookie());
	        }

	        return cookieList;
	    }

	    /**
	     * Indicates the URL that the user agent used for this request.
	     *
	     * @return the full URL of this request
	     */
	    @JsonIgnore
	    public String getRedirectUrl() {
	        return UrlUtils.buildFullRequestUrl(scheme, serverName, serverPort, requestURI, queryString);
	    }

	    public List<Locale> getLocales() {
	        return locales;
	    }

	    public String getMethod() {
	        return method;
	    }

	    public Map<String, String[]> getParameterMap() {
	        return parameters;
	    }

	    public Collection<String> getParameterNames() {
	        return parameters.keySet();
	    }

	    public String[] getParameterValues(String name) {
	        return parameters.get(name);
	    }

	    public String getPathInfo() {
	        return pathInfo;
	    }

	    public String getQueryString() {
	        return (this.queryString);
	    }

	    public String getRequestURI() {
	        return (this.requestURI);
	    }

	    public String getRequestURL() {
	        return requestURL;
	    }

	    public String getScheme() {
	        return scheme;
	    }

	    public String getServerName() {
	        return serverName;
	    }

	    public int getServerPort() {
	        return serverPort;
	    }

	    public String getServletPath() {
	        return servletPath;
	    }
        
	    public String getDatafilepath() {
			return datafilepath;
		}


		public void setDatafilepath(String datafilepath) {
			this.datafilepath = datafilepath;
		}


		private boolean propertyEquals(String log, Object arg1, Object arg2) {
	        if ((arg1 == null) && (arg2 == null)) {
	            if (logger.isDebugEnabled()) {
	                logger.debug(log + ": both null (property equals)");
	            }

	            return true;
	        }

	        if (arg1 == null || arg2 == null) {
	            if (logger.isDebugEnabled()) {
	                logger.debug(log + ": arg1=" + arg1 + "; arg2=" + arg2 + " (property not equals)");
	            }

	            return false;
	        }

	        if (arg1.equals(arg2)) {
	            if (logger.isDebugEnabled()) {
	                logger.debug(log + ": arg1=" + arg1 + "; arg2=" + arg2 + " (property equals)");
	            }

	            return true;
	        } else {
	            if (logger.isDebugEnabled()) {
	                logger.debug(log + ": arg1=" + arg1 + "; arg2=" + arg2 + " (property not equals)");
	            }

	            return false;
	        }
	    }

	    public String toString() {
	        return "DefaultSavedRequest[" + getRedirectUrl() + "]";
	    }


		public String getContentType() {
			return contentType;
		}


		public void setContentType(String contentType) {
			this.contentType = contentType;
		}


		public int getContentLength() {
			return contentLength;
		}


		public void setContentLength(int contentLength) {
			this.contentLength = contentLength;
		}
	    
	    
}
