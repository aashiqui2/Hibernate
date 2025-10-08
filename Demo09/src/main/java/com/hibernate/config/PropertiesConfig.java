package com.hibernate.config;

import java.util.Properties;

import com.hibernate.exception.InternalServiceException;

import java.util.List;
import java.io.*;

public class PropertiesConfig {
	private static final Properties PROPERTIES = new Properties();
	private static final PropertiesConfig PROPERTIES_CONFIG = new PropertiesConfig();
	
	private PropertiesConfig() {
		List<String> filesToLoad = List.of("hibernate.properties", "messages.properties");
		filesToLoad.forEach(fileToLoad -> {
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileToLoad);
			if (inputStream != null) {
				try {
					PROPERTIES.load(inputStream);
				} catch (IOException e) {
					throw new InternalServiceException(e.getMessage());
				}
			} else {
				throw new InternalServiceException("Failed to load : " + fileToLoad);
			}
		});
	}

	public static PropertiesConfig getInstance() {
		return PROPERTIES_CONFIG;
	}
	public String getProperty(String key) {
		return PROPERTIES.getProperty(key);
	}

}
