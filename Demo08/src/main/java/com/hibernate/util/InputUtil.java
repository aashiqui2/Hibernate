package com.hibernate.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

import com.hibernate.dto.OwnerDTO;
import com.hibernate.enums.Gender;
import com.hibernate.enums.PetType;

public class InputUtil {
	public static int acceptMenuOption(Scanner sc) {
		System.out.println("Press 1 to add new owner.");
		System.out.println("Press 2 to fetch owner details.");
		System.out.println("Press 3 to update pet details of owner.");
		System.out.println("Press 4 to delete owner details.");
		System.out.println("Press 5 to fetch owner details with mobile Number.");
		int menuOption = sc.nextInt();
		if (menuOption == 1 || menuOption == 2 || menuOption == 3 || menuOption == 4|| menuOption == 5) {
			return menuOption;
		} else {
			return acceptMenuOption(sc);
		}
	}

	public static boolean wantToContinue(Scanner sc) {
		System.out.println("Press Y to continue and N to exit.");
		char choice = sc.next().toUpperCase().charAt(0);
		return 'Y' == choice;
	}

	public static OwnerDTO acceptOwnerDetailsToSave(Scanner sc) {
		System.out.println("Enter first name of owner:");
		String firstName = sc.next();
		System.out.println("Enter last name of owner:");
		String lastName = sc.next();
		System.out.println("Enter gender of owner:" + Arrays.asList(Gender.values()).toString());
		String gender = sc.next().toUpperCase();
		System.out.println("Enter city of owner:");
		String city = sc.next();
		System.out.println("Enter state of owner:");
		String state = sc.next();
		System.out.println("Enter mobile number of owner:");
		String mobileNumber = sc.next();
		System.out.println("Enter email id of owner:");
		String emailId = sc.next();
		System.out.println("Enter id of pet:");
		int petId = sc.nextInt();
		System.out.println("Enter name of pet:");
		String petName = sc.next();
		System.out.println("Enter date of birth of pet (dd-MM-yyyy):");
		String petDateOfBirth = sc.next();
		System.out.println("Enter gender of pet:" + Arrays.asList(Gender.values()).toString());
		String petGender = sc.next().toUpperCase();
		System.out.println("Enter pet type:" + Arrays.asList(PetType.values()).toString());
		String petType = sc.next().toUpperCase();
		try {
			OwnerDTO ownerDTO = new OwnerDTO();
			ownerDTO.setFirstName(firstName);
			ownerDTO.setLastName(lastName);
			ownerDTO.setGender(Gender.valueOf(gender));
			ownerDTO.setCity(city);
			ownerDTO.setState(state);
			ownerDTO.setMobileNumber(mobileNumber);
			ownerDTO.setEmailId(emailId);
			ownerDTO.setPetId(petId);
			ownerDTO.setPetName(petName);
			ownerDTO.setPetBirthDate(convertStringToDate(petDateOfBirth));
			ownerDTO.setPetGender(Gender.valueOf(petGender));
			ownerDTO.setPetType(PetType.valueOf(petType));
			return ownerDTO;
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			return acceptOwnerDetailsToSave(sc);
		}
	}

	private static LocalDate convertStringToDate(String petDateOfBirth) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		return LocalDate.parse(petDateOfBirth, format);
	}
	
	public static int acceptOwnerIdToOperate(Scanner sc) {
		System.out.println("Enter id of owner:");
		return sc.nextInt();
	}
	
	public static String acceptPetDetailsToUpdate(Scanner scanner) {
		System.out.println("Enter updated name of pet:");
		return scanner.next();
	}

	public static String acceptOwnerEmailIdToOperate(Scanner scanner) {
		System.out.println("Enter email id of owner:");
		return scanner.next();
	}
	public static String acceptOwnerMobileNumberToOperate(Scanner scanner) {
		System.out.println("Enter mobile number of owner:");
		return scanner.next();
	}

}
