package org.projectzion.game.utils.converter;

import org.projectzion.game.persitence.entities.security.Role;
import org.projectzion.game.persitence.entities.security.User;
import org.projectzion.game.persitence.repositories.RoleRepository;
import org.projectzion.game.tos.UserTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class UserTO2UserConverter implements Converter<UserTO, User> {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public User convert(UserTO userTO) {
        User user = new User();
        user.setId(userTO.getId());
        user.setEmail(userTO.getEmail());
        user.setPassword(userTO.getPassword());
        user.setFirstName(userTO.getFirstName());
        user.setLastName(userTO.getLastName());
        user.setEnabled(userTO.isEnabled());
        Collection<Role> roles = new ArrayList<>();
        userTO.getRoles().forEach(strRole ->{
            Role role = roleRepository.findByName(strRole);
            if(role!= null)
            {
                roles.add(role);
            }
        });
        user.setRoles(roles);
        return user;
    }
}
