package org.springcloud.msvc.users.model.factories;

import org.springcloud.msvc.users.model.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class FactoryResponseEntityMap {

    private FactoryResponseEntityMap() {
    }

    public static ResponseEntity<Map<String, String>> toMap(User user, HttpStatus httpStatus){
        Map<String,String>  responseUser = new HashMap<>();
        responseUser.put("id",String.valueOf(user.getId()));
        responseUser.put("name",user.getName());
        responseUser.put("email",user.getEmail());
        responseUser.put("password", user.getPassword());
        return new ResponseEntity<>(responseUser,httpStatus);
    }

}
