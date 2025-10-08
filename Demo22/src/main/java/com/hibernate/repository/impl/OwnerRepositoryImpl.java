package com.hibernate.repository.impl;

import java.util.Objects;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hibernate.config.DatabaseConfig;
import com.hibernate.entity.Owner;
import com.hibernate.entity.Pet;
import com.hibernate.repository.OwnerRepository;

public class OwnerRepositoryImpl implements OwnerRepository {
	private SessionFactory sessionFactory = DatabaseConfig.getSessionFactory();

	@Override
	public void saveOwner(Owner owner) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			if (session.contains(owner)) {
				System.out.println("Entity is in PERSISTENT state with id " + session.getIdentifier(owner));
			} else {
				System.out.println("Entity is in TRANSIENT state.");
			}
			session.persist(owner);
			if (session.contains(owner)) {
				System.out.println("Entity is in PERSISTENT state with id " + session.getIdentifier(owner));
			} else {
				System.out.println("Entity is in TRANSIENT state.");
			}
			transaction.commit();
		}
	}

	@Override
	public Owner findOwner(int ownerId) {
		try (Session session = sessionFactory.openSession()) {
			Owner owner = session.get(Owner.class, ownerId);
			if (Objects.nonNull(owner)) {
				// Handling a single entity reference (OneToOne)
				// Unwraps the proxy object (if owner.getPet() was lazily loaded)
				// and returns the actual Pet entity instance (concrete class).
				// Does NOT load a collection, just ensures you get the real object.
				Pet pet = Hibernate.unproxy(owner.getPet(), Pet.class);
				owner.setPet(pet);
			}
			return owner;
		}
	}

	@Override
	public void updatePetDetailsUsingPersistentState(int ownerId, String petName) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			Owner owner = session.get(Owner.class, ownerId);
			if (Objects.nonNull(owner)) {
				owner.getPet().setName(petName);
				if (session.contains(owner)) {
					System.out.println("Entity is in PERSISTENT state with id " + session.getIdentifier(owner));
				} else {
					System.out.println("Entity is in DETACHED state.");
				}
			}
			transaction.commit();
		}
	}

	@Override
	public void updatePetDetailsUsingDetachedState(int ownerId, String petName) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			Owner owner = session.get(Owner.class, ownerId);
			if (Objects.nonNull(owner)) {
				owner.getPet().setName(petName);
				if (session.contains(owner)) {
					System.out.println("Entity is in PERSISTENT state with id " + session.getIdentifier(owner));
				} else {
					System.out.println("Entity is in DETACHED state.");
				}
				session.evict(owner);
				if (session.contains(owner)) {
					System.out.println("Entity is in PERSISTENT state with id " + session.getIdentifier(owner));
				} else {
					System.out.println("Entity is in DETACHED state.");
				}
				session.merge(owner);
			}
			transaction.commit();
		}
	}

	@Override
	public void deleteOwner(int ownerId) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			Owner owner = session.get(Owner.class, ownerId);
			if (Objects.nonNull(owner)) {
				if (session.contains(owner)) {
					System.out.println("Entity is in PERSISTENT state with id " + session.getIdentifier(owner));
				} else {
					System.out.println("Entity is in DELETED state.");
				}
				session.remove(owner);
				if (session.contains(owner)) {
					System.out.println("Entity is in PERSISTENT state with id " + session.getIdentifier(owner));
				} else {
					System.out.println("Entity is in DELETED state.");
				}
			}
			transaction.commit();
		}
	}
}
