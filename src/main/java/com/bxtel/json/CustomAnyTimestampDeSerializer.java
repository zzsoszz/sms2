package com.bxtel.json;

import java.io.IOException;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CustomAnyTimestampDeSerializer extends JsonDeserializer<Timestamp> {
	
	private SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Override
	public Timestamp deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		Timestamp date = null;
		try {
			if(jp.getText().trim().length()==10)
			{
				date = new Timestamp(sdf1.parse(jp.getText().trim()).getTime());
			}else	if(jp.getText().trim().length()==19)
			{
				date = new Timestamp( sdf2.parse(jp.getText().trim()).getTime());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}

/*
 * 
 * http://blog.csdn.net/rain_yuan/article/details/7000307
MyJsonProvider(){

ObjectMapper mapper = JsonHelper.getObjectMapperInstance()();
// Enable human readable date format
SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
mapper.getDeserializationConfig().setDateFormat(sdf);
mapper.getSerializationConfig().setDateFormat(sdf);
// Enable JAXB annotation, with Jackson annotation being the preferred one.
AnnotationIntrospector primary = new JacksonAnnotationIntrospector();
AnnotationIntrospector secondary = new JaxbAnnotationIntrospector();
AnnotationIntrospector introspector = new AnnotationIntrospector.Pair(primary, secondary);
mapper.getDeserializationConfig().setAnnotationIntrospector(introspector);
mapper.getSerializationConfig().setAnnotationIntrospector(introspector);
super(mapper);}

objectMapper.getSerializationConfig().setDateFormat(myDateFormat);  
 
objectMapper.getSerializationConfig().setDateFormat(myDateFormat); 

http://ljhzzyx.blog.163.com/blog/static/38380312201373023926692/

*/