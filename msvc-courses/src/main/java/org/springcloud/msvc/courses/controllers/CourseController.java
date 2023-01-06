package org.springcloud.msvc.courses.controllers;

import org.springcloud.msvc.courses.model.entity.Course;
import org.springcloud.msvc.courses.services.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

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
                .findCourseById(id)
                .map(course -> ResponseEntity.ok().body(course))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/save")
    public ResponseEntity<Course> save(@RequestBody Course course){
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.save(course));
    }

    @PutMapping("/update")
    public ResponseEntity<Course> update(@RequestBody Course course){
        return courseService.update(course) != null ?
                ResponseEntity.status(HttpStatus.ACCEPTED).body(course) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        return courseService.delete(id) ?
                ResponseEntity.status(HttpStatus.OK).build():
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
