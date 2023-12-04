package com.userlist.constants;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFileLoader {

	static Properties properties = new Properties();
	
	
	static boolean isLoaded = false;
	public static void loadProperties() {
        
		
        try (InputStream input = PropertiesFileLoader.class.getResourceAsStream("/application-dev.properties")) {
            properties.load(input);
            isLoaded = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 
	
	public static String getFile(String fileName) {
		String filePath = fileName;
		InputStream inputStreamFile;
		byte[] bytes;
		try {
			inputStreamFile = PropertiesFileLoader.class.getResourceAsStream(filePath);
			bytes = new byte[inputStreamFile.available()];
			inputStreamFile.read(bytes);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return new String(bytes);
	}
	
	
	public static String getAudience() {
		if(!isLoaded) {
			loadProperties();
		}
		 String value = properties.getProperty("appAudience");
	      System.out.println("Value: " + value);
	      if(value == null) {
	    	  return "";
	      }
	      return value;
	}
	
}
