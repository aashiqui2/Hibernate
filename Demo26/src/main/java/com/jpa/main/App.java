package com.jpa.main;

import java.util.List;
import java.util.Scanner;

import com.jpa.dto.OwnerDTO;
import com.jpa.dto.PetDTO;
import com.jpa.service.OwnerService;
import com.jpa.service.PetService;
import com.jpa.service.impl.OwnerServiceImpl;
import com.jpa.service.impl.PetServiceImpl;
import com.jpa.util.InputUtil;


public class App {
	public static void main(String[] args) {
		App app = new App();
		app.run();
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
					OwnerDTO ownerDTO = InputUtil.acceptOwnerDetailsToSave(scanner);
					PetDTO petDTO = InputUtil.acceptPetDetailsToSave(scanner);
					ownerDTO.setPetDTO(petDTO);
					ownerService.saveOwner(ownerDTO);
					System.out.println("Owner has been saved successfully.");
					break;
				case 2:
					int ownerId = InputUtil.acceptOwnerIdToOperate(scanner);
					ownerDTO = ownerService.findOwner(ownerId);
					System.out.println("Owner has been fetched successfully.");
					System.out.println(ownerDTO);
					break;
				case 3:
					ownerId = InputUtil.acceptOwnerIdToOperate(scanner);
					String petName = InputUtil.acceptPetDetailsToUpdate(scanner);
					ownerService.updatePetDetails(ownerId, petName);
					System.out.println("Pet details of owner have been updated successfully.");
					break;
				case 4:
					ownerId = InputUtil.acceptOwnerIdToOperate(scanner);
					ownerService.deleteOwner(ownerId);
					System.out.println("Owner has been deleted successfully.");
					break;
				case 5:
					List<OwnerDTO> ownerDTOList = ownerService.findAllOwners();
					System.out.println("There are " + ownerDTOList.size() + " owners.");
					ownerDTOList.forEach(System.out::println);
					break;
				case 6:
					int petId = InputUtil.acceptPetIdToOperate(scanner);
					petDTO = petService.findPet(petId);
					System.out.println("Pet has been fetched successfully.");
					System.out.println(petDTO);
					break;
				case 7:
					double age = petService.findAverageAgeOfPet();
					System.out.println("Average age of pet is " + age + " years.");
					break;
				case 8:
					int pageNumber = InputUtil.acceptPageNumberToOperate(scanner);
					int pageSize = InputUtil.acceptPageSizeToOperate(scanner);
					List<Object[]> detailsList = ownerService.findIdAndFirstNameAndLastNameAndPetName(pageNumber,
							pageSize);
					System.out.println("Showing " + detailsList.size() + " records on page number " + pageNumber);
					detailsList.forEach(details -> System.out.println("Id: " + details[0] + ", First Name: "
							+ details[1] + ", Last Name: " + details[2] + ", Pet Name: " + details[3]));
					break;
				default:
					System.out.println("Invalid option entered.");
				}
			} while (InputUtil.wantToContinue(scanner));
		} catch (Exception exception) {
			exception.printStackTrace();
			System.out.println(exception.getMessage());
		}
	}
}
