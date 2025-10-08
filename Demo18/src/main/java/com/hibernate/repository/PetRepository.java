package com.hibernate.repository;

import java.util.List;

import com.hibernate.entity.Pet;

public interface PetRepository {
	Pet findPet(int petId);

	List<Pet> findAllPets();
}
