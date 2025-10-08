package com.hibernate.service;

import java.util.List;

import com.hibernate.dto.OwnerDTO;
import com.hibernate.exception.DuplicateOwnerException;
import com.hibernate.exception.OwnerNotFoundException;

public interface OwnerService {
	void saveOwner(OwnerDTO ownerDTO) throws DuplicateOwnerException;

	OwnerDTO findOwner(int ownerId) throws OwnerNotFoundException;

	void updatePetDetails(int ownerId, String petName) throws OwnerNotFoundException;

	void deleteOwner(int ownerId) throws OwnerNotFoundException;

	List<OwnerDTO> findAllOwners();
}
