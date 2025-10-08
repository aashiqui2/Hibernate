package com.hibernate.repository.impl;

import java.util.Objects;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;

import com.hibernate.config.DatabaseConfig;
import com.hibernate.entity.Owner;
import com.hibernate.repository.OwnerRepository;

public class OwnerRepositoryImpl implements OwnerRepository {
	private SessionFactory sessionFactory = DatabaseConfig.getSessionFactory();

	@Override
	public void saveOwner(Owner owner) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			session.persist(owner);
			transaction.commit();
		}
	}

	@Override
	public Owner findOwner(int ownerId) {
		try (Session session = sessionFactory.openSession()) {
			return session.get(Owner.class, ownerId);
		}
	}

	@Override
	public Owner findOwnerWithPet(int ownerId) {
		try (Session session = sessionFactory.openSession()) {
			Owner owner = session.get(Owner.class, ownerId);
			if (Objects.nonNull(owner)) {
				Hibernate.initialize(owner.getPet());
			}
			return owner;
		}
	}

	@Override
	public void updatePetDetails(int ownerId, String petName) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			Owner owner = session.get(Owner.class, ownerId);
			owner.getPet().setName(petName);
			session.merge(owner);
			transaction.commit();
		}
	}

	@Override
	public void deleteOwner(int ownerId) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			Owner owner = session.get(Owner.class, ownerId);
			session.remove(owner);
			transaction.commit();
		}
	}
}
