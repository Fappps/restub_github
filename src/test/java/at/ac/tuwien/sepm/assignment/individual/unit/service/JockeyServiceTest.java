package at.ac.tuwien.sepm.assignment.individual.unit.service;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.entity.Jockey;
import at.ac.tuwien.sepm.assignment.individual.exceptions.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exceptions.NotUpdatedException;
import at.ac.tuwien.sepm.assignment.individual.exceptions.ValidationException;
import at.ac.tuwien.sepm.assignment.individual.persistence.exceptions.PersistenceException;
import at.ac.tuwien.sepm.assignment.individual.persistence.util.DBConnectionManager;
import at.ac.tuwien.sepm.assignment.individual.service.IHorseService;
import at.ac.tuwien.sepm.assignment.individual.service.IJockeyService;
import at.ac.tuwien.sepm.assignment.individual.service.exceptions.ServiceException;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = "test")
public class JockeyServiceTest {

    @Autowired
    IJockeyService jockeyService;
    @Autowired
    DBConnectionManager dbConnectionManager;

    /**
     * It is important to close the database connection after each test in order to clean the in-memory database
     */
    @After
    public void afterEachTest() throws PersistenceException {
        dbConnectionManager.closeConnection();
    }

    @Test(expected = NotFoundException.class)
    public void givenNothing_whenFindJockeyByIdWhichNotExists_thenNotFoundException()
        throws NotFoundException, ServiceException {
        jockeyService.findOneById(1);
    }

    @Test
    public void givenJockey_findThatJockey()
        throws NotFoundException, NotUpdatedException, ServiceException, ValidationException {
        Jockey j = new Jockey();
        j.setName("hor");
        j.setSkills(null);
        jockeyService.createJockey(j);
        Jockey t = jockeyService.findOneById(1);
        assertEquals(t.getName(),"hor");
    }

    @Test(expected = ValidationException.class)
    public void givenJockeyWithNoName_expectedValidationException()
        throws NotFoundException, NotUpdatedException, ServiceException, ValidationException {
        Jockey j = new Jockey();
        jockeyService.createJockey(j);
    }

    @Test
    public void updateJockey_onlySkillHasToChange()
        throws NotFoundException, NotUpdatedException, ServiceException, ValidationException {
        Jockey j = new Jockey();
        j.setName("hor");
        jockeyService.createJockey(j);
        j.setName(null);
        j.setSkills(66.9);
        Jockey test = jockeyService.updateJockey(1,j);
        assertEquals(test.getSkills(),66.9,0.1);
        assertEquals(test.getName(),"hor");
    }





}

