package com.hibernate.service.impl;


import java.util.Objects;

import com.hibernate.config.PropertiesConfig;
import com.hibernate.dto.OwnerDTO;
import com.hibernate.repository.OwnerRepository;
import com.hibernate.service.OwnerService;
import com.hibernate.util.MapperUtil;
import com.hibernate.repository.impl.OwnerRepositoryImpl;
import com.hibernate.entity.Owner;
import com.hibernate.entity.OwnerPetPrimaryKey;
import com.hibernate.exception.DuplicateOwnerPetCombinationException;
import com.hibernate.exception.OwnerPetCombinationNotFoundException;

public class OwnerServiceImpl implements OwnerService {
	private OwnerRepository ownerRepository;
	private static final String OWNER_PET_COMBINATION_ALREADY_EXISTS = "owner.pet.combination.already.exists";
	private static final String OWNER_PET_COMBINATION_NOT_FOUND = "owner.pet.combination.not.found";
	private static final PropertiesConfig PROPERTIES_CONFIG = PropertiesConfig.getInstance();

	public OwnerServiceImpl() {
		this.ownerRepository = new OwnerRepositoryImpl();
	}

	@Override
	public void saveOwner(OwnerDTO ownerDTO) throws DuplicateOwnerPetCombinationException {
		OwnerPetPrimaryKey primaryKey = new OwnerPetPrimaryKey();
		primaryKey.setId(ownerDTO.getId());
		primaryKey.setPetId(ownerDTO.getPetId());
		Owner existingOwner = ownerRepository.findOwner(primaryKey);
		if (Objects.nonNull(existingOwner)) {
			throw new DuplicateOwnerPetCombinationException(
					String.format(PROPERTIES_CONFIG.getProperty(OWNER_PET_COMBINATION_ALREADY_EXISTS), ownerDTO.getId(), ownerDTO.getPetId()));
		}
		Owner owner = MapperUtil.convertOwnerDtoToEntity(ownerDTO);
		ownerRepository.saveOwner(owner);
	}

	@Override
	public OwnerDTO findOwner(OwnerPetPrimaryKey primaryKey) throws OwnerPetCombinationNotFoundException {
		Owner owner = ownerRepository.findOwner(primaryKey);
		if (Objects.isNull(owner)) {
			throw new OwnerPetCombinationNotFoundException(String.format(PROPERTIES_CONFIG.getProperty(OWNER_PET_COMBINATION_NOT_FOUND), primaryKey.getId(), primaryKey.getPetId()));
		}
		return MapperUtil.convertOwnerEntityToDto(owner);
	}

	@Override
	public void updatePetDetails(OwnerPetPrimaryKey primaryKey, String petName) throws OwnerPetCombinationNotFoundException {
		Owner owner = ownerRepository.findOwner(primaryKey);
		if (Objects.isNull(owner)) {
			throw new OwnerPetCombinationNotFoundException(String.format(PROPERTIES_CONFIG.getProperty(OWNER_PET_COMBINATION_NOT_FOUND), primaryKey.getId(), primaryKey.getPetId()));
		}
		ownerRepository.updatePetDetails(primaryKey, petName);
	}

	@Override
	public void deleteOwner(OwnerPetPrimaryKey primaryKey) throws OwnerPetCombinationNotFoundException {
		Owner owner = ownerRepository.findOwner(primaryKey);
		if (Objects.isNull(owner)) {
			throw new OwnerPetCombinationNotFoundException(String.format(PROPERTIES_CONFIG.getProperty(OWNER_PET_COMBINATION_NOT_FOUND), primaryKey.getId(), primaryKey.getPetId()));
		}
		ownerRepository.deleteOwner(primaryKey);
	}
}
