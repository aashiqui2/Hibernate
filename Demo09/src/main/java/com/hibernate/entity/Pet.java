package com.hibernate.entity;


import java.time.LocalDate;
import com.hibernate.enums.Gender;
import com.hibernate.enums.PetType;
import jakarta.persistence.*;

@Entity
@Table(name = "pet_table")
public class Pet {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	@Column(nullable = false)
	private String name;
	@Column(name = "date_of_birth", nullable = false)
	private LocalDate birthDate;
	@Enumerated(value = EnumType.STRING)
	@Column(nullable = false)
	private Gender gender;
	@Enumerated(value = EnumType.STRING)
	@Column(nullable = false)
	private PetType type;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public PetType getType() {
		return type;
	}

	public void setType(PetType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Pet [id=" + id + ", name=" + name + ", birthDate=" + birthDate + ", gender=" + gender + ", type=" + type
				+ "]";
	}
}

