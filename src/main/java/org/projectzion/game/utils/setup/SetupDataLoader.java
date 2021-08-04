package org.projectzion.game.utils.setup;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.projectzion.game.configs.security.InitialUsersConfig;
import org.projectzion.game.persitence.entities.security.Privilege;
import org.projectzion.game.persitence.entities.security.Role;
import org.projectzion.game.persitence.repositories.PrivilegeRepository;
import org.projectzion.game.persitence.repositories.RoleRepository;
import org.projectzion.game.persitence.repositories.UserRepository;
import org.projectzion.game.services.KeyValueService;
import org.projectzion.game.utils.converter.UserTO2UserConverter;
import org.projectzion.game.utils.json.serializer.DateDeserializer;
import org.projectzion.game.utils.json.serializer.DateSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    Logger logger = LoggerFactory.getLogger(SetupDataLoader.class);

    public static final String ROLE_SUPER_USER = "ROLE_SUPER_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";

    public static final String KV_SETUP_DATE = "KV_SETUP_DONE";





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

    @Autowired
    KeyValueService keyValueService;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try{
            if (isSetupDone()) {
                return;
            }

            // == create initial privileges
            Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
            Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

            Privilege writeSuperUser = createPrivilegeIfNotFound("WRITE_USERS_SUPER_USER");
            Privilege readSuperUser = createPrivilegeIfNotFound("READ_USERS_SUPER_USER");
            Privilege writeAdmin = createPrivilegeIfNotFound("WRITE_USERS_ADMIN");
            Privilege readAdmin = createPrivilegeIfNotFound("READ_USERS_USER");
            Privilege writeUser = createPrivilegeIfNotFound("WRITE_USERS_USER");
            Privilege readUser = createPrivilegeIfNotFound("READ_USERS_USER");

            // == create initial roles
            List<Privilege> super_userPrivileges = Arrays.asList(readPrivilege, writePrivilege,readSuperUser,writeAdmin,readAdmin,writeUser,readUser);
            createRoleIfNotFound(ROLE_SUPER_USER, super_userPrivileges);

            List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege,readAdmin,writeUser,readUser);
            createRoleIfNotFound(ROLE_ADMIN, adminPrivileges);

            List<Privilege> rolePrivileges = Arrays.asList(readPrivilege, writePrivilege, readUser);
            createRoleIfNotFound(ROLE_USER, rolePrivileges);

            initialUsersConfig.getInital_users().forEach(userTO ->{
                userRepository.save(userTO2UserConverter.convert(userTO));
            });

                saveSetupIsDone();
        }catch(Exception e)
        {
            logger.error("wtf setup", e);
        }

    }



    @Transactional
    void saveSetupIsDone() throws Exception{
        Date now = new Date();
        keyValueService.save(KV_SETUP_DATE,now);
    }

    private boolean isSetupDone() throws Exception {
        return keyValueService.read(KV_SETUP_DATE, Date.class) != null;
    }

    @Transactional
    protected Privilege createPrivilegeIfNotFound(String name) {
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    protected Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }

}