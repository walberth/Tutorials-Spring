package com.project.loggingdoitright.repository;

import com.project.loggingdoitright.model.Person;
import java.util.List;

public interface IPersonRepository {
    Person getPersonById(int id);
    List<Person> getAllPerson();
    Integer createPerson(Person person);
    void deletePerson(int id);
    void updatePerson(int id, Person person);
}
