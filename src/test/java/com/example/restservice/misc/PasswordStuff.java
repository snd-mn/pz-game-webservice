package com.example.restservice.misc;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordStuff {


    @Test
    public void test_bcrypt(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.print(bCryptPasswordEncoder.encode("YSNfChT3I6s4aSxDIo6E"));
    }
}
