package com.hibernate.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hibernate.config.DatabaseConfig;
import com.hibernate.entity.Owner;
import com.hibernate.repository.OwnerRepository;

public class OwnerRepositoryImpl implements OwnerRepository {
	private SessionFactory sessionFactory = DatabaseConfig.getSessionFactory();

	@Override
	public Owner findOwner(int ownerId) {
		String hql = "SELECT o FROM Owner o WHERE o.id = :ownerId";
		try (Session session = sessionFactory.openSession()) {
			return session.createSelectionQuery(hql, Owner.class)
					.setParameter("ownerId", ownerId)
					.getSingleResultOrNull();
		}
	}
	
	@Override
	public Owner findOwnerWithPet(int ownerId) {
		String hql = "SELECT o FROM Owner o JOIN FETCH o.pet WHERE o.id = :ownerId";
		try (Session session = sessionFactory.openSession()) {
			return session.createSelectionQuery(hql, Owner.class)
					.setParameter("ownerId", ownerId)
					.getSingleResultOrNull();
		}
	}

	@Override
	public List<Owner> findAllOwners() {
		String hql = "SELECT o FROM Owner o";
		try (Session session = sessionFactory.openSession()) {
			return session.createSelectionQuery(hql, Owner.class).getResultList();
		}
	}
	
	@Override
	public List<Owner> findAllOwnersWithPet() {
		String hql = "SELECT o FROM Owner o JOIN FETCH o.pet"; //JOIN FETCH o.pet N+1
		try (Session session = sessionFactory.openSession()) {
			return session.createSelectionQuery(hql, Owner.class).getResultList();
		}
	}
}


