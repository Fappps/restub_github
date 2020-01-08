package at.ac.tuwien.sepm.assignment.individual.service.impl;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.entity.Jockey;
import at.ac.tuwien.sepm.assignment.individual.exceptions.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exceptions.NotUpdatedException;
import at.ac.tuwien.sepm.assignment.individual.exceptions.ValidationException;
import at.ac.tuwien.sepm.assignment.individual.persistence.IHorseDao;
import at.ac.tuwien.sepm.assignment.individual.persistence.IJockeyDao;
import at.ac.tuwien.sepm.assignment.individual.persistence.exceptions.PersistenceException;
import at.ac.tuwien.sepm.assignment.individual.service.IHorseService;
import at.ac.tuwien.sepm.assignment.individual.service.IJockeyService;
import at.ac.tuwien.sepm.assignment.individual.service.exceptions.ServiceException;
import at.ac.tuwien.sepm.assignment.individual.service.validation.IHorseValidator;
import at.ac.tuwien.sepm.assignment.individual.service.validation.IJockeyValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JockeyService implements IJockeyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JockeyService.class);
    private final IJockeyDao jockeyDao;
    private final IJockeyValidator jockeyValidator;

    @Autowired
    public JockeyService(IJockeyDao jockeyDao, IJockeyValidator jockeyValidator) {
        this.jockeyDao = jockeyDao;
        this.jockeyValidator = jockeyValidator;
    }


    @Override
    public Jockey findOneById(Integer id) throws ServiceException, NotFoundException {
        LOGGER.info("Get jockey with id " + id);
        try {
            return jockeyDao.findOneById(id);
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Jockey createJockey(Jockey jockey) throws ServiceException, NotUpdatedException, NotFoundException, ValidationException {
        LOGGER.info("Create jockey " + jockey);
        try {
            jockeyValidator.checkCreateJockey(jockey);
            return jockeyDao.createJockey(jockey);
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Jockey updateJockey(Integer id, Jockey jockey) throws ServiceException, NotUpdatedException, NotFoundException, ValidationException {
        LOGGER.info("Update jockey with id" + id);
        try {
            jockeyValidator.checkUpdateJockey(jockey);
            return jockeyDao.updateJockey(id,jockey);
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void deleteJockey(Integer id) throws NotFoundException, ServiceException {
        LOGGER.info("Delete jockey with id" + id);
        try {
            jockeyDao.deleteJockey(id);
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Jockey[] getAllJockeys() throws NotFoundException, ServiceException {
        LOGGER.info("Get all horses");
        try {
            return jockeyDao.getAllJockeys();
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Jockey[] findJockeysFiltered(String name, Double skills) throws NotFoundException, ServiceException {
        LOGGER.info("Get all horses");
        try {
            return jockeyDao.findJockeysFiltered(name, skills);
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}
