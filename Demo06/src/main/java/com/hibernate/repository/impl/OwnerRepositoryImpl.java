package com.hibernate.repository.impl;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hibernate.config.DatabaseConfig;
import com.hibernate.entity.Owner;
import com.hibernate.entity.OwnerPetPrimaryKey;
import com.hibernate.repository.OwnerRepository;


public class OwnerRepositoryImpl implements OwnerRepository {
	private SessionFactory sessionFactory = DatabaseConfig.getSessionFactory();
	
	@Override
	public void saveOwner(Owner owner) {
		try(Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			session.persist(owner);
			transaction.commit();
		}
	}

	@Override
	public Owner findOwner(OwnerPetPrimaryKey primaryKey) {
		try(Session session = sessionFactory.openSession()) {
			return session.get(Owner.class, primaryKey);
		}
	}

	@Override
	public void updatePetDetails(OwnerPetPrimaryKey primaryKey, String petName) {
		try(Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			var owner = session.get(Owner.class, primaryKey);
			owner.setPetName(petName);
			session.merge(owner);
			transaction.commit();
		}
	}

	@Override
	public void deleteOwner(OwnerPetPrimaryKey primaryKey) {
		try(Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			var owner = session.get(Owner.class, primaryKey);
			session.remove(owner);
			transaction.commit();
		}
	}
}


