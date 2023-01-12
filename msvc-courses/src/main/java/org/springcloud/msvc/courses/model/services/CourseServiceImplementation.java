package org.springcloud.msvc.courses.model.services;

import org.springcloud.msvc.courses.model.clients.UserClientRest;
import org.springcloud.msvc.courses.model.dto.DTOUser;
import org.springcloud.msvc.courses.model.entity.Course;
import org.springcloud.msvc.courses.model.entity.CourseUser;
import org.springcloud.msvc.courses.model.factories.FactoryUser;
import org.springcloud.msvc.courses.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class CourseServiceImplementation implements CourseService{

    private final CourseRepository courseRepository;

    private final UserClientRest userClientRest;

    public CourseServiceImplementation(CourseRepository courseRepository, UserClientRest userClientRest) {
        this.courseRepository = courseRepository;
        this.userClientRest = userClientRest;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Course> getAll() {
        return (List<Course>) courseRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Course> findCourseById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    @Transactional
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    @Transactional
    public Course update(Course course) {
        return findCourseById(course.getId()).isPresent() ?
                courseRepository.save(course) : null;
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return findCourseById(id)
                .map(course -> {
                    courseRepository.deleteById(id);
                    return Boolean.TRUE;})
                .orElse(Boolean.FALSE);
    }

    @Override
    @Transactional
    public Optional<DTOUser> assignUser(DTOUser user, Long idCourse) {
        Optional<Course> course = courseRepository.findById(idCourse);
        if(course.isPresent()){
            DTOUser userMsvc = userClientRest.userById(user.getId());
            CourseUser courseUser = new CourseUser();
            courseUser.setUserId(userMsvc.getId());
            course.get().addCourseUser(courseUser);
            courseRepository.save(course.get());
            return Optional.of(userMsvc);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<DTOUser> createUser(DTOUser user, Long idCourse) {
        Optional<Course> course = courseRepository.findById(idCourse);
        if(course.isPresent()){
           Map<String, String>  mapUserMsvc = userClientRest.save(user);
            DTOUser userMsvc = FactoryUser.mapToUser(mapUserMsvc);
            CourseUser courseUser = new CourseUser();
            courseUser.setUserId(userMsvc.getId());
            course.get().addCourseUser(courseUser);
            courseRepository.save(course.get());
            return Optional.of(userMsvc);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<DTOUser> dropUser(DTOUser user, Long idCourse) {
        Optional<Course> course = courseRepository.findById(idCourse);
        if(course.isPresent()){
            DTOUser userMsvc = userClientRest.userById(user.getId());
            CourseUser courseUser = new CourseUser();
            courseUser.setUserId(userMsvc.getId());
            course.get().deleteCourseUser(courseUser);
            courseRepository.save(course.get());
            return Optional.of(userMsvc);
        }
        return Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Course> findUsersByIdInCourse(Long idCourse) {
        Optional<Course> course = courseRepository.findById(idCourse);
        if(course.isPresent()){
            course.get().setUsers(userClientRest
                    .findAllUsersByIdsInCourse(course
                            .get()
                            .getCourseUser()
                            .stream()
                            .map(CourseUser::getUserId)
                            .toList()));
            return Optional.of(course.get());
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public void deleteCourseUserById(long userId) {
        courseRepository.deleteCourseUserById(userId);
    }
}
