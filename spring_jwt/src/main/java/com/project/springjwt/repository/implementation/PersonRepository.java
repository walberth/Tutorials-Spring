package com.project.springjwt.repository.implementation;

import com.project.springjwt.model.Person;
import com.project.springjwt.repository.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("PersonRepository")
public class PersonRepository implements IPersonRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer createPerson(Person person) {
        return jdbcTemplate.queryForObject(String.format("CALL createPerson('%s', '%s', '%s', %s, '%s', '%s', '%s', '%s', '%s', '%s')",
                    person.getFirstName(),
                    person.getFatherLastName(),
                    person.getMotherLastName(),
                    person.getSex(),
                    person.getBirthDate(),
                    person.getDocument(),
                    person.getEmail(),
                    person.getMobile(),
                    person.getTelephone(),
                    person.getUserRegister()), Integer.class);
    }
}
