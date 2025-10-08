package com.hibernate.service;


import com.hibernate.dto.OwnerDTO;
import com.hibernate.entity.OwnerPetPrimaryKey;
import com.hibernate.exception.DuplicateOwnerPetCombinationException;
import com.hibernate.exception.OwnerPetCombinationNotFoundException;


public interface OwnerService {
	void saveOwner(OwnerDTO ownerDTO) throws DuplicateOwnerPetCombinationException;

	OwnerDTO findOwner(OwnerPetPrimaryKey primarykey) throws OwnerPetCombinationNotFoundException;

	void updatePetDetails(OwnerPetPrimaryKey primarykey, String petName) throws OwnerPetCombinationNotFoundException;

	void deleteOwner(OwnerPetPrimaryKey primarykey) throws OwnerPetCombinationNotFoundException;
}
