package com.example.restservice.persitence.repositories;

import com.example.restservice.persitence.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserNodeRepository extends CrudRepository<User, Long> {

}
