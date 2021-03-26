package com.example.restservice.persitence.repositories;

import com.example.restservice.persitence.entities.User;
import com.example.restservice.persitence.entities.UserReward;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRewardRepository extends CrudRepository<UserReward, Long> {

}
