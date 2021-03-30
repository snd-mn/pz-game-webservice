package org.projectzion.game.persitence.repositories;

import org.projectzion.game.persitence.entities.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

    void delete(Role role);

}
