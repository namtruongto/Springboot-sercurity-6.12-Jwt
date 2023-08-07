package com.truongto.mock.services;

import java.util.List;

import com.truongto.mock.entities.Person;

public interface PersonService {
    Person save(Person person);
    List<Person> findAll();
    void delete(Long id);
    Person findById(Long id);
    Person findByUserName(String userName);
    Person create(Person person);
}
