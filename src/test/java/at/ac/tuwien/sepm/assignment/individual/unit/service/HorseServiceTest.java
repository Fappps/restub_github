package at.ac.tuwien.sepm.assignment.individual.unit.service;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.exceptions.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exceptions.NotUpdatedException;
import at.ac.tuwien.sepm.assignment.individual.exceptions.ValidationException;
import at.ac.tuwien.sepm.assignment.individual.persistence.IHorseDao;
import at.ac.tuwien.sepm.assignment.individual.persistence.exceptions.PersistenceException;
import at.ac.tuwien.sepm.assignment.individual.persistence.util.DBConnectionManager;
import at.ac.tuwien.sepm.assignment.individual.service.IHorseService;
import at.ac.tuwien.sepm.assignment.individual.service.exceptions.ServiceException;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = "test")
public class HorseServiceTest {

    @Autowired
    IHorseService horseService;
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
    public void givenNothing_whenFindHorseByIdWhichNotExists_thenNotFoundException()
        throws NotFoundException, ServiceException {
        horseService.findOneById(1);
    }

    @Test
    public void givenHorse_findThatHorse()
        throws NotFoundException, NotUpdatedException, ServiceException, ValidationException {
        Horse h = new Horse();
        h.setMaxSpeed(60.0);
        h.setMinSpeed(60.0);
        h.setName("hor");
        horseService.createHorse(h);
        Horse t = horseService.findOneById(1);
        assertEquals(t.getName(),"hor");
    }

    @Test(expected = ValidationException.class)
    public void givenHorseWithNoName_expectedValidationException()
        throws NotFoundException, NotUpdatedException, ServiceException, ValidationException {
        Horse h = new Horse();
        h.setBreed("lol");
        h.setMaxSpeed(60.0);
        h.setMinSpeed(60.0);
        horseService.createHorse(h);
    }
    @Test
    public void updateHorse_onlyBreedHasToChange()
        throws NotFoundException, NotUpdatedException, ServiceException, ValidationException {
        Horse h = new Horse();
        h.setMaxSpeed(60.0);
        h.setMinSpeed(60.0);
        h.setName("hor");
        horseService.createHorse(h);
        h.setName(null);
        h.setMaxSpeed(null);
        h.setMinSpeed(null);
        h.setBreed("kek");
        Horse test = horseService.updateHorse(1,h);
        assertEquals(test.getBreed(),"kek");
        assertEquals(test.getName(),"hor");
    }





}

