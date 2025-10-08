package com.hibernate.service.impl;


import java.util.Objects;

import com.hibernate.config.PropertiesConfig;
import com.hibernate.dto.OwnerDTO;
import com.hibernate.dto.PetDTO;
import com.hibernate.entity.Owner;
import com.hibernate.entity.Pet;
import com.hibernate.exception.OwnerNotFoundException;
import com.hibernate.exception.OwnerPetCombinationNotFoundException;
import com.hibernate.exception.PetNotFoundException;
import com.hibernate.repository.OwnerRepository;
import com.hibernate.repository.PetRepository;
import com.hibernate.repository.impl.OwnerRepositoryImpl;
import com.hibernate.repository.impl.PetRepositoryImpl;
import com.hibernate.service.OwnerService;
import com.hibernate.util.MapperUtil;

public class OwnerServiceImpl implements OwnerService {
	private OwnerRepository ownerRepository;
	private PetRepository petRepository;
	private static final String OWNER_NOT_FOUND = "owner.not.found";
	private static final String PET_NOT_FOUND = "pet.not.found";
	private static final String OWNER_PET_COMBINATION_NOT_FOUND = "owner.pet.combination.not.found";
	private static final PropertiesConfig PROPERTIES_CONFIG = PropertiesConfig.getInstance();

	public OwnerServiceImpl() {
		this.ownerRepository = new OwnerRepositoryImpl();
		this.petRepository = new PetRepositoryImpl();
	}

	@Override
	public void saveOwner(OwnerDTO ownerDTO) {
		Owner owner = MapperUtil.convertOwnerDtoToEntity(ownerDTO);
		ownerRepository.saveOwner(owner);
	}

	@Override
	public OwnerDTO findOwner(int ownerId) throws OwnerNotFoundException {
		Owner owner = ownerRepository.findOwner(ownerId);
		if (Objects.isNull(owner)) {
			throw new OwnerNotFoundException(String.format(PROPERTIES_CONFIG.getProperty(OWNER_NOT_FOUND), ownerId));
		}
		return MapperUtil.convertOwnerEntityToDtoWithoutPet(owner);
	}
	
	@Override
	public OwnerDTO findOwnerWithPet(int ownerId) throws OwnerNotFoundException {
		Owner owner = ownerRepository.findOwnerWithPet(ownerId);
		if (Objects.isNull(owner)) {
			throw new OwnerNotFoundException(String.format(PROPERTIES_CONFIG.getProperty(OWNER_NOT_FOUND), ownerId));
		}
		return MapperUtil.convertOwnerEntityToDto(owner);
	}
	
	@Override
	public void updatePetDetails(int ownerId, int petId, String petName) throws OwnerNotFoundException, OwnerPetCombinationNotFoundException {
		Owner owner = ownerRepository.findOwnerWithPet(ownerId);
		if (Objects.isNull(owner)) {
			throw new OwnerNotFoundException(String.format(PROPERTIES_CONFIG.getProperty(OWNER_NOT_FOUND), ownerId));
		} else if (owner.getPetList().stream().filter(pet -> pet.getId() == petId).findFirst().isEmpty()) {
			throw new OwnerPetCombinationNotFoundException(String.format(PROPERTIES_CONFIG.getProperty(OWNER_PET_COMBINATION_NOT_FOUND), petId, ownerId));
		} else {
			ownerRepository.updatePetDetails(ownerId, petId, petName);
		}
	}

	@Override
	public void deleteOwner(int ownerId) throws OwnerNotFoundException {
		Owner owner = ownerRepository.findOwner(ownerId);
		if (Objects.isNull(owner)) {
			throw new OwnerNotFoundException(String.format(PROPERTIES_CONFIG.getProperty(OWNER_NOT_FOUND), ownerId));
		}
		ownerRepository.deleteOwner(ownerId);
	}
	
	@Override
	public void addPet(int ownerId, PetDTO petDTO) throws OwnerNotFoundException {
		Owner owner = ownerRepository.findOwner(ownerId);
		if (Objects.isNull(owner)) {
			throw new OwnerNotFoundException(String.format(PROPERTIES_CONFIG.getProperty(OWNER_NOT_FOUND), ownerId));
		}
		Pet pet = MapperUtil.convertPetDtoToEntity(petDTO);
		ownerRepository.addPet(ownerId, pet);
	}

	@Override
	public void removePet(int ownerId, int petId) throws OwnerNotFoundException, OwnerPetCombinationNotFoundException {
		Owner owner = ownerRepository.findOwnerWithPet(ownerId);
		if (Objects.isNull(owner)) {
			throw new OwnerNotFoundException(String.format(PROPERTIES_CONFIG.getProperty(OWNER_NOT_FOUND), ownerId));
		} else if (owner.getPetList().stream().filter(pet -> pet.getId() == petId).findFirst().isEmpty()) {
			throw new OwnerPetCombinationNotFoundException(String.format(PROPERTIES_CONFIG.getProperty(OWNER_PET_COMBINATION_NOT_FOUND), petId, ownerId));
		} else {
			ownerRepository.removePet(ownerId, petId);
		}
	}
	
	@Override
	public void addCoOwner(int petId, OwnerDTO ownerDTO) throws PetNotFoundException {
		Pet pet = petRepository.findPet(petId);
		if (Objects.isNull(pet)) {
			throw new PetNotFoundException(String.format(PROPERTIES_CONFIG.getProperty(PET_NOT_FOUND), petId));
		} else {
			Owner owner = MapperUtil.convertOwnerDtoToEntityWithoutPet(ownerDTO);
			ownerRepository.addCoOwner(petId, owner);
		}
	}
}