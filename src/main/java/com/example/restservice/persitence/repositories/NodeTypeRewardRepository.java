package com.example.restservice.persitence.repositories;

import com.example.restservice.persitence.entities.NodeType;
import com.example.restservice.persitence.entities.NodeTypeReward;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeTypeRewardRepository extends CrudRepository<NodeTypeReward, Long> {

}
