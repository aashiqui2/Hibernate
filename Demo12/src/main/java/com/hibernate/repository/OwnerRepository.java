package com.hibernate.repository;

import com.hibernate.entity.Owner;
import com.hibernate.entity.Pet;

public interface OwnerRepository {
	
	void saveOwner(Owner owner);

	Owner findOwner(int ownerId);
	
	Owner findOwnerWithPet(int ownerId);
	
	void updatePetDetails(int ownerId, int petId, String petName);

	void deleteOwner(int ownerId);

	void addPet(int ownerId, Pet pet);

	void deletePet(int ownerId, int petId);

}
