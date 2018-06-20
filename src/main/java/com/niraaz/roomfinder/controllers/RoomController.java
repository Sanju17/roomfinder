package com.niraaz.roomfinder.controllers;

import com.niraaz.roomfinder.entities.Room;
import com.niraaz.roomfinder.entities.User;
import com.niraaz.roomfinder.services.RoomService;
import com.niraaz.roomfinder.services.UserService;
import com.niraaz.roomfinder.utils.RFConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Map;
import java.util.Optional;

@Controller
public class RoomController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoomService roomService;

	@ModelAttribute("roomTypeMap")
	public Map<String, String> roomTypeMap() {
		return RFConstants.roomTypeMap;
	}

	@GetMapping("/addRoom")
	public String addRoomForm(Model model) {
		model.addAttribute( "room", new Room( ) );
		return "views/addRoom";
	}

	@PostMapping("/addRoom")
	public String saveRoom( @Valid Room room, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "views/addForm";
		}

		Optional<User> userOptional = userService.findById( 1 );
		if(userOptional.isPresent()) {
			User user = userOptional.get();
			roomService.save( room, user );
			return "redirect:/";
		}else {
			return "views/addForm";
		}
	}

	@GetMapping("/roomList")
	public String getRoomsOfRenter( Model model, Principal principal ) {

		User user = getUserByUsername( principal.getName() );
		model.addAttribute( "roomList", roomService.findRoomByRenter( user ) );
		return "views/roomList";

	}

	@GetMapping("/update")
	public String getRoomUpdateForm( @RequestParam("roomId") int roomId, Model model ) {

		Optional<Room> roomOptional = roomService.findById( roomId );

		if(roomOptional.isPresent()) {
			Room room = roomOptional.get();
			model.addAttribute( "room", room );
			return "views/updateRoom";
		}

		return "redirect:/roomList";

	}

	@PostMapping("/update")
	public String updateRoomInfo( @Valid @ModelAttribute("room") Room room, BindingResult bindingResult, Model model, Principal principal ) {

		if(bindingResult.hasErrors()) {
			return "views/updateRoom";
		}

		User user = getUserByUsername( principal.getName() );
		roomService.save( room, user );
		return "redirect:/roomList";
	}

	private User getUserByUsername(String username) {
		return userService.findbyUsername(username);
	}

}
