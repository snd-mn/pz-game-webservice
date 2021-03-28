package com.example.restservice.persitence.repositories;

import com.example.restservice.persitence.entities.NodeType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeTypeRepository extends CrudRepository<NodeType, Long> {

}
