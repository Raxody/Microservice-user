package org.springcloud.msvc.courses.repositories;

import org.springcloud.msvc.courses.model.entity.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Long> {

}
