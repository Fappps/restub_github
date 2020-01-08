package at.ac.tuwien.sepm.assignment.individual.unit.persistence;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.entity.Jockey;
import at.ac.tuwien.sepm.assignment.individual.exceptions.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exceptions.NotUpdatedException;
import at.ac.tuwien.sepm.assignment.individual.exceptions.ValidationException;
import at.ac.tuwien.sepm.assignment.individual.persistence.IHorseDao;
import at.ac.tuwien.sepm.assignment.individual.persistence.IJockeyDao;
import at.ac.tuwien.sepm.assignment.individual.persistence.exceptions.PersistenceException;
import at.ac.tuwien.sepm.assignment.individual.persistence.util.DBConnectionManager;
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
public class JockeyDaoTest {

    @Autowired
    IJockeyDao jockeyDao;
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
        throws PersistenceException, NotFoundException {
        jockeyDao.findOneById(1);
    }

    @Test
    public void givenJockey_findThatJockey()
        throws PersistenceException, NotFoundException, NotUpdatedException {
        Jockey j = new Jockey();
        j.setName("feg");
        j.setSkills(3.4);
        jockeyDao.createJockey(j);
        Jockey t = jockeyDao.findOneById(1);
        assertEquals(t.getName(),"feg");
    }

    @Test(expected = NotFoundException.class)
    public void deleteJockey()
        throws PersistenceException, NotFoundException, NotUpdatedException {
        Jockey j = new Jockey();
        j.setName("feg");
        j.setSkills(3.4);
        jockeyDao.createJockey(j);
        jockeyDao.findOneById(1);
        jockeyDao.deleteJockey(1);
        jockeyDao.findOneById(1);
    }

    @Test
    public void findAllJockeysWithNameA()
        throws PersistenceException, NotFoundException, NotUpdatedException {
        Jockey j = new Jockey();
        j.setName("feg");
        jockeyDao.createJockey(j);
        j.setName("fag");
        jockeyDao.createJockey(j);
        j.setName("abra");
        jockeyDao.createJockey(j);
        int size = jockeyDao.findJockeysFiltered("a",null).length;
        assertEquals(2,size,0);
    }

    @Test(expected = NotFoundException.class)
    public void findButNoResults()
        throws PersistenceException, NotFoundException, NotUpdatedException {
        Jockey j = new Jockey();
        j.setName("feg");
        jockeyDao.createJockey(j);
        jockeyDao.findJockeysFiltered("a",null);
    }
}

