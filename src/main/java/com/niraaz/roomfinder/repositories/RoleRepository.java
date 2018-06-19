package com.niraaz.roomfinder.repositories;

import com.niraaz.roomfinder.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String > {
}
