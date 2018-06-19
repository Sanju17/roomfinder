package com.niraaz.roomfinder;

import com.niraaz.roomfinder.entities.Room;
import com.niraaz.roomfinder.entities.User;
import com.niraaz.roomfinder.services.RoomService;
import com.niraaz.roomfinder.services.UserService;
import com.niraaz.roomfinder.utils.RFConstants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoomfinderApplicationTests {

	@Autowired
	private UserService userService;

	@Autowired
	private RoomService roomService;

	@Before
	public void initDb(){
		System.out.println( "InitDb();" );
		{
			User newUser = new User( "Sangharsha", "Chaulagain", "csangharsha", "9803608089", "sangharsha.chaulagain@gmail.com", "guess_it" );
			userService.createUser( newUser );
		}
		{
			User newUser = new User( "Sumit", "Ghising", "gsumit", "9803989009", "sumit.ghising@gmail.com", "123456" );
			userService.createUser( newUser );
		}

		Room room = new Room( RFConstants.ROOM_TYPE_SINGLE, 7000.0, RFConstants.UPLOAD_DIRECTORY + RFConstants.IMAGE_NOT_AVAILABLE, "Single Room, sunlight, top floor", "Imadole, Latitpur", "Water,Parking" );
		Optional<User> optionalUser = userService.findById( 1 );
		optionalUser.ifPresent( user -> roomService.save( room, user )  );

	}


	public void testUser( ) {
		System.out.println( "testUser();" );
		Optional<User> optionalUser = userService.findById( 1 );
		optionalUser.ifPresent( user -> Assert.assertNotNull( user ) );
	}

	@Test
	public void testRoom( ) {
		System.out.println( "testRoom();" );
		Optional<User> optionalUser = userService.findById( 1 );
		optionalUser.ifPresent( user -> {

			List<Room> rooms = roomService.findRoomByRenter( user );
			Assert.assertNotNull( rooms );

			List<Room> rooms1 = roomService.findAllRoomForRecommendation( user );
			Assert.assertEquals( rooms1.size(), 0 );

		} );

	}

}
