package org.projectzion.game.utils.converter;

import org.projectzion.game.tos.UserTO;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
@ConfigurationPropertiesBinding
public class String2UserTOConverter implements Converter<String, UserTO> {

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserTO convert(String s) {
        UserTO userTO = new UserTO();

        String[] splits = s.split(";");
        userTO.setEmail(splits[0]);
        userTO.setPassword(passwordEncoder().encode(splits[1]));

        //TODO REMOVE ON OAUTH2
        userTO.setPasswordClear(splits[1]);

        //TODO collection in properties
        Collection<String> roles = new ArrayList<>();
        roles.add(splits[2]);
        userTO.setRoles(roles);

        return userTO;
    }
}
