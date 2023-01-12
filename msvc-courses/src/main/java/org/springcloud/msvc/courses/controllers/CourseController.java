package org.springcloud.msvc.courses.controllers;

import feign.FeignException;
import feign.FeignException.FeignClientException;
import org.springcloud.msvc.courses.model.ValidatorParam;
import org.springcloud.msvc.courses.model.dto.DTOCourse;
import org.springcloud.msvc.courses.model.dto.DTOUser;
import org.springcloud.msvc.courses.model.entity.Course;
import org.springcloud.msvc.courses.model.factories.FactoryCourse;
import org.springcloud.msvc.courses.model.factories.FactoryResponseEntityMap;
import org.springcloud.msvc.courses.model.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class CourseController {
    private static final String ERROR ="Error";
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping()
    public ResponseEntity<List<Course>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> findCourseById(@PathVariable("id") Long id){
        return courseService
                .findUsersByIdInCourse(id)
                .map(course -> ResponseEntity.ok().body(course))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/save")
    public ResponseEntity<Map<String,String>> save(@Valid @RequestBody DTOCourse dtoCourse, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ValidatorParam.getErrors(bindingResult);
        }
        return FactoryResponseEntityMap
                .toMap(courseService
                        .save(FactoryCourse
                                .toAddCourse(dtoCourse)),
                        HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String,String>> update(@Valid @RequestBody DTOCourse dtoCourse, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ValidatorParam.getErrors(bindingResult);
        }
        Course course = FactoryCourse.toUpdateCourse(dtoCourse);
        return courseService.update(course) != null ?
                FactoryResponseEntityMap.toMap(course,HttpStatus.ACCEPTED) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id){
        return courseService.delete(id) ?
                ResponseEntity.status(HttpStatus.OK).body(Boolean.TRUE):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(Boolean.FALSE);
    }

    @PutMapping("/assign-user/{idCourse}")
    public ResponseEntity<Map<String,String>> assignUser(@RequestBody DTOUser userToAssign,@PathVariable Long idCourse){
        Optional<DTOUser> user;
        try{
        user = courseService.assignUser(userToAssign,idCourse);
        }catch (FeignClientException feignClientException){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap(ERROR,
                    "The user does not exist by " +
                            "id or there is a communication error."  + feignClientException.getMessage()));
        }
        if(user.isPresent()){
            return FactoryResponseEntityMap.userToMap(user.get(),HttpStatus.CREATED);
        }
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/create-user/{idCourse}")
    public ResponseEntity<Map<String,String>> createUser(@RequestBody DTOUser userToAssign,@PathVariable Long idCourse){
        Optional<DTOUser> user;
        try{
            user = courseService.createUser(userToAssign,idCourse);
        }catch (FeignClientException feignClientException){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap(ERROR,
                    "User can't be created " +
                            "or there is a communication error."  + feignClientException.getMessage()));
        }
        if(user.isPresent()){
            return FactoryResponseEntityMap.userToMap(user.get(),HttpStatus.CREATED);
        }
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/delete-user/{idCourse}")
    public ResponseEntity<Map<String,String>> deleteUser(@RequestBody DTOUser userToAssign,@PathVariable Long idCourse){
        Optional<DTOUser> user;
        try{
            user = courseService.dropUser(userToAssign,idCourse);
        }catch (FeignClientException feignClientException){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap(ERROR,
                    "The user does not exist by " +
                            "id or there is a communication error."  + feignClientException.getMessage()));
        }
        if(user.isPresent()){
            return FactoryResponseEntityMap.userToMap(user.get(),HttpStatus.OK);
        }
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/delete-user-of-course/{id}")
    public ResponseEntity<?> deleteCourseUser(@PathVariable Long id){
        courseService.deleteCourseUserById(id);
        return ResponseEntity.noContent().build();
    }
}
