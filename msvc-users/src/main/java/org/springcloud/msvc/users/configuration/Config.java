package org.springcloud.msvc.users.configuration;


import org.springcloud.msvc.users.model.clients.CourseClientRest;
import org.springcloud.msvc.users.model.services.UserServiceImplementation;
import org.springcloud.msvc.users.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public UserServiceImplementation userServiceImplementation(UserRepository userRepository, CourseClientRest courseClientRest){
        return new UserServiceImplementation(userRepository, courseClientRest);
    }
}
