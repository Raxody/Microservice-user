package org.springcloud.msvc.courses.model.factories;

import org.springcloud.msvc.courses.model.dto.DTOUser;
import org.springcloud.msvc.courses.model.entity.Course;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class FactoryResponseEntityMap {

    private FactoryResponseEntityMap() {
    }

    public static ResponseEntity<Map<String, String>> toMap(Course course, HttpStatus httpStatus){
        Map<String,String>  responseCourse = new HashMap<>();
        responseCourse.put("id",String.valueOf(course.getId()));
        responseCourse.put("name",course.getName());
        return new ResponseEntity<>(responseCourse,httpStatus);
    }

    public static ResponseEntity<Map<String, String>> userToMap(DTOUser user, HttpStatus httpStatus){
        Map<String,String>  responseUser = new HashMap<>();
        responseUser.put("id",String.valueOf(user.getId()));
        responseUser.put("name",user.getName());
        responseUser.put("email",user.getEmail());
        responseUser.put("password", user.getPassword());
        return new ResponseEntity<>(responseUser,httpStatus);
    }

}
