package dinamica.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.bxtel.json.CustomAnyTimeDeSerializer;
import com.bxtel.json.CustomTimeSerializer;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/*
 * http://wiki.fasterxml.com/JacksonHowToCustomSerializers
 */

public class JsonHelper {
	
	public static ObjectMapper ominstance=null;
	
	public static ObjectMapper getObjectMapperInstance()
	{
		if(ominstance==null )
		{
			ominstance=new ObjectMapper();
			ominstance.configure(Feature.ALLOW_SINGLE_QUOTES, true);
			ominstance.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			SimpleModule testModule = new SimpleModule("MyModule", new Version(1, 0, 0, null));
			testModule.addDeserializer(java.util.Date.class,new  CustomAnyTimeDeSerializer());
			testModule.addSerializer(java.util.Date.class,new  CustomTimeSerializer());
			ominstance.registerModule(testModule);
		}
		return ominstance;
	}
	
	public static void main(String[] args) throws ParseException {
		ObjectMapper om = JsonHelper.getObjectMapperInstance();
	}
}
