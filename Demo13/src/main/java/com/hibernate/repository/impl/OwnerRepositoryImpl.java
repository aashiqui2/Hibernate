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
				// Handling a collection (ManyToMany)
				// Forces initialization of the lazy-loaded Pet collection.
				// Triggers a SELECT query to fetch all pets belonging to this owner.
				// After this, you can safely use owner.getPetList() even outside a Session.
				Hibernate.initialize(owner.getPetList());
			}
			return owner;
		}
	}

	@Override
	public void updatePetDetails(int ownerId, int petId, String petName) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			Owner owner = session.get(Owner.class, ownerId);
			if (Objects.nonNull(owner)) {
				owner.getPetList().stream().filter(pet -> pet.getId() == petId).findFirst()
						.ifPresent(pet -> pet.setName(petName));
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
				owner.getPetList().stream().filter(pet -> pet.getOwnerList().size() == 1).forEach(session::remove);// remove
																													// pet
				session.remove(owner);// remove owner
			}
			transaction.commit();
		}
	}

	@Override
	public void addPet(int ownerId, Pet pet) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			Owner owner = session.get(Owner.class, ownerId);
			if (Objects.nonNull(owner)) {
				owner.getPetList().add(pet);
				session.merge(owner);
			}
			transaction.commit();
		}
	}

	@Override
	public void removePet(int ownerId, int petId) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			Owner owner = session.get(Owner.class, ownerId);
			if (Objects.nonNull(owner)) {

				/*
				 * owner.getPetList().stream().filter(pet -> pet.getId() == petId) .filter(pet
				 * -> pet.getOwnerList().size() == 1) .findFirst() .ifPresent(pet ->
				 * session.remove(pet));
				 */

				owner.getPetList().stream().filter(pet -> pet.getId() == petId)
						.filter(pet -> pet.getOwnerList().size() == 1).findFirst().ifPresent(session::remove);

				// ? Always remove the pet from the owner's pet list (unlink the association)
				owner.getPetList().removeIf(pet -> pet.getId() == petId);
				session.merge(owner);
			}
			transaction.commit();
		}
	}

	@Override
	public void addCoOwner(int petId, Owner owner) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			Pet pet = session.get(Pet.class, petId);
			if (Objects.nonNull(pet)) {
				owner.getPetList().add(pet);
				session.persist(owner);
			}
			transaction.commit();
		}
	}
}
