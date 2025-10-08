package com.hibernate.service;


import com.hibernate.dto.OwnerDTO;
import com.hibernate.dto.PetDTO;
import com.hibernate.exception.OwnerNotFoundException;
import com.hibernate.exception.OwnerPetCombinationNotFoundException;
import com.hibernate.exception.PetNotFoundException;

public interface OwnerService {
	
	void saveOwner(OwnerDTO ownerDTO);

	OwnerDTO findOwner(int ownerId) throws OwnerNotFoundException;
	
	OwnerDTO findOwnerWithPet(int ownerId) throws OwnerNotFoundException;
	
	void updatePetDetails(int ownerId, int petId, String petName) throws OwnerNotFoundException, OwnerPetCombinationNotFoundException;

	void deleteOwner(int ownerId) throws OwnerNotFoundException;
	
	void addPet(int ownerId, PetDTO petDTO) throws OwnerNotFoundException;

	void removePet(int ownerId, int petId) throws OwnerNotFoundException, OwnerPetCombinationNotFoundException;

	void addCoOwner(int petId, OwnerDTO ownerDTO) throws PetNotFoundException;
}
