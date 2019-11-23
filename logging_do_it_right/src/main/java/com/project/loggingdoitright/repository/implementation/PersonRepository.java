package com.project.loggingdoitright.repository.implementation;

import com.project.loggingdoitright.model.Person;
import com.project.loggingdoitright.repository.IPersonRepository;
import com.project.loggingdoitright.repository.mapper.PersonRowMapper;
import com.project.loggingdoitright.transversal.TimeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("PersonRepository")
public class PersonRepository implements IPersonRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Person getPersonById(int id) {
        return jdbcTemplate.queryForObject("CALL getPersonById(?)",
                new Object[]{ id },
                (rs, rowNum) -> new Person(rs.getInt("id"),
                                           rs.getString("firstName"),
                                           rs.getString("fatherLastName"),
                                           rs.getString("motherLastName"),
                                           rs.getBoolean("sex"),
                                           rs.getDate("birthDate").toLocalDate(),
                                           rs.getString("document"),
                                           rs.getString("email"),
                                           rs.getString("telephone"),
                                           rs.getString("mobile"),
                                           rs.getString("userRegister"),
                                           rs.getTimestamp("timeStamp")));
    }

    @Override
    public List<Person> getAllPerson() {
        return jdbcTemplate.query("CALL getAllPerson()", new PersonRowMapper());
    }

    @Override
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

    @Override
    public void deletePerson(int id) {
        jdbcTemplate.execute(String.format("CALL deletePerson(%s)", id));
    }

    @Override
    public void updatePerson(int id, Person person) {
        jdbcTemplate.execute(String.format("CALL updatePerson(%s, '%s', '%s', '%s', %s, '%s', '%s', '%s', '%s', '%s')",
                id,
                person.getFirstName(),
                person.getFatherLastName(),
                person.getMotherLastName(),
                person.getSex(),
                person.getBirthDate(),
                person.getDocument(),
                person.getEmail(),
                person.getTelephone(),
                person.getMobile()));
    }
}
