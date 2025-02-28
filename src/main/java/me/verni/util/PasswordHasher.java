package me.verni.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHasher {

    public static String hash(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }
}
