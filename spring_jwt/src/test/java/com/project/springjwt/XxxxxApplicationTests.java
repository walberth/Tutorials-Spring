package com.project.springjwt;

import com.project.springjwt.repository.IPersonRepository;
import com.project.springjwt.service.ISecurityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class XxxxxApplicationTests {
    @Autowired
    private IPersonRepository personRepository;

    @Autowired
    private ISecurityService securityService;

    @Test
    public void contextLoads() {
    }

    /*@Test
    public void contextLoads() {
        List<Person> test5 = this.personRepository.getPersonListByRowMapper(10, 0);

        List<Person> test6 = this.personRepository.getPersonListByRowMapperAndExtrapolation(10, 0);

        int personCreated = this.personRepository.createPerson(new Person(0,
                                                                     "Walberth",
                                                                     "Gutierrez",
                                                                     "Telles",
                                                                     true,
                                                                     new Date(2019, 03, 15).toLocalDate(),
                                                                     "73676494",
                                                                     "w.felipe.gutierrez@gmail.com",
                                                                     "994518203",
                                                                     "5577078",
                                                                     "wgutierrez",
                                                                     null));


        Person personCreated2 = this.personRepository.createAndGetPersonCreated(new Person(0,
                                                                           "Walberth",
                                                                           "Gutierrez",
                                                                           "Telles",
                                                                           true,
                                                                           new Date(2019, 03, 15).toLocalDate(),
                                                                           "73676494",
                                                                           "w.felipe.gutierrez@gmail.com",
                                                                           "994518203",
                                                                           "5577078",
                                                                           "wgutierrez",
                                                                           null));

        Person test = this.personRepository.getPersonById(personCreated);

        List<Person> test1 = this.personRepository.getPersonList(10, 0);

        this.personRepository.updatePerson(new Person(personCreated,
                                                 "Felipillo",
                                                 "Gutierrez",
                                                 "Telles",
                                                 true,
                                                 new Date(2019, 03, 15).toLocalDate(),
                                                 "73676494",
                                                 "w.felipe.gutierrez@gmail.com",
                                                 "994518203",
                                                 "5577078",
                                                 "wgutierrez",
                                                 null));

        this.personRepository.deletePerson(personCreated);
    }

    @Test
    public void ServiceTest() {
        this.securityService.CreatePerson(new Person(0,
                "Angelona",
                "Tejeda",
                "Ramos",
                true,
                new Date(2019, 03, 15).toLocalDate(),
                "73676494",
                "w.felipe.gutierrez@gmail.com",
                "994518203",
                "5577078",
                "wgutierrez",
                null),
                new Person(0,
                "Daniela",
                "Tejeda",
                "Ramos",
                true,
                new Date(2019, 03, 15).toLocalDate(),
                "73676494",
                "w.felipe.gutierrez@gmail.com",
                "994518203",
                "5577078",
                "wgutierrez",
                null));


        this.securityService.UpdatePerson(new Person(0,
                        "Angelona",
                        "Tejeda",
                        "Ramos",
                        true,
                        new Date(2019, 03, 15).toLocalDate(),
                        "73676494",
                        "w.felipe.gutierrez@gmail.com",
                        "994518203",
                        "5577078",
                        "wgutierrez",
                        null));

        this.securityService.DeletePerson();
    }*/
}
