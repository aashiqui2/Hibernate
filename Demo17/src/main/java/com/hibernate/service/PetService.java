package com.hibernate.service;

import com.hibernate.dto.PetDTO;
import com.hibernate.exception.PetNotFoundException;

public interface PetService {
	PetDTO findPet(int petId) throws PetNotFoundException;

}