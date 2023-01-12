package org.springcloud.msvc.users.model.factories;

import org.springcloud.msvc.users.model.dto.DTOUser;
import org.springcloud.msvc.users.model.entity.User;

public class FactoryUser {

    private FactoryUser() {
    }

    public static User toAddUser(DTOUser dtoUser){
        return new User(dtoUser.getName(),
                dtoUser.getEmail(),
                dtoUser.getPassword());
    }

    public static User toUpdateUser(DTOUser dtoUser){
        return new User(dtoUser.getId(),
                dtoUser.getName(),
                dtoUser.getEmail(),
                dtoUser.getPassword());
    }
}
