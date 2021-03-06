package org.projectzion.game.utils;

import java.util.ArrayList;
import java.util.Collection;

import org.projectzion.game.persitence.entities.security.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
public class UserPrincipal  implements UserDetails {
    private User user;

    public UserPrincipal(User user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    //TODO check if columns get generated -> should not
    @Override
    public String getUsername() {
        return this.getUser().getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        //TODO
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        //TODO
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        //TODO
        return true;
    }

    @Override
    public boolean isEnabled() {
        //TODO
        return true;
    }
}
