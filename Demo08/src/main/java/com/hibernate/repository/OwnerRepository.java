package com.hibernate.repository;

import com.hibernate.entity.Owner;

public interface OwnerRepository {
	
	void saveOwner(Owner owner);

	Owner findOwner(int ownerId);

	void updatePetDetails(int ownerId, String petName);

	void deleteOwner(int ownerId);
	
	Owner findOwner(String emailId,String mobileNumber);

}
