package com.hibernate.service.impl;

import com.hibernate.repository.PetRepository;
import com.hibernate.repository.impl.PetRepositoryImpl;
import com.hibernate.service.PetService;

public class PetServiceImpl implements PetService {
	private PetRepository petRepository;

	public PetServiceImpl() {
		this.petRepository = new PetRepositoryImpl();
	}

	@Override
	public Double findAverageAgeOfPet() {
		return petRepository.findAverageAgeOfPet();
	}
}