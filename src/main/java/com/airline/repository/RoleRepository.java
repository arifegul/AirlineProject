package com.airline.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airline.model.ERole;
import com.airline.model.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

	Optional<Role> findByName(ERole name);


}
