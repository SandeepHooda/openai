package com.sandeep.AI.Util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


public class SCV {
	
	//@Value("${openai.api.key}")
    private String apiKey;
    
    public static final String OPEN_AI_API_KEY = "openai.api.key";
    public static final String DB_URL="db.url";
    public static final String DB_DRIVER="db.driverClassName";
    public static final String DB_USER="db.username";
    public static final String DB_PWD="db.password";
  
    
	
	public static String getValue(String key) {
		String value = System.getenv(key);
		return value;
		
	}

}
