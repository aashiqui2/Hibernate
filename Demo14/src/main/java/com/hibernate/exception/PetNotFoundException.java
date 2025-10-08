package com.hibernate.exception;

public class PetNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public PetNotFoundException(String message) {
		super(message);
	}
}
