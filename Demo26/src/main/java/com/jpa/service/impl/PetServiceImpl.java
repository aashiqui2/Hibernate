package com.jpa.service.impl;

import java.util.Objects;

import com.jpa.config.PropertiesConfig;
import com.jpa.dto.PetDTO;
import com.jpa.entity.Pet;
import com.jpa.exception.PetNotFoundException;
import com.jpa.repository.PetRepository;
import com.jpa.repository.impl.PetRepositoryImpl;
import com.jpa.service.PetService;
import com.jpa.util.MapperUtil;


public class PetServiceImpl implements PetService {
	private PetRepository petRepository;
	private static final String PET_NOT_FOUND = "pet.not.found";
	private static final PropertiesConfig PROPERTIES_CONFIG = PropertiesConfig.getInstance();

	public PetServiceImpl() {
		this.petRepository = new PetRepositoryImpl();
	}

	@Override
	public PetDTO findPet(int petId) throws PetNotFoundException {
		Pet pet = petRepository.findPet(petId);
		if (Objects.isNull(pet)) {
			throw new PetNotFoundException(String.format(PROPERTIES_CONFIG.getProperty(PET_NOT_FOUND), petId));
		}
		return MapperUtil.convertPetEntityToDto(pet);
	}

	@Override
	public Double findAverageAgeOfPet() {
		return petRepository.findAverageAgeOfPet();
	}
}
