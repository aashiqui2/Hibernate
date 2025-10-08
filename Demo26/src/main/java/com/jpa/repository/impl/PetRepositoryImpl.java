package com.jpa.repository.impl;

import com.jpa.config.DatabaseConfig;
import com.jpa.entity.Pet;
import com.jpa.repository.PetRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;


public class PetRepositoryImpl implements PetRepository {
	private EntityManagerFactory entityManagerFactory = DatabaseConfig.getEntityManagerFactory();

	@Override
	public Pet findPet(int petId) {
		try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
			return entityManager.find(Pet.class, petId);
		}
	}

	@Override
	public Double findAverageAgeOfPet() {
		String jpql = "SELECT AVG(YEAR(CURRENT_DATE()) - YEAR(p.birthDate)) FROM Pet p";
		try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
			return entityManager.createQuery(jpql, Double.class).getSingleResult();
		}
	}
}
