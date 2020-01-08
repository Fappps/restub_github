package at.ac.tuwien.sepm.assignment.individual.service.impl;

import at.ac.tuwien.sepm.assignment.individual.entity.OutputResult;
import at.ac.tuwien.sepm.assignment.individual.entity.Simulation;
import at.ac.tuwien.sepm.assignment.individual.exceptions.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exceptions.NotUpdatedException;
import at.ac.tuwien.sepm.assignment.individual.exceptions.ValidationException;
import at.ac.tuwien.sepm.assignment.individual.persistence.ISimulationDao;
import at.ac.tuwien.sepm.assignment.individual.persistence.exceptions.PersistenceException;
import at.ac.tuwien.sepm.assignment.individual.service.ISimulationService;
import at.ac.tuwien.sepm.assignment.individual.service.exceptions.ServiceException;
import at.ac.tuwien.sepm.assignment.individual.service.validation.ISimulationValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimulationService implements ISimulationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimulationService.class);
    private final ISimulationDao simulationDao;
    private final ISimulationValidator simulationValidator;

    @Autowired
    public SimulationService(ISimulationDao simulationDao, ISimulationValidator simulationValidator) {
        this.simulationDao = simulationDao;
        this.simulationValidator = simulationValidator;
    }


    @Override
    public OutputResult findOneById(Integer id) throws ServiceException, NotFoundException {
        LOGGER.info("Get simulation with id " + id);
        try {
            return simulationDao.findOneById(id);
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public OutputResult createSimulation(Simulation simulation) throws ServiceException, NotUpdatedException, NotFoundException, ValidationException {
        //try {
            return new OutputResult();/*
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }*/
    }


    @Override
    public OutputResult updateSimulation(Integer id, Simulation simulation) throws ServiceException, NotUpdatedException, NotFoundException, ValidationException {
        return null;
    }

    @Override
    public void deleteSimulation(Integer id) throws NotFoundException, ServiceException {

    }

    @Override
    public OutputResult[] getAllSimulations() throws NotFoundException, ServiceException {
        return new OutputResult[0];
    }

    @Override
    public OutputResult[] findSimulationsFiltered(String name, Double skills) throws NotFoundException, ServiceException {
        return new OutputResult[0];
    }


}
