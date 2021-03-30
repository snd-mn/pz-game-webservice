package org.projectzion.game.services;

import org.projectzion.game.persitence.entities.security.Role;
import org.projectzion.game.persitence.entities.security.User;
import org.projectzion.game.persitence.repositories.RoleRepository;
import org.projectzion.game.persitence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User createUser(User user){
        List realRoles = new ArrayList();
        user.getRoles().forEach(role -> {
            Role repRole = roleRepository.findByName(role.getName());
            if(repRole!=null)
            {
                realRoles.add(repRole);
            }
        });
        user.setRoles(realRoles);
        User userBack = this.userRepository.save(user);
        return userBack;
    }


    public Optional<User> getUserById(Long id){
        return this.userRepository.findById(id);
    }
}