package com.hibernate.repository.impl;

import java.util.List;
import java.util.Objects;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hibernate.config.DatabaseConfig;
import com.hibernate.entity.Owner;
import com.hibernate.entity.Pet;
import com.hibernate.repository.OwnerRepository;

public class OwnerRepositoryImpl implements OwnerRepository {
	private SessionFactory sessionFactory = DatabaseConfig.getSessionFactory();

	@Override
	public List<Owner> findSelectedOwnersWithoutHql(List<Integer> ownerIdList) {
		try (Session session = sessionFactory.openSession()) {
			return ownerIdList.stream().map(ownerId -> {
				Owner owner = session.get(Owner.class, ownerId);
				if (Objects.nonNull(owner)) {
					Pet pet = Hibernate.unproxy(owner.getPet(), Pet.class);
					owner.setPet(pet);
				}
				return owner;
			}).toList();
		}
	}
	//Note: primary cache does not work for HQL or Criteria API

	@Override
	public Owner findOwnerWithoutHql(Integer ownerId) {
		try (Session session = sessionFactory.openSession()) {
			Owner owner = session.get(Owner.class, ownerId);
			if (Objects.nonNull(owner)) {
				Pet pet = Hibernate.unproxy(owner.getPet(), Pet.class);
				owner.setPet(pet);
			}
			return owner;
		}
	}

	@Override
	public List<Owner> findSelectedOwnersWithHql(List<Integer> ownerIdList) {
		String sql = "SELECT o FROM Owner o JOIN FETCH o.pet WHERE o.id = :ownerId"; //solved N+1
		try (Session session = sessionFactory.openSession()) {
			return ownerIdList.stream().map(ownerId -> session.createQuery(sql, Owner.class)
					.setParameter("ownerId", ownerId).getSingleResultOrNull()).toList();
		}
	}

	@Override
	public Owner findOwnerWithHql(Integer ownerId) {
		String sql = "SELECT o FROM Owner o JOIN FETCH o.pet WHERE o.id = :ownerId"; //solved N+1
		try (Session session = sessionFactory.openSession()) {
			return session.createQuery(sql, Owner.class).setParameter("ownerId", ownerId).getSingleResultOrNull();
		}
	}
}
