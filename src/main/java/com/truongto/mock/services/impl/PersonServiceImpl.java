package com.truongto.mock.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truongto.mock.entities.Person;
import com.truongto.mock.repositories.PersonRepository;
import com.truongto.mock.services.PersonService;
import com.truongto.mock.thfw.exceptions.NotFoundException;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Override
    public Person save(Person person) {
        return this.personRepository.save(person);
    }

    @Override
    public List<Person> findAll() {
        return this.personRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        this.personRepository.deleteById(id);
    }

    @Override
    public Person findById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy người dùng với id: " + id));
    }

    @Override
    public Person findByUserName(String userName) {
        return personRepository.findByUsername(userName).orElseThrow(
                () -> new NotFoundException("Không tìm thấy người dùng với username: " + userName));
    }

    @Override
    public Person create(Person person) {
        return personRepository.save(person);
    }

}
