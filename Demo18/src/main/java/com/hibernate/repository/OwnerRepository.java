package com.hibernate.repository;

import java.util.List;

import com.hibernate.entity.Owner;

public interface OwnerRepository {
	Owner findOwner(int ownerId);

	List<Owner> findAllOwners();
}

