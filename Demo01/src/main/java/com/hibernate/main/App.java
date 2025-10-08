package com.hibernate.main;

import org.hibernate.cfg.Configuration;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.awt.Desktop;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Properties;

import org.h2.tools.Server;
import org.hibernate.SessionFactory;
import org.h2.tools.Server;

import com.hibernate.entity.Owner;

public class App {
	public static void main(String[] args) {

		// Auto Load hibernate.cfg.xml from classpath (src/main/resources) 
		// no need of file name like this new Configuration().configure("hibernate.cfg.xml"); 
		/*
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		*/
		
		

		// Java Configuration for default hibernate.properties file
		/*
      	Configuration configuration = new Configuration()
				                     .addAnnotatedClass(Owner.class);
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				                     .applySettings(configuration.getProperties()).build();
		configuration.buildSessionFactory(serviceRegistry);
		*/
		
	
		
		// Java Configuration for custom h2.properties file	
		// Start H2 Console in same JVM as Hibernate
				
		try {
		    Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
		    String url = "http://localhost:8082";
            System.out.println("H2 Console started at " + url);
		    // Try to open in default browser
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI(url));
            } else {
                System.out.println("Desktop not supported. Please open manually: " + url);
            }
		} catch (SQLException | IOException | URISyntaxException e) {
		    e.printStackTrace();
		}

		// Hibernate init...
		
		String profile = "h2"; 
		String fileName = profile.equals("mysql") ? "mysql.properties" : "h2.properties";
		
		// class loader of a specific class (App.class.getClassLoader()).
		//Properties props = new Properties();
		//try (InputStream input = App.class.getClassLoader().getResourceAsStream(fileName)) {
		//    props.load(input);
		//} catch(Exception e) {
		//    e.printStackTrace();
		//}
		
		// context class loader of the current thread.
		Properties props = new Properties();
		try (InputStream input = Thread.currentThread()
		        .getContextClassLoader()
		        .getResourceAsStream(fileName)) {
		    props.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}


		Configuration configuration = new Configuration()
		        .addAnnotatedClass(Owner.class)
		        .setProperties(props);

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
		        .applySettings(configuration.getProperties()).build();

		configuration.buildSessionFactory(serviceRegistry);
		
		
	}
}
