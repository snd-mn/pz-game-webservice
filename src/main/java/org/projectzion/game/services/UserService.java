package org.projectzion.game.services;

import org.projectzion.game.persitence.entities.security.Role;
import org.projectzion.game.persitence.entities.security.User;
import org.projectzion.game.persitence.repositories.RoleRepository;
import org.projectzion.game.persitence.repositories.UserRepository;
import org.projectzion.game.tos.UserTO;
import org.projectzion.game.utils.converter.UserTO2UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserTO2UserConverter userTO2UserConverter;


    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User createUser(UserTO userTO){
        //TODO check priviliges
        userTO.setPassword(passwordEncoder.encode(userTO.getPassword()));
        User user = userTO2UserConverter.convert(userTO);
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

    public User updateUser(UserTO userTO) {
        //TODO check priviliges
        userTO.setPassword(passwordEncoder.encode(userTO.getPassword()));
        User repoUser = userRepository.findById(userTO.getId()).get();
        //TODO roles,enabled and so on
        repoUser.setEmail(userTO.getEmail());
        repoUser.setPassword(userTO.getPassword());
        repoUser.setFirstName(userTO.getFirstName());
        repoUser.setLastName(userTO.getLastName());
        return userRepository.save(repoUser);
    }

    public void deleteUser(Long id) {
        //TODO check priviliges
        userRepository.delete(getUserById(id).get());
    }

    public User findById(Long id) {
        return userRepository.findById(id).get();
    }
}