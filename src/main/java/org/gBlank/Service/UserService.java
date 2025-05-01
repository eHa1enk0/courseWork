package org.gBlank.Service;

import org.gBlank.Entity.Request;
import org.gBlank.Entity.User;

public class UserService {

    public boolean emailValid(String email) {
        return email.contains("@") && email.contains(".");
    }

    
}
