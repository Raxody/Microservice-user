package org.springcloud.msvc.users.services;

import org.springcloud.msvc.users.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAll();
    Optional<User> findUserById(Long id);
    User save(User user);
    User update(User user);
    boolean delete(Long id);


}
