package org.springcloud.msvc.courses.configuration;

import org.springcloud.msvc.courses.model.clients.UserClientRest;
import org.springcloud.msvc.courses.model.services.CourseServiceImplementation;
import org.springcloud.msvc.courses.repositories.CourseRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public CourseServiceImplementation courseServiceImplementation(CourseRepository courseRepository, UserClientRest userClientRest){
        return new CourseServiceImplementation(courseRepository,userClientRest);
    }


}
