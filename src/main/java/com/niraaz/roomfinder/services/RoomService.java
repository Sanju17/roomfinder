package com.niraaz.roomfinder.services;

import com.niraaz.roomfinder.entities.Room;
import com.niraaz.roomfinder.entities.User;
import com.niraaz.roomfinder.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

	@Autowired
	private RoomRepository roomRepository;

	public void save( Room room, User user) {
		room.setRenter( user );
		roomRepository.save( room );
	}

	public List<Room> findRoomByRenter( User renter ){
		return roomRepository.findRoomsByRenter( renter );
	}

	public List<Room> findAllRoomForRecommendation( User renter ) {
		return roomRepository.findRoomsByRenterNotIn( renter );
	}

	public Optional<Room> findById( int id ){
		return roomRepository.findById( id );
	}

}
