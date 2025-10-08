package com.hibernate.service.impl;

import java.util.Objects;

import com.hibernate.config.PropertiesConfig;
import com.hibernate.dto.OwnerDTO;
import com.hibernate.exception.DuplicateOwnerException;
import com.hibernate.exception.OwnerNotFoundException;
import com.hibernate.repository.OwnerRepository;
import com.hibernate.service.OwnerService;
import com.hibernate.util.MapperUtil;
import com.hibernate.repository.impl.OwnerRepositoryImpl;
import com.hibernate.entity.Owner;

public class OwnerServiceImpl implements OwnerService {
	private OwnerRepository ownerRepository;
	private static final String OWNER_ALREADY_EXISTS = "owner.already.exists";
	private static final String OWNER_NOT_FOUND = "owner.not.found";
	private static final PropertiesConfig PROPERTIES_CONFIG = PropertiesConfig.getInstance();

	public OwnerServiceImpl() {
		this.ownerRepository = new OwnerRepositoryImpl();
	}

	@Override
	public void saveOwner(OwnerDTO ownerDTO) throws DuplicateOwnerException {
		Owner existingOwner = ownerRepository.findOwner(ownerDTO.getId());
		if (Objects.nonNull(existingOwner)) {
			throw new DuplicateOwnerException(
					String.format(PROPERTIES_CONFIG.getProperty(OWNER_ALREADY_EXISTS), ownerDTO.getId()));
		}
		Owner owner = MapperUtil.convertOwnerDtoToEntity(ownerDTO);
		ownerRepository.saveOwner(owner);
	}

	@Override
	public OwnerDTO findOwner(int ownerId) throws OwnerNotFoundException {
		Owner owner = ownerRepository.findOwner(ownerId);
		if (Objects.isNull(owner)) {
			throw new OwnerNotFoundException(String.format(PROPERTIES_CONFIG.getProperty(OWNER_NOT_FOUND), ownerId));
		}
		return MapperUtil.convertOwnerEntityToDto(owner);
	}

	@Override
	public void updatePetDetails(int ownerId, String petName) throws OwnerNotFoundException {
		Owner owner = ownerRepository.findOwner(ownerId);
		if (Objects.isNull(owner)) {
			throw new OwnerNotFoundException(String.format(PROPERTIES_CONFIG.getProperty(OWNER_NOT_FOUND), ownerId));
		}
		ownerRepository.updatePetDetails(ownerId, petName);
	}

	@Override
	public void deleteOwner(int ownerId) throws OwnerNotFoundException {
		Owner owner = ownerRepository.findOwner(ownerId);
		if (Objects.isNull(owner)) {
			throw new OwnerNotFoundException(String.format(PROPERTIES_CONFIG.getProperty(OWNER_NOT_FOUND), ownerId));
		}
		ownerRepository.deleteOwner(ownerId);
	}
}