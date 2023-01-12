package org.springcloud.msvc.users.model.services;

import org.springcloud.msvc.users.model.clients.CourseClientRest;
import org.springcloud.msvc.users.model.entity.User;
import org.springcloud.msvc.users.repositories.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class UserServiceImplementation implements UserService{

    private final UserRepository userRepository;
    private final CourseClientRest courseClientRest;

    public UserServiceImplementation(UserRepository userRepository, CourseClientRest courseClientRest) {
        this.userRepository = userRepository;
        this.courseClientRest = courseClientRest;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public User save(User user) {
        return !findUserByEmail(user.getEmail()).isPresent() ? userRepository.save(user) : null;
    }

    @Override
    @Transactional
    public User update(User user) {
        Optional<User> userOld = findUserById(user.getId());
        if(userOld.isEmpty()){
            return null;
        }else if(user.getEmail().equalsIgnoreCase(userOld.get().getEmail())){
            return userRepository.save(user);
        }
        return !findUserByEmail(user.getEmail()).isPresent() ? userRepository.save(user) : null;
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return findUserById(id).map(user -> {
            userRepository.deleteById(id);
            courseClientRest.deleteCourseUser(id);
            return Boolean.TRUE;
        }).orElse(Boolean.FALSE);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmailWithQuery(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllUsersById(Iterable<Long> userId) {
        return (List<User>) userRepository.findAllById(userId);
    }
}
