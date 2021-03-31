package org.projectzion.game.scoped.request;

import org.projectzion.game.persitence.entities.security.Role;
import org.projectzion.game.utils.UserPrincipal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class RequestScoped {

    @Bean
    @Scope("request")
    public UserPrincipal currentUserPrincipal() {
        UserPrincipal userPrincipal = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.isAuthenticated())
        {
            String currentUserName = authentication.getName();
            userPrincipal = (UserPrincipal) authentication.getPrincipal();
        }

        return userPrincipal;
    }
}
