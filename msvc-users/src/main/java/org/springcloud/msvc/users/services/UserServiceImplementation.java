package org.springcloud.msvc.users.services;

import org.springcloud.msvc.users.model.entity.User;
import org.springcloud.msvc.users.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService{

    private final UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
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
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User update(User user) {
        return  findUserById(user.getId()).isPresent() ? userRepository.save(user) : null;
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return findUserById(id).map(user -> {
            userRepository.deleteById(id);
            return Boolean.TRUE;
        }).orElse(Boolean.FALSE);
    }
}
