package com.hibernate.entity;

import java.time.*;

import org.hibernate.annotations.NaturalId;

import com.hibernate.enums.Gender;
import com.hibernate.enums.PetType;

import jakarta.persistence.*;

@Entity
@Table(name = "owner_table")
public class Owner {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id; // database primary key (surrogate key)
	
	@Column(name = "first_name", nullable = false)
	private String firstName;
	@Column(name = "last_name", nullable = false)
	private String lastName;
	@Enumerated(value = EnumType.STRING)
	@Column(nullable = false)
	private Gender gender;
	@Column(nullable = false)
	private String city;
	@Column(nullable = false)
	private String state;

	// Natural ID are unique in nature and but it can be null and also when we have
	// 2 Natural ID it won't automatically provide unique.
	// Hibernate-specific annotation (not standard JPA).
	@NaturalId(mutable = true)
	@Column(name = "mobile_number", nullable = false, unique = true, length = 10)
	private String mobileNumber; // business identifier

	// Natural ID are unique in nature and but it can be null
	@NaturalId(mutable = true)
	@Column(name = "email_id", nullable = false,unique = true)
	private String emailId;

	@Column(name = "pet_id", nullable = false)
	private int petId;
	@Column(name = "pet_name", nullable = false)
	private String petName;
	@Column(name = "pet_date_of_birth", nullable = false)
	private LocalDate petBirthDate;
	@Enumerated(value = EnumType.STRING)
	@Column(name = "pet_gender", nullable = false)
	private Gender petGender;
	@Enumerated(value = EnumType.STRING)
	@Column(name = "pet_type", nullable = false)
	private PetType petType;

	// setId() is removed
	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public int getPetId() {
		return petId;
	}

	public void setPetId(int petId) {
		this.petId = petId;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public LocalDate getPetBirthDate() {
		return petBirthDate;
	}

	public void setPetBirthDate(LocalDate petBirthDate) {
		this.petBirthDate = petBirthDate;
	}

	public Gender getPetGender() {
		return petGender;
	}

	public void setPetGender(Gender petGender) {
		this.petGender = petGender;
	}

	public PetType getPetType() {
		return petType;
	}

	public void setPetType(PetType petType) {
		this.petType = petType;
	}

	@Override
	public String toString() {
		return "Owner [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", city=" + city + ", state=" + state + ", mobileNumber=" + mobileNumber + ", emailId=" + emailId
				+ ", petId=" + petId + ", petName=" + petName + ", petBirthDate=" + petBirthDate + ", petGender="
				+ petGender + ", petType=" + petType + "]";
	}
}