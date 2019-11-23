package com.project.loggingdoitright.service;

import com.project.loggingdoitright.model.Person;
import com.project.loggingdoitright.transversal.Response;
import java.util.List;

public interface IPersonService {
    Response<Person> getPersonById(int id);
    Response<List<Person>> getAllPerson();
    Response<Integer> createPerson(Person person);
    Response<Object> deletePerson(int id);
    Response<Object> updatePerson(int id, Person person);
}
