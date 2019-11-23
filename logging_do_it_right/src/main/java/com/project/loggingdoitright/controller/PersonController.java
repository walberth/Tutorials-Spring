package com.project.loggingdoitright.controller;

import com.project.loggingdoitright.model.Person;
import com.project.loggingdoitright.service.IPersonService;
import com.project.loggingdoitright.transversal.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {
    private IPersonService personService;

    @Autowired
    public PersonController(IPersonService personService){
        this.personService = personService;
    }

    @GetMapping("/{id}")
    public Response<Person> getPersonById(@PathVariable("id") Integer id, @RequestParam("username") String username) {
        return this.personService.getPersonById(id);
    }

    @GetMapping("/")
    public Response<List<Person>> getAllPerson(@RequestParam("username") String username) {
        return this.personService.getAllPerson();
    }

    @PostMapping("/")
    public Response<Integer> createPerson(@RequestBody Person person, @RequestParam("username") String username) {
        return this.personService.createPerson(person);
    }

    @DeleteMapping("/{id}")
    public Response<Object> deletePerson(@PathVariable("id") Integer id, @RequestParam("username") String username) {
        return this.personService.deletePerson(id);
    }

    @PutMapping("/{id}")
    public Response<Object> updatePerson(@PathVariable("id") Integer id, @RequestBody Person person, @RequestParam("username") String username) {
        return this.personService.updatePerson(id, person);
    }
}
