package com.niraaz.roomfinder.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class User {

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	@Column( name = "id" )
	private Integer id;

	@Column( name = "first_name" )
	@NotEmpty
	private String firstname;

	@Column( name = "last_name" )
	@NotEmpty
	private String lastname;

	@Column( name = "user_name", unique = true )
	@Size(min = 6)
	private String username;

	@Column( name = "phone" )
	@NotEmpty
	private String phone;

	@Email
	@Column( name = "email", unique = true )
	@NotEmpty
	private String email;

	@Column( name = "password" )
	@Size( min = 6 )
	private String password;

	@OneToMany( mappedBy = "renter" )
	private List<Room> roomsToRent;

	@ManyToMany
	@JoinTable( name = "user_role", joinColumns = { @JoinColumn( name = "user_id", referencedColumnName = "id" ) }, inverseJoinColumns = { @JoinColumn( name = "role_name", referencedColumnName = "name" ) } )
	private List<Role> roles;

	public User( ) {
	}

	public User( String firstname, String lastname, String username, String phone, String email, String password ) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.phone = phone;
		this.email = email;
		this.password = password;
	}

	public Integer getId( ) {
		return id;
	}

	public void setId( Integer id ) {
		this.id = id;
	}

	public String getFirstname( ) {
		return firstname;
	}

	public void setFirstname( String firstname ) {
		this.firstname = firstname;
	}

	public String getLastname( ) {
		return lastname;
	}

	public void setLastname( String lastname ) {
		this.lastname = lastname;
	}

	public String getUsername( ) {
		return username;
	}

	public void setUsername( String username ) {
		this.username = username;
	}

	public String getPhone( ) {
		return phone;
	}

	public void setPhone( String phone ) {
		this.phone = phone;
	}

	public String getEmail( ) {
		return email;
	}

	public void setEmail( String email ) {
		this.email = email;
	}

	public String getPassword( ) {
		return password;
	}

	public void setPassword( String password ) {
		this.password = password;
	}

	public List<Role> getRoles( ) {
		return roles;
	}

	public void setRoles( List<Role> roles ) {
		this.roles = roles;
	}

	public List<Room> getRoomsToRent( ) {
		return roomsToRent;
	}

	public void setRoomsToRent( List<Room> roomsToRent ) {
		this.roomsToRent = roomsToRent;
	}

}