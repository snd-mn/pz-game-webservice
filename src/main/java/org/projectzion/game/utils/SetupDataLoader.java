package org.projectzion.game.utils;

import org.projectzion.game.configs.security.InitialUsersConfig;
import org.projectzion.game.persitence.entities.security.Privilege;
import org.projectzion.game.persitence.entities.security.Role;
import org.projectzion.game.persitence.repositories.PrivilegeRepository;
import org.projectzion.game.persitence.repositories.RoleRepository;
import org.projectzion.game.persitence.repositories.UserRepository;
import org.projectzion.game.utils.converter.UserTO2UserConverter;
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
    InitialUsersConfig initialUsersConfig;

    @Autowired
    UserTO2UserConverter userTO2UserConverter;

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

        initialUsersConfig.getInital_users().forEach(userTO ->{
            userRepository.save(userTO2UserConverter.convert(userTO));
        });

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