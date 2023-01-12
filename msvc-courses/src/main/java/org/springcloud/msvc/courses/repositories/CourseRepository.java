package org.springcloud.msvc.courses.repositories;

import org.springcloud.msvc.courses.model.entity.Course;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Long> {

    @Modifying //this notation is used if I want to modify tuples in the bd
    @Query("DELETE FROM CourseUser AS cu WHERE cu.userId=?1")
    void deleteCourseUserById(long userId);

}
