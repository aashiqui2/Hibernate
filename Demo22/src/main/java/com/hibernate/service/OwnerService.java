package com.hibernate.service;

import com.hibernate.dto.OwnerDTO;
import com.hibernate.exception.OwnerNotFoundException;

public interface OwnerService {
	void saveOwner(OwnerDTO ownerDTO);

	OwnerDTO findOwner(int ownerId) throws OwnerNotFoundException;

	void updatePetDetailsUsingPersistentState(int ownerId, String petName) throws OwnerNotFoundException;

	void updatePetDetailsUsingDetachedState(int ownerId, String petName) throws OwnerNotFoundException;

	void deleteOwner(int ownerId) throws OwnerNotFoundException;
}