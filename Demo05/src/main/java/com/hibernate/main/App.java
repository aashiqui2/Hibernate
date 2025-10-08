package com.hibernate.main;


import java.util.Scanner;

import com.hibernate.dto.OwnerDTO;
import com.hibernate.service.OwnerService;
import com.hibernate.service.impl.OwnerServiceImpl;
import com.hibernate.util.InputUtil;

public class App {
	public static void main(String[] args) {
		App app = new App();
		app.run();
	}

	public void run() {
		OwnerService ownerService = new OwnerServiceImpl();
		try (Scanner sc = new Scanner(System.in)) {
			do {
				System.out.println("Welcome to Petistaan");
				int menuOption = InputUtil.acceptMenuOption(sc);
				switch (menuOption) {
				case 1:
					OwnerDTO ownerDTO = InputUtil.acceptOwnerDetailsToSave(sc);
					ownerService.saveOwner(ownerDTO);
					System.out.println("Owner has been saved successfully.");
					break;
				case 2:
					String ownerId = InputUtil.acceptOwnerIdToOperate(sc);
					ownerDTO = ownerService.findOwner(ownerId);
					System.out.println("Owner has been fetched successfully.");
					System.out.println(ownerDTO);
					break;
				case 3:
					ownerId = InputUtil.acceptOwnerIdToOperate(sc);
					String petName = InputUtil.acceptPetDetailsToUpdate(sc);
					ownerService.updatePetDetails(ownerId, petName);
					System.out.println("Pet details of owner have been updated successfully.");
					break;
				case 4:
					ownerId = InputUtil.acceptOwnerIdToOperate(sc);
					ownerService.deleteOwner(ownerId);
					System.out.println("Owner has been deleted successfully.");
					break;
				default:
					System.out.println("Invalid option entered.");
				}
			} while (InputUtil.wantToContinue(sc));

		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
	}
}
