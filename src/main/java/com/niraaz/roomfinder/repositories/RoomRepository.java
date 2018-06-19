package com.niraaz.roomfinder.repositories;

import com.niraaz.roomfinder.entities.Room;
import com.niraaz.roomfinder.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {

	List<Room> findRoomsByRenter(User renter);

	List<Room> findRoomsByRenterNotIn( User renter );

}
