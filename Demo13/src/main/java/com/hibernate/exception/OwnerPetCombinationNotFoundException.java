package com.hibernate.exception;

public class OwnerPetCombinationNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public OwnerPetCombinationNotFoundException(String message) {
		super(message);
	}
}
