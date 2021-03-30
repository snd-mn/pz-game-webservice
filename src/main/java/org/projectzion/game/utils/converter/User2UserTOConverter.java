package org.projectzion.game.utils.converter;

import org.projectzion.game.persitence.entities.security.User;
import org.projectzion.game.persitence.repositories.RoleRepository;
import org.projectzion.game.tos.UserTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class User2UserTOConverter implements Converter<User, UserTO> {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public UserTO convert(User user) {
        UserTO userTO = new UserTO();
        userTO.setId(user.getId());
        userTO.setEmail(user.getEmail());
        userTO.setPassword(user.getPassword());
        userTO.setFirstName(user.getFirstName());
        userTO.setLastName(user.getLastName());
        userTO.setEnabled(user.isEnabled());

        Collection<String> roles = new ArrayList<>();
        user.getRoles().forEach(role ->{
            roles.add(role.getName());
        });
        userTO.setRoles(roles);

        return userTO;
    }
}
