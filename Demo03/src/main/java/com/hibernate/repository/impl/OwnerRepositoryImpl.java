package com.hibernate.repository.impl;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hibernate.config.DatabaseConfig;
import com.hibernate.entity.Owner;
import com.hibernate.repository.OwnerRepository;


public class OwnerRepositoryImpl implements OwnerRepository {
	private SessionFactory sessionFactory = DatabaseConfig.getSessionFactory();
	
	@Override
	public void saveOwner(Owner owner) {
		try(Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			System.out.println("Before Saving "+ owner.getId());
			session.persist(owner);
			System.out.println("After Saving "+ owner.getId());
			transaction.commit();
			System.out.println("After Commiting "+ owner.getId());
		}
	}

	@Override
	public Owner findOwner(int ownerId) {
		try(Session session = sessionFactory.openSession()) {
			return session.get(Owner.class, ownerId);
		}
	}

	@Override
	public void updatePetDetails(int ownerId, String petName) {
		try(Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			var owner = session.get(Owner.class, ownerId);
			owner.setPetName(petName);
			session.merge(owner);
			transaction.commit();
		}
	}

	@Override
	public void deleteOwner(int ownerId) {
		try(Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			var owner = session.get(Owner.class, ownerId);
			session.remove(owner);
			transaction.commit();
		}
	}
}

