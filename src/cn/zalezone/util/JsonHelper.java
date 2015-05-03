package cn.zalezone.util;

import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.codehaus.jackson.map.ObjectMapper;

public class JsonHelper {
	
	private static ObjectMapper mapper = null;
	
	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static String ObjectToJson(Object obj) throws Exception{
		if(mapper==null){
			mapper = initMapper();
		}
		StringWriter sw = new StringWriter();
        mapper.writeValue(sw, obj);
        String json = sw.toString();
		return json;
	}

	
	public static <T> T JsonToObject(String json, Class<T> clazz) throws Exception{
		if(mapper==null){
			mapper = initMapper();
		}
//		Class c =  Class.forName("com.jeff.test.json.ClassInfo");
        T obj =  mapper.readValue(json, clazz);
		return obj;
	}
	
	public static Object JsonToObject(String json, String classFullName) throws Exception{
		if(mapper==null){
			mapper = initMapper();
		}
		Class clazz =  Class.forName(classFullName);
        Object obj =  mapper.readValue(json, clazz);
		return obj;
	}

	
	
	private static ObjectMapper initMapper(){
		ObjectMapper mapper = new ObjectMapper();
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		dateFormat.setTimeZone(TimeZone.getDefault());
//		mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
		mapper.getSerializationConfig().setDateFormat(dateFormat);
		mapper.getDeserializationConfig().setDateFormat(dateFormat);
		return mapper;
	}

}
