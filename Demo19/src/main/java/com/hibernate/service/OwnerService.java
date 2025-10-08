package com.hibernate.service;


import java.util.List;

import com.hibernate.dto.OwnerDTO;
import com.hibernate.exception.OwnerNotFoundException;

public interface OwnerService {
	OwnerDTO findOwner(int ownerId) throws OwnerNotFoundException;

	OwnerDTO findOwnerWithPet(int ownerId) throws OwnerNotFoundException;

	List<OwnerDTO> findAllOwners();

	List<OwnerDTO> findAllOwnersWithPet();
}
