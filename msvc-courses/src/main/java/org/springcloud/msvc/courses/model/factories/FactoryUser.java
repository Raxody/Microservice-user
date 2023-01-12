package org.springcloud.msvc.courses.model.factories;

import org.springcloud.msvc.courses.model.dto.DTOUser;
import java.util.Map;

public class FactoryUser {

    private FactoryUser() {
    }

    public static DTOUser mapToUser(Map<String,String> user){
        return new DTOUser(
                Long.parseLong(user.get("id")),
                user.get("name"),
                user.get("email"),
                user.get("password"));
    }

}
