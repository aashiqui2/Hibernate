package com.hibernate.service.impl;

import java.util.List;

import com.hibernate.dto.OwnerDTO;
import com.hibernate.entity.Owner;
import com.hibernate.repository.OwnerRepository;
import com.hibernate.repository.impl.OwnerRepositoryImpl;
import com.hibernate.service.OwnerService;
import com.hibernate.util.MapperUtil;

public class OwnerServiceImpl implements OwnerService {
	private OwnerRepository ownerRepository;

	public OwnerServiceImpl() {
		this.ownerRepository = new OwnerRepositoryImpl();
	}

	@Override
	public List<OwnerDTO> findSelectedOwnersWithoutHql(List<Integer> ownerIdList) {
		return ownerRepository.findSelectedOwnersWithoutHql(ownerIdList).stream()
				.map(MapperUtil::convertOwnerEntityToDto).toList();
	}

	@Override
	public List<OwnerDTO> findSelectedOwnersWithoutHqlV2(List<Integer> ownerIdList) {
		return ownerIdList.stream().map(ownerId -> {
			Owner owner = ownerRepository.findOwnerWithoutHql(ownerId);
			return MapperUtil.convertOwnerEntityToDto(owner);
		}).toList();
	}

	@Override
	public List<OwnerDTO> findSelectedOwnersWithHql(List<Integer> ownerIdList) {
		return ownerRepository.findSelectedOwnersWithHql(ownerIdList).stream().map(MapperUtil::convertOwnerEntityToDto)
				.toList();
	}

	@Override
	public List<OwnerDTO> findSelectedOwnersWithHqlV2(List<Integer> ownerIdList) {
		return ownerIdList.stream().map(ownerId -> {
			Owner owner = ownerRepository.findOwnerWithHql(ownerId);
			return MapperUtil.convertOwnerEntityToDto(owner);
		}).toList();
	}

	@Override
	public List<OwnerDTO> findAllOwners() {
		return ownerRepository.findAllOwners().stream().map(MapperUtil::convertOwnerEntityToDto).toList();
	}
}
