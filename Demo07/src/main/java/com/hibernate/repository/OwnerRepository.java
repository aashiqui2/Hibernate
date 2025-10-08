package com.hibernate.repository;

import java.util.UUID;

import com.hibernate.entity.Owner;
import com.hibernate.entity.OwnerPetPrimaryKey;

public interface OwnerRepository {
	
	void saveOwner(Owner owner);

	Owner findOwner(OwnerPetPrimaryKey primarykey);

	void updatePetDetails(OwnerPetPrimaryKey primarykey, String petName);

	void deleteOwner(OwnerPetPrimaryKey primarykey);

}
