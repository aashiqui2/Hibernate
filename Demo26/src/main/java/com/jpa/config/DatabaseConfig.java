package com.jpa.config;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DatabaseConfig {
	private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = buildEntityManagerFactory();

	private DatabaseConfig() {

	}

	private static EntityManagerFactory buildEntityManagerFactory() {
		return Persistence.createEntityManagerFactory("Petistaan");
	}

	public static EntityManagerFactory getEntityManagerFactory() {
		return ENTITY_MANAGER_FACTORY;
	}
}
