package com.jpa.service.impl;

import java.util.List;
import java.util.Objects;

import com.jpa.config.PropertiesConfig;
import com.jpa.dto.OwnerDTO;
import com.jpa.entity.Owner;
import com.jpa.exception.OwnerNotFoundException;
import com.jpa.repository.OwnerRepository;
import com.jpa.repository.impl.OwnerRepositoryImpl;
import com.jpa.service.OwnerService;
import com.jpa.util.MapperUtil;


public class OwnerServiceImpl implements OwnerService {
	private OwnerRepository ownerRepository;
	private static final String OWNER_NOT_FOUND = "owner.not.found";
	private static final PropertiesConfig PROPERTIES_CONFIG = PropertiesConfig.getInstance();

	public OwnerServiceImpl() {
		this.ownerRepository = new OwnerRepositoryImpl();
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

	@Override
	public List<OwnerDTO> findAllOwners() {
		return ownerRepository.findAllOwners().stream().map(MapperUtil::convertOwnerEntityToDto).toList();
	}
	
	@Override
	public List<Object[]> findIdAndFirstNameAndLastNameAndPetName(int pageNumber, int pageSize) {
		return ownerRepository.findIdAndFirstNameAndLastNameAndPetName(pageNumber, pageSize);
	}
}
