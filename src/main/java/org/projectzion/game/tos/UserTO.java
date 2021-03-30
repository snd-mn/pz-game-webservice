package org.projectzion.game.tos;

import lombok.Data;

import java.util.Collection;

@Data
public class UserTO {
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    //may set this someday to false for preventing the user to start in a unset game environment
    private boolean enabled = true;
    private Collection<String> roles;
}
