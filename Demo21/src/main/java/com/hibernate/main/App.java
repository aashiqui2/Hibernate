package com.hibernate.main;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.hibernate.dto.OwnerDTO;
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
					List<OwnerDTO> ownerDTOList = ownerService.findAllOwners();
					System.out.println("There are " + ownerDTOList.size() + " owners.");
					ownerDTOList.forEach(System.out::println);
					break;
				case 2:
					String firstName = InputUtil.acceptOwnerInititialsToOperate(scanner);
					ownerDTOList = ownerService.findAllOwnersByFirstNameInitials(firstName);
					System.out.println(
							"There are " + ownerDTOList.size() + " owners whose name starts with " + firstName);
					ownerDTOList.forEach(System.out::println);
					break;
				case 3:
					LocalDate startDate = InputUtil.acceptFromPetBirthDateToOperate(scanner);
					LocalDate endDate = InputUtil.acceptToPetBirthDateToOperate(scanner);
					ownerDTOList = ownerService.findByAllOwnersByPetDateOfBirthBetween(startDate, endDate);
					System.out.println("There are " + ownerDTOList.size() + " owners whose pets born between "
							+ startDate + " and " + endDate);
					ownerDTOList.forEach(System.out::println);
					break;
				case 4:
					double age = petService.findAverageAgeOfPet();
					System.out.println("Average age of pet is " + age + " years.");
					break;
				case 5:
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
			System.out.println(exception.getMessage());
		}
	}
}
