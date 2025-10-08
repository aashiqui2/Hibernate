package com.hibernate.service.impl;


import java.util.List;
import java.util.Objects;

import com.hibernate.config.PropertiesConfig;
import com.hibernate.dto.OwnerDTO;
import com.hibernate.entity.Owner;
import com.hibernate.exception.OwnerNotFoundException;
import com.hibernate.repository.OwnerRepository;
import com.hibernate.repository.impl.OwnerRepositoryImpl;
import com.hibernate.service.OwnerService;
import com.hibernate.util.MapperUtil;

public class OwnerServiceImpl implements OwnerService {
	private OwnerRepository ownerRepository;
	private static final String OWNER_NOT_FOUND = "owner.not.found";
	private static final PropertiesConfig PROPERTIES_CONFIG = PropertiesConfig.getInstance();

	public OwnerServiceImpl() {
		this.ownerRepository = new OwnerRepositoryImpl();
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
	public List<OwnerDTO> findAllOwners() {
		return ownerRepository.findAllOwners().stream()
				.map(MapperUtil::convertOwnerEntityToDtoWithoutPet)
				.toList();
	}
}

