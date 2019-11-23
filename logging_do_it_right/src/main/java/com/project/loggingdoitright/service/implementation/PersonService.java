package com.project.loggingdoitright.service.implementation;

import com.project.loggingdoitright.model.Person;
import com.project.loggingdoitright.repository.IPersonRepository;
import com.project.loggingdoitright.service.IPersonService;
import com.project.loggingdoitright.transversal.Message;
import com.project.loggingdoitright.transversal.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service("PersonService")
public class PersonService implements IPersonService {
    private IPersonRepository personRepository;

    @Autowired
    public PersonService(IPersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Response<Person> getPersonById(int id) {
        Response<Person> response = new Response<>();

        this.otherMethodExceptionThrow();

        if(id == 0) {
            response.setMessage(Message.MustBeIndicatePersonId);

            return response;
        }

        Person person = this.personRepository.getPersonById(id);

        if(person == null) {
            response.setMessage(Message.PersonCantBeFind);

            return response;
        }

        response.setData(person);
        response.setIsWarning(false);

        return response;
    }

    private void otherMethodExceptionThrow() {
        double except = 1/0;
    }

    @Override
    public Response<List<Person>> getAllPerson() {
        Response<List<Person>> response = new Response<>();

        List<Person> personList = this.personRepository.getAllPerson();

        if(personList.isEmpty()) {
            response.setMessage(Message.PersonListCantBeFind);

            return response;
        }

        response.setData(personList);
        response.setIsWarning(false);

        return response;
    }

    @Override
    @Transactional
    public Response<Integer> createPerson(Person person) {
        Response<Integer> response = new Response<>();

        if(person == null) {
            response.setMessage(Message.MustBeIndicatePersonInformation);

            return response;
        }

        Integer personCreated = this.personRepository.createPerson(person);

        if(personCreated == null || personCreated == 0) {
            response.setMessage(Message.PersonCantBeCreated);

            return response;
        }

        response.setData(personCreated);
        response.setIsWarning(false);

        return response;
    }

    @Override
    @Transactional
    public Response<Object> deletePerson(int id) {
        Response<Object> response = new Response<>();

        if(id == 0) {
            response.setMessage(Message.MustBeIndicatePersonId);

            return response;
        }

        this.personRepository.deletePerson(id);

        response.setData(null);
        response.setIsWarning(false);

        return response;
    }

    @Override
    @Transactional
    public Response<Object> updatePerson(int id, Person person) {
        Response<Object> response = new Response<>();

        if(id == 0 || person == null) {
            response.setMessage(Message.MustBeIndicatePersonInformation);

            return response;
        }

        this.personRepository.updatePerson(id, person);

        response.setData(null);
        response.setIsWarning(false);

        return response;
    }
}
