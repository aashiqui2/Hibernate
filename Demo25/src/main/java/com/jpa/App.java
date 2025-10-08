package com.jpa;

import jakarta.persistence.Persistence;

public class App {
	public static void main(String[] args) {
		Persistence.createEntityManagerFactory("Petistaan");
	}

}
