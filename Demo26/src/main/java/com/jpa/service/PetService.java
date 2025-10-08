package com.jpa.service;

import com.jpa.dto.PetDTO;
import com.jpa.exception.PetNotFoundException;


public interface PetService {
	PetDTO findPet(int petId) throws PetNotFoundException;

	Double findAverageAgeOfPet();
}