package com.hibernate.service.impl;


import java.time.LocalDate;
import java.util.List;

import com.hibernate.dto.OwnerDTO;
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
	public List<OwnerDTO> findAllOwners() {
		return ownerRepository.findAllOwners().stream().map(MapperUtil::convertOwnerEntityToDto).toList();
	}

	@Override
	public List<OwnerDTO> findAllOwnersByFirstNameInitials(String firstName) {
		return ownerRepository.findAllOwnersByFirstNameInitials(firstName).stream()
				.map(MapperUtil::convertOwnerEntityToDto).toList();
	}

	@Override
	public List<OwnerDTO> findByAllOwnersByPetDateOfBirthBetween(LocalDate startDate, LocalDate endDate) {
		return ownerRepository.findAllOwnersByPetDateOfBirthRange(startDate, endDate).stream()
				.map(MapperUtil::convertOwnerEntityToDto).toList();
	}

	@Override
	public List<Object[]> findIdAndFirstNameAndLastNameAndPetName(int pageNumber, int pageSize) {
		return ownerRepository.findIdAndFirstNameAndLastNameAndPetName(pageNumber, pageSize);
	}
}
