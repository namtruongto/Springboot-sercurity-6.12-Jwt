package com.truongto.mock.services.impl;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.truongto.mock.entities.User;
import com.truongto.mock.repositories.UserRepository;
import com.truongto.mock.services.UserService;
import com.truongto.mock.thfw.exceptions.NotFoundException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository personRepository;

    @Override
    public User save(User person) {
        return this.personRepository.save(person);
    }

    @Override
    public List<User> findAll() {
        return this.personRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        this.personRepository.deleteById(id);
    }

    @Override
    public User findById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy người dùng với id: " + id));
    }

    @Override
    public User findByUserName(String userName) {
        return personRepository.findByUsername(userName).orElseThrow(
                () -> new NotFoundException("Không tìm thấy người dùng với username: " + userName));
    }

    @Override
    public User create(User person) {
        return personRepository.save(person);
    }

}
