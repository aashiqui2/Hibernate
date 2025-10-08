package com.hibernate.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.hibernate.entity.DomesticPet;
import com.hibernate.entity.Owner;
import com.hibernate.entity.Pet;
import com.hibernate.entity.WildPet;

public class DatabaseConfig {
	private static final SessionFactory SESSION_FACTORY = buildSessionFactory();

	private DatabaseConfig() {

	}

	private static SessionFactory buildSessionFactory() {
		Configuration configuration = new Configuration()
				.addAnnotatedClass(Owner.class)
				.addAnnotatedClass(Pet.class)
				.addAnnotatedClass(DomesticPet.class)
				.addAnnotatedClass(WildPet.class);
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		return configuration.buildSessionFactory(serviceRegistry);
	}

	public static SessionFactory getSessionFactory() {
		return SESSION_FACTORY;
	}
}
