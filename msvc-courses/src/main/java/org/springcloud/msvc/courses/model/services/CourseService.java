package org.springcloud.msvc.courses.model.services;

import org.apache.tomcat.jni.User;
import org.springcloud.msvc.courses.model.dto.DTOUser;
import org.springcloud.msvc.courses.model.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Course> getAll();
    Optional<Course> findCourseById(Long id);
    Course save(Course course);
    Course update(Course course);
    boolean delete(Long id);

    Optional<DTOUser> assignUser(DTOUser user, Long idCourse);
    Optional<DTOUser> createUser(DTOUser user, Long idCourse);
    Optional<DTOUser> dropUser(DTOUser user, Long idCourse);

    Optional<Course> findUsersByIdInCourse(Long idCourse);

    void deleteCourseUserById(long userId);

}
