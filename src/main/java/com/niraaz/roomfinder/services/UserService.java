package com.niraaz.roomfinder.services;

import com.niraaz.roomfinder.entities.Role;
import com.niraaz.roomfinder.entities.User;
import com.niraaz.roomfinder.repositories.RoleRepository;
import com.niraaz.roomfinder.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	public void createUser(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(  );
		user.setPassword( encoder.encode( user.getPassword() ) );

		Role role = new Role( "USER" );
		List<Role> roles = new ArrayList<>( );
		roles.add( role );
		user.setRoles( roles );

		if(!roleRepository.existsById( role.getName() )){
			roleRepository.save( role );
		}

		userRepository.save( user );
	}

	public Optional<User> findById( int id) {
		return userRepository.findById( id );
	}

	public boolean isUserPresent( String username, String email ) {
		if(userRepository.findByUsernameOrEmail( username, email ).size() > 0) {
			return true;
		}
		return false;
	}

	public User findbyUsername( String username ) {
		return userRepository.findByUsername( username );
	}
}
