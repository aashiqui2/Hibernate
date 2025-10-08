package com.jpa.repository;

import com.jpa.entity.Pet;


public interface PetRepository {
	Pet findPet(int petId);

	Double findAverageAgeOfPet();
}
