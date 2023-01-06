package org.springcloud.msvc.users.controllers;

import org.springcloud.msvc.users.model.FactoryUser;
import org.springcloud.msvc.users.model.dto.DTOUser;
import org.springcloud.msvc.users.model.entity.User;
import org.springcloud.msvc.users.model.ValidatorParam;
import org.springcloud.msvc.users.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public ResponseEntity<User> findUser(@PathVariable("id") Long id ){
        return userService
                .findUserById(id)
                .map(user -> ResponseEntity.status(HttpStatus.OK).body(user))
                .orElse( ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody DTOUser dtoUser, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return ValidatorParam.getErrors(bindingResult);
        }
        return bindingResult.hasErrors() ?
                ValidatorParam.getErrors(bindingResult) :
                ResponseEntity.status(HttpStatus.OK).body(userService.save(FactoryUser.user(dtoUser)));
    }


    @PutMapping("/edit")
    public ResponseEntity<?> edit(@Valid @RequestBody DTOUser dtoUser, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return ValidatorParam.getErrors(bindingResult);
        }
        return userService.update(FactoryUser.user(dtoUser)) != null ?
                ResponseEntity.status(HttpStatus.ACCEPTED).body(dtoUser) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id , BindingResult bindingResult){
        return userService.delete(id) ? new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK) :
                new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_FOUND);
    }
}
