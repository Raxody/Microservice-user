package org.springcloud.msvc.users.repositories;

import org.springcloud.msvc.users.model.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {

}
