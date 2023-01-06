package org.springcloud.msvc.courses.services;

import org.springcloud.msvc.courses.model.entity.Course;
import org.springcloud.msvc.courses.repositories.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImplementation implements CourseService{

    private final CourseRepository courseRepository;

    public CourseServiceImplementation(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
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
}
