package com.example.restservice.persitence.repositories;

import com.example.restservice.persitence.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}