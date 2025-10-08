package com.hibernate.entity;

import java.io.Serializable;
import java.util.Objects;

// Composite Primary Key
public class OwnerPetPrimaryKey implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id;      // must match Owner.id 
    private int petId;  // must match Owner.petId
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPetId() {
		return petId;
	}

	public void setPetId(int petId) {
		this.petId = petId;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(id, petId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OwnerPetPrimaryKey other = (OwnerPetPrimaryKey) obj;
		return id == other.id && petId == other.petId;
	}

	@Override
	public String toString() {
		return "OwnerPetPrimaryKey [id=" + id + ", petId=" + petId + "]";
	}

}
