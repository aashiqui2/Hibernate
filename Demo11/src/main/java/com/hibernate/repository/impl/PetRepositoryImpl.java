package com.hibernate.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hibernate.config.DatabaseConfig;
import com.hibernate.entity.Pet;
import com.hibernate.repository.PetRepository;

public class PetRepositoryImpl implements PetRepository {
	private SessionFactory sessionFactory = DatabaseConfig.getSessionFactory();

	@Override
	public Pet findPet(int petId) {
		try (Session session = sessionFactory.openSession()) {
			return session.get(Pet.class, petId);
		}
	}

}
