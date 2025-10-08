package com.hibernate.repository.impl;

import java.time.LocalDate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import com.hibernate.config.DatabaseConfig;
import com.hibernate.entity.DomesticPet;
import com.hibernate.entity.Pet;
import com.hibernate.repository.PetRepository;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;

public class PetRepositoryImpl implements PetRepository {
	private SessionFactory sessionFactory = DatabaseConfig.getSessionFactory();

	@Override
	public Double findAverageAgeOfPet() {
		try (Session session = sessionFactory.openSession()) {
			HibernateCriteriaBuilder hibernateCriteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Double> criteriaQuery = hibernateCriteriaBuilder.createQuery(Double.class);
			Root<Pet> root = criteriaQuery.from(Pet.class);
			// Treat root as DomesticPet so we can access birthDate
			Root<DomesticPet> domesticPetRoot = hibernateCriteriaBuilder.treat(root, DomesticPet.class);
			// Extract birth year and calculate age
			Expression<Integer> birthYear = hibernateCriteriaBuilder.function("year", Integer.class, domesticPetRoot.get("birthDate"));
			Expression<Integer> age = hibernateCriteriaBuilder.diff(LocalDate.now().getYear(), birthYear);
			// Select average age
			criteriaQuery.select(hibernateCriteriaBuilder.avg(age));
			return session.createQuery(criteriaQuery).getSingleResultOrNull();
		}
	}

}


