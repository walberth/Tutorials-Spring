package com.project.xxxxx.repository.implementation;

import com.project.xxxxx.model.Person;
import com.project.xxxxx.repository.IPersonRepository;
import com.project.xxxxx.repository.mapper.PersonRowMapper;
import com.project.xxxxx.transversal.TimeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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

    public Person getPersonById(int idPerson) {
        return jdbcTemplate.queryForObject("CALL getPersonById(?)", new Object[]{idPerson}, (rs, rowNum) ->
                new Person(rs.getInt("id"),
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
                           TimeHelper.convertToLocalDateTimeViaInstant(rs.getTimestamp("timeStamp"))));
    }

    public List<Person> getPersonList(int rowsPerPage, int pageNumber) {
        return jdbcTemplate.query("CALL getPersonList(?, ?)", new Object[]{ rowsPerPage, pageNumber }, (rs, rowNum) ->
                new Person(rs.getInt("id"),
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
                        TimeHelper.convertToLocalDateTimeViaInstant(rs.getTimestamp("timeStamp"))));
    }

    public List<Person> getPersonListByRowMapper(int rowsPerPage, int pageNumber) {
        return jdbcTemplate.query("CALL getPersonList(?, ?)",
                new Object[]{ rowsPerPage, pageNumber },
                new PersonRowMapper());
    }

    public List<Person> getPersonListByRowMapperAndExtrapolation(int rowsPerPage, int pageNumber) {
        return jdbcTemplate.query(String.format("CALL getPersonList(%s, %s)", rowsPerPage, pageNumber),
                new PersonRowMapper());
    }

    public void updatePerson(Person person) {
        jdbcTemplate.execute(String.format("CALL updatePerson(%s, '%s', '%s', '%s', %s, '%s', '%s', '%s', '%s', '%s')",
                person.getId(),
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

    public void deletePerson(int idPerson) {
        jdbcTemplate.execute(String.format("CALL deletePerson(%s)", idPerson));
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

    public Person createAndGetPersonCreated(Person person) {
        return (Person) jdbcTemplate.queryForObject(String.format("CALL createAndGetPersonCreated('%s', '%s', '%s', %s, '%s', '%s', '%s', '%s', '%s', '%s')",
                person.getFirstName(),
                person.getFatherLastName(),
                person.getMotherLastName(),
                person.getSex(),
                person.getBirthDate(),
                person.getDocument(),
                person.getEmail(),
                person.getMobile(),
                person.getTelephone(),
                person.getUserRegister()),
                new BeanPropertyRowMapper(Person.class));
    }
}
