package org.springcloud.msvc.users.model.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "msvc-courses", url = "localhost:8002/msvc-courses")
public interface CourseClientRest {

    @DeleteMapping("/delete-user-of-course/{id}")
    void deleteCourseUser(@PathVariable Long id);
}
