package br.edu.atitus.api_example.entities;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_user")
public class UserEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.UUID)
	private UUID id;
	
	@Column(length = 100, nullable = false)
	private String name;
	
	@Column(length = 100, nullable = false)
	private String email;
	
	@JsonIgnore
	@Column(length = 100, nullable = false) 
	private String password;
	
	@Enumerated(EnumType.ORDINAL)
	private TypeUser type;

	public UUID getId() {
		
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public TypeUser getType() {
		return type;
	}

	public void setType(TypeUser type) {
		this.type = type;
	}
	
	
	
	
}
