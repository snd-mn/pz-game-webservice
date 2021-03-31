package org.projectzion.game.utils.setup;

import org.projectzion.game.configs.security.InitialUsersConfig;
import org.projectzion.game.persitence.repositories.UserRepository;
import org.projectzion.game.tos.UserTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

//TODO replace with key value table item
@Component
@Scope(value = WebApplicationContext.SCOPE_APPLICATION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SetupState {
    @Autowired
    UserRepository userRepository;

    @Autowired
    InitialUsersConfig initialUsersConfig;

    @Scope(value = WebApplicationContext.SCOPE_APPLICATION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public boolean isSetupDone(){
        UserTO userTO = initialUsersConfig.getInital_users().stream().iterator().next();
        return userRepository.findByEmail(userTO.getEmail()) != null;
    }
}
