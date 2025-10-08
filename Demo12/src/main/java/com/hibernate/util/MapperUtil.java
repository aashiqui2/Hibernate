package com.hibernate.util;


import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.hibernate.dto.OwnerDTO;
import com.hibernate.dto.PetDTO;
import com.hibernate.entity.Owner;
import com.hibernate.entity.Pet;


public class MapperUtil {
	private MapperUtil() {

	}

	public static OwnerDTO convertOwnerEntityToDto(Owner owner) {
		OwnerDTO ownerDTO = new OwnerDTO();
		ownerDTO.setId(owner.getId());
		ownerDTO.setFirstName(owner.getFirstName());
		ownerDTO.setLastName(owner.getLastName());
		ownerDTO.setGender(owner.getGender());
		ownerDTO.setCity(owner.getCity());
		ownerDTO.setState(owner.getState());
		ownerDTO.setMobileNumber(owner.getMobileNumber());
		ownerDTO.setEmailId(owner.getEmailId());
		List<PetDTO> petDTOList = owner.getPetList().stream()
				.map(MapperUtil::convertPetEntityToDtoWithoutOwner)
				.collect(Collectors.toList());
		// List<PetDTO> petDTOList=owner.getPetList().stream()
		//              .map(pet->convertPetEntityToDtoWithoutOwner(pet))
		// 			 .collect(Collectors.toList());
		ownerDTO.setPetDTOList(petDTOList);
		return ownerDTO;
	}

	public static PetDTO convertPetEntityToDtoWithoutOwner(Pet pet) {
		PetDTO petDTO = new PetDTO();
		petDTO.setId(pet.getId());
		petDTO.setName(pet.getName());
		petDTO.setBirthDate(pet.getBirthDate());
		petDTO.setGender(pet.getGender());
		petDTO.setType(pet.getType());
		return petDTO;
	}

	public static PetDTO convertPetEntityToDto(Pet pet) {
		PetDTO petDTO = new PetDTO();
		petDTO.setId(pet.getId());
		petDTO.setName(pet.getName());
		petDTO.setBirthDate(pet.getBirthDate());
		petDTO.setGender(pet.getGender());
		petDTO.setType(pet.getType());
		OwnerDTO ownerDTO = convertOwnerEntityToDtoWithoutPet(pet.getOwner());
		petDTO.setOwnerDTO(ownerDTO);
		return petDTO;
	}

	public static OwnerDTO convertOwnerEntityToDtoWithoutPet(Owner owner) {
		OwnerDTO ownerDTO = new OwnerDTO();
		ownerDTO.setId(owner.getId());
		ownerDTO.setFirstName(owner.getFirstName());
		ownerDTO.setLastName(owner.getLastName());
		ownerDTO.setGender(owner.getGender());
		ownerDTO.setCity(owner.getCity());
		ownerDTO.setState(owner.getState());
		ownerDTO.setMobileNumber(owner.getMobileNumber());
		ownerDTO.setEmailId(owner.getEmailId());
		return ownerDTO;
	}

	public static Owner convertOwnerDtoToEntity(OwnerDTO ownerDTO) {
		Owner owner = new Owner();
		owner.setFirstName(ownerDTO.getFirstName());
		owner.setLastName(ownerDTO.getLastName());
		owner.setGender(ownerDTO.getGender());
		owner.setCity(ownerDTO.getCity());
		owner.setState(ownerDTO.getState());
		owner.setMobileNumber(ownerDTO.getMobileNumber());
		owner.setEmailId(ownerDTO.getEmailId());
		List<Pet> petList = ownerDTO.getPetDTOList().stream().map(petDTO -> {
			Pet pet = convertPetDtoToEntity(petDTO);
			pet.setOwner(owner);
			return pet;
		}).collect(Collectors.toList());
		owner.setPetList(petList);
		return owner;
	}

	public static Pet convertPetDtoToEntity(PetDTO petDTO) {
		Pet pet = new Pet();
		pet.setName(petDTO.getName());
		pet.setBirthDate(petDTO.getBirthDate());
		pet.setGender(petDTO.getGender());
		pet.setType(petDTO.getType());
		return pet;
	}

}

