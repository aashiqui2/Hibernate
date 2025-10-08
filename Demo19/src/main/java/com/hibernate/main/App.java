package com.hibernate.main;

import java.util.List;
import java.util.Scanner;

import com.hibernate.dto.OwnerDTO;
import com.hibernate.dto.PetDTO;
import com.hibernate.service.OwnerService;
import com.hibernate.service.PetService;
import com.hibernate.service.impl.OwnerServiceImpl;
import com.hibernate.service.impl.PetServiceImpl;
import com.hibernate.util.InputUtil;

public class App {
	public static void main(String[] args) {
		App demo = new App();
		demo.run();
	}

	public void run() {
		OwnerService ownerService = new OwnerServiceImpl();
		PetService petService = new PetServiceImpl();
		try (Scanner scanner = new Scanner(System.in)) {
			do {
				System.out.println("Welcome to Petistaan");
				int menuOption = InputUtil.acceptMenuOption(scanner);
				switch (menuOption) {
				case 1:
					int ownerId = InputUtil.acceptOwnerIdToOperate(scanner);
					OwnerDTO ownerDTO = ownerService.findOwner(ownerId);
					System.out.println("Owner has been fetched successfully.");
					System.out.println(ownerDTO);
					ownerDTO = ownerService.findOwnerWithPet(ownerId);
					System.out.println("Owner with pet has been fetched successfully.");
					System.out.println(ownerDTO);
					break;
				case 2:
					int petId = InputUtil.acceptPetIdToOperate(scanner);
					PetDTO petDTO = petService.findPet(petId);
					System.out.println("Pet has been fetched successfully.");
					System.out.println(petDTO);
					break;
				case 3:
					List<OwnerDTO> ownerDTOList = ownerService.findAllOwners();
					System.out.println("There are " + ownerDTOList.size() + " owners.");
					ownerDTOList.forEach(System.out::println);
					break;
				case 4:
					List<PetDTO> petDTOList = petService.findAllPets();
					System.out.println("There are " + petDTOList.size() + " pets.");
					petDTOList.forEach(System.out::println);
					break;
				case 5:
					ownerDTOList = ownerService.findAllOwnersWithPet();
					System.out.println("There are " + ownerDTOList.size() + " owners with pets.");
					ownerDTOList.forEach(System.out::println);
					break;
				default:
					System.out.println("Invalid option entered.");
				}
			} while (InputUtil.wantToContinue(scanner));
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
	}
}
