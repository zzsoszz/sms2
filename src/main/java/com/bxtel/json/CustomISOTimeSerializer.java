package com.bxtel.json;

import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;


public class CustomISOTimeSerializer extends JsonSerializer<Date> {  
    @Override  
    public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {  
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");  
        String formattedDate = formatter.format(value);  
        StringBuffer toFix =new StringBuffer(formattedDate);
        toFix.insert(toFix.length()-2, ":");
        jgen.writeString(toFix.toString());
    }
}