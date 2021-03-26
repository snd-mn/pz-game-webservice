package com.example.restservice.persitence.repositories;

import com.example.restservice.persitence.entities.TargetSystem;
import com.example.restservice.persitence.entities.User;
import com.example.restservice.persitence.entities.UserNode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserNodeRepository extends CrudRepository<UserNode, Long> {

}
