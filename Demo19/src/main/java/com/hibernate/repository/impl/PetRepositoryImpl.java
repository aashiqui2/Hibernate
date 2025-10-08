package com.hibernate.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hibernate.config.DatabaseConfig;
import com.hibernate.entity.Pet;
import com.hibernate.repository.PetRepository;

public class PetRepositoryImpl implements PetRepository {
	private SessionFactory sessionFactory = DatabaseConfig.getSessionFactory();

	@Override
	public Pet findPet(int petId) {
		String hql = "SELECT p FROM Pet p JOIN FETCH p.owner WHERE p.id = :petId"; //JOIN FETCH p.owner N+1
		try (Session session = sessionFactory.openSession()) {
			return session.createSelectionQuery(hql, Pet.class)
					.setParameter("petId", petId)
					.getSingleResultOrNull();
		}
	} 

	@Override
	public List<Pet> findAllPets() {
		String hql = "SELECT p FROM Pet p JOIN FETCH p.owner"; //JOIN FETCH p.owner N+1
		try (Session session = sessionFactory.openSession()) {
			return session.createSelectionQuery(hql, Pet.class).getResultList();
		}
	}
}


