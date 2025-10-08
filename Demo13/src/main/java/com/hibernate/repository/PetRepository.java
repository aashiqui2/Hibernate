package com.hibernate.repository;

import com.hibernate.entity.Pet;

public interface PetRepository {
	Pet findPet(int petId);

	Pet findPetWithOwner(int petId);
}
