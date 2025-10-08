package com.hibernate.repository;

import com.hibernate.entity.Owner;

public interface OwnerRepository {
	void saveOwner(Owner owner);

	Owner findOwner(int ownerId);

	void updatePetDetailsUsingPersistentState(int ownerId, String petName);

	void updatePetDetailsUsingDetachedState(int ownerId, String petName);

	void deleteOwner(int ownerId);
}
