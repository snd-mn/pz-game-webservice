package com.example.restservice.persitence.repositories;

import com.example.restservice.configs.auth.AuthFromPropertiesConfiguration;
import com.example.restservice.persitence.entities.security.Privilege;
import com.example.restservice.persitence.entities.security.Role;
import com.example.restservice.persitence.entities.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    public static final String ROLE_SUPER_USER = "ROLE_SUPER_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";
    private boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    AuthFromPropertiesConfiguration authFromPropertiesConfiguration;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        // == create initial privileges
        Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE"); 

        // == create initial roles
        List<Privilege> super_userPrivileges = Arrays.asList(readPrivilege, writePrivilege);
        createRoleIfNotFound(ROLE_SUPER_USER, super_userPrivileges);

        List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);
        createRoleIfNotFound(ROLE_ADMIN, adminPrivileges);

        List<Privilege> rolePrivileges = new ArrayList<>();
        createRoleIfNotFound(ROLE_USER, rolePrivileges);

        authFromPropertiesConfiguration.getInital_users().forEach(user ->{
            Collection<Role> roles = new ArrayList<>();
            user.getRoles().forEach(role -> {
                Role roleFromRepo = roleRepository.findByName(role.getName());
                roles.add(roleFromRepo);
            });
            user.setFirstName("");
            user.setLastName("");
            user.setEmail(user.getEmail());
            //allready encoded
            user.setPassword(user.getPassword());
            user.setRoles(roles);
            user.setEnabled(true);
            userRepository.save(user);
        });

//        Role basicRole = roleRepository.findByName("ROLE_USER");
//        User basicUser = new User();
//        basicUser.setFirstName("User");
//        basicUser.setLastName("User");
//        basicUser.setEmail("user@test.com");
//        basicUser.setPassword(passwordEncoder.encode("user"));
//        basicUser.setRoles(Arrays.asList(basicRole));
//        basicUser.setEnabled(true);
//        userRepository.save(basicUser);

        alreadySetup = true;
    }

    @Transactional
    private Privilege createPrivilegeIfNotFound(String name) {
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    private Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }

}