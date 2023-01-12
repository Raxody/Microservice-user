package org.springcloud.msvc.users.controllers;

import org.springcloud.msvc.users.model.factories.FactoryResponseEntityMap;
import org.springcloud.msvc.users.model.factories.FactoryUser;
import org.springcloud.msvc.users.model.dto.DTOUser;
import org.springcloud.msvc.users.model.entity.User;
import org.springcloud.msvc.users.model.ValidatorParam;
import org.springcloud.msvc.users.model.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable("id") Long id ){
        return userService
                .findUserById(id)
                .map(user -> ResponseEntity.status(HttpStatus.OK).body(user))
                .orElse( ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/save")
    public ResponseEntity<Map<String, String>> save(@Valid @RequestBody DTOUser dtoUser, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return ValidatorParam.getErrors(bindingResult);
        }
        User user = userService.save(FactoryUser.toAddUser(dtoUser));
        return user == null ?
                ResponseEntity
                        .badRequest().body(Collections
                        .singletonMap("Warning","The email is already register, try with another please")) :
                FactoryResponseEntityMap.toMap(user,HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<Map<String, String>> edit(@Valid @RequestBody DTOUser dtoUser, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return ValidatorParam.getErrors(bindingResult);
        }
        User user = FactoryUser.toUpdateUser(dtoUser);
        if(!userService.findUserById(user.getId()).isPresent() ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return userService.update(user) != null ?
                FactoryResponseEntityMap.toMap(user,HttpStatus.OK) :
                ResponseEntity
                        .badRequest().body(Collections
                                .singletonMap("Warning","The email is already register, try with another please")) ;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id){
        return userService.delete(id) ? new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK) :
                new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/users-by-courses")
    public ResponseEntity<List<User>> findAllUsersByIdsInCourse(@RequestParam List<Long> idsUsers){
        return ResponseEntity.ok(userService.findAllUsersById(idsUsers));
    }
}
