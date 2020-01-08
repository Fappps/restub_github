package at.ac.tuwien.sepm.assignment.individual.service.impl;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.exceptions.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exceptions.NotUpdatedException;
import at.ac.tuwien.sepm.assignment.individual.exceptions.ValidationException;
import at.ac.tuwien.sepm.assignment.individual.persistence.IHorseDao;
import at.ac.tuwien.sepm.assignment.individual.persistence.exceptions.PersistenceException;
import at.ac.tuwien.sepm.assignment.individual.service.IHorseService;
import at.ac.tuwien.sepm.assignment.individual.service.exceptions.ServiceException;
import at.ac.tuwien.sepm.assignment.individual.service.validation.HorseValidator;
import at.ac.tuwien.sepm.assignment.individual.service.validation.IHorseValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HorseService implements IHorseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HorseService.class);
    private final IHorseDao horseDao;
    private final IHorseValidator horseValidator;

    @Autowired
    public HorseService(IHorseDao horseDao,IHorseValidator horseValidator) {
        this.horseDao = horseDao;
        this.horseValidator = horseValidator;
    }

    @Override
    public Horse findOneById(Integer id) throws ServiceException, NotFoundException {
        LOGGER.info("Get horse with id " + id);
        try {
            return horseDao.findOneById(id);
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Horse createHorse(Horse horse) throws ServiceException, NotUpdatedException, NotFoundException, ValidationException {
        LOGGER.info("Create horse " + horse);
        try {
            horseValidator.checkCreateHorse(horse);
            return horseDao.createHorse(horse);
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Horse updateHorse(Integer id, Horse horse) throws ServiceException, NotUpdatedException, NotFoundException, ValidationException {
        LOGGER.info("Update horse with id" + id);
        try {
            horseValidator.checkUpdateHorse(horse);
            return horseDao.updateHorse(id,horse);
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void deleteHorse(Integer id) throws NotFoundException, ServiceException {
        LOGGER.info("Delete horse with id" + id);
        try {
            horseDao.deleteHorse(id);
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Horse[] getAllHorses() throws NotFoundException, ServiceException {
        LOGGER.info("Get all horses");
        try {
            return horseDao.getAllHorses();
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Horse[] findHorsesFiltered(String name, String breed, Double minSpeed, Double maxSpeed) throws NotFoundException, ServiceException {
        LOGGER.info("Get all horses");
        try {
            return horseDao.findHorsesFiltered(name, breed, minSpeed, maxSpeed);
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }


}
