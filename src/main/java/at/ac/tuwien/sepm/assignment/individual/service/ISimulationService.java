package at.ac.tuwien.sepm.assignment.individual.service;

import at.ac.tuwien.sepm.assignment.individual.entity.OutputResult;
import at.ac.tuwien.sepm.assignment.individual.entity.Simulation;
import at.ac.tuwien.sepm.assignment.individual.exceptions.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exceptions.NotUpdatedException;
import at.ac.tuwien.sepm.assignment.individual.exceptions.ValidationException;
import at.ac.tuwien.sepm.assignment.individual.service.exceptions.ServiceException;

public interface ISimulationService {

    /**
     * @param id of the simulation to find.
     * @return the simulation with the specified id.
     * @throws ServiceException  will be thrown if something goes wrong during data processing.
     * @throws NotFoundException will be thrown if the simulation could not be found in the system.
     */
    OutputResult findOneById(Integer id) throws ServiceException, NotFoundException;
    OutputResult createSimulation(Simulation simulation) throws ServiceException, NotUpdatedException, NotFoundException, ValidationException;
    OutputResult updateSimulation(Integer id, Simulation simulation) throws ServiceException, NotUpdatedException, NotFoundException, ValidationException;
    void deleteSimulation(Integer id) throws NotFoundException, ServiceException;
    OutputResult[] getAllSimulations() throws NotFoundException, ServiceException;
    OutputResult[] findSimulationsFiltered(String name, Double skills) throws NotFoundException, ServiceException;
}
