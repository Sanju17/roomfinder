package com.niraaz.roomfinder.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "room_type")
	@NotEmpty
	private String roomType;

	@Column(name = "price")
	private Double price;

	private String image;

	private String description;

	private String location;

	private String facilities;

	@ManyToOne
	@JoinColumn(name = "renter_id")
	private User renter;

	public Room( ) {
	}

	public Room( String roomType, Double price, String image, String description, String location, String facilities ) {
		this.roomType = roomType;
		this.price = price;
		this.image = image;
		this.description = description;
		this.location = location;
		this.facilities = facilities;
	}

	public Integer getId( ) {
		return id;
	}

	public void setId( Integer id ) {
		this.id = id;
	}

	public String getRoomType( ) {
		return roomType;
	}

	public void setRoomType( String roomType ) {
		this.roomType = roomType;
	}

	public Double getPrice( ) {
		return price;
	}

	public void setPrice( Double price ) {
		this.price = price;
	}

	public String getImage( ) {
		return image;
	}

	public void setImage( String image ) {
		this.image = image;
	}

	public String getDescription( ) {
		return description;
	}

	public void setDescription( String description ) {
		this.description = description;
	}

	public String getLocation( ) {
		return location;
	}

	public void setLocation( String location ) {
		this.location = location;
	}

	public String getFacilities( ) {
		return facilities;
	}

	public void setFacilities( String facilities ) {
		this.facilities = facilities;
	}

	public User getRenter( ) {
		return renter;
	}

	public void setRenter( User renter ) {
		this.renter = renter;
	}

}
