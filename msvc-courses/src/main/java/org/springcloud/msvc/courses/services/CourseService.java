package org.springcloud.msvc.courses.services;

import org.springcloud.msvc.courses.model.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Course> getAll();
    Optional<Course> findCourseById(Long id);
    Course save(Course course);
    Course update(Course course);
    boolean delete(Long id);
}
