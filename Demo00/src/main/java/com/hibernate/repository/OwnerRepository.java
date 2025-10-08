package com.hibernate.repository;

import java.util.List;

import com.hibernate.dto.OwnerDTO;

public interface OwnerRepository {
	
	void saveOwner(OwnerDTO owner);

	OwnerDTO findOwner(int ownerId);

	void updatePetDetails(int ownerId, String petName);

	void deleteOwner(int ownerId);

	List<OwnerDTO> findAllOwners();
}
