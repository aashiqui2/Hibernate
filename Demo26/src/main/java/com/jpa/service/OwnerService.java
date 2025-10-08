package com.jpa.service;

import java.util.List;

import com.jpa.dto.OwnerDTO;
import com.jpa.exception.OwnerNotFoundException;


public interface OwnerService {
	void saveOwner(OwnerDTO ownerDTO);

	OwnerDTO findOwner(int ownerId) throws OwnerNotFoundException;

	void updatePetDetails(int ownerId, String petName) throws OwnerNotFoundException;

	void deleteOwner(int ownerId) throws OwnerNotFoundException;

	List<OwnerDTO> findAllOwners();
	
	List<Object[]> findIdAndFirstNameAndLastNameAndPetName(int pageNumber, int pageSize);
}