package com.example.restservice.persitence.repositories;

import com.example.restservice.persitence.entities.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

    void delete(Role role);

}
