package org.projectzion.game.persitence.repositories;

import org.projectzion.game.persitence.entities.security.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    Privilege findByName(String name);

    void delete(Privilege privilege);

}
