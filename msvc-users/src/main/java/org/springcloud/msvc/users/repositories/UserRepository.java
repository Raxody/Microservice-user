package org.springcloud.msvc.users.repositories;

import org.springcloud.msvc.users.model.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {

    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.email=?1")
    Optional<User> findByEmailWithQuery(String email);
}
