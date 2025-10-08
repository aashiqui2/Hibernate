package com.hibernate.service.impl;

import java.util.Objects;

import com.hibernate.config.PropertiesConfig;
import com.hibernate.dto.PetDTO;
import com.hibernate.entity.Pet;
import com.hibernate.exception.PetNotFoundException;
import com.hibernate.repository.PetRepository;
import com.hibernate.repository.impl.PetRepositoryImpl;
import com.hibernate.service.PetService;
import com.hibernate.util.MapperUtil;

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
}