package com.niraaz.roomfinder.repositories;

import com.niraaz.roomfinder.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findByUsernameOrEmail(String username, String email);

}
