package com.truongto.mock.services;

import java.util.List;

import com.truongto.mock.entities.User;

public interface UserService {
    User save(User person);
    List<User> findAll();
    void delete(Long id);
    User findById(Long id);
    User findByUserName(String userName);
    User create(User person);
}
