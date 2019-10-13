package com.project.springjwt.repository;

import com.project.springjwt.model.Person;

public interface IPersonRepository {
    Integer createPerson(Person person);
}
