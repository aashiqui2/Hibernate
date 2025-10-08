package com.hibernate.exception;

public class DuplicateOwnerPetCombinationException extends Exception {
	private static final long serialVersionUID = 1L;

	public DuplicateOwnerPetCombinationException(String message) {
		super(message);
	}
}
