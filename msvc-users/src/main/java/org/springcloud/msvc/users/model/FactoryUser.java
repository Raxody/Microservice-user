package org.springcloud.msvc.users.model;

import org.springcloud.msvc.users.model.dto.DTOUser;
import org.springcloud.msvc.users.model.entity.User;

public class FactoryUser {

    private FactoryUser() {
    }

    public static User user(DTOUser dtoUser){
        return new User(dtoUser.getName(),
                dtoUser.getEmail(),
                dtoUser.getPassword());
    }
}
