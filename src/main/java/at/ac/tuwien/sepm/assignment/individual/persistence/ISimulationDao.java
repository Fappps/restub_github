package at.ac.tuwien.sepm.assignment.individual.persistence;

import at.ac.tuwien.sepm.assignment.individual.entity.OutputResult;
import at.ac.tuwien.sepm.assignment.individual.entity.Simulation;
import at.ac.tuwien.sepm.assignment.individual.exceptions.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exceptions.NotUpdatedException;
import at.ac.tuwien.sepm.assignment.individual.persistence.exceptions.PersistenceException;

public interface ISimulationDao {

    /**
     * @param id of the simulation to find.
     * @return the simulation with the specified id.
     * @throws PersistenceException will be thrown if something goes wrong during the database access.
     * @throws NotFoundException    will be thrown if the simulation could not be found in the database.
     */
    OutputResult findOneById(Integer id) throws PersistenceException, NotFoundException;
    OutputResult createSimulation(Simulation simulation) throws PersistenceException, NotUpdatedException, NotFoundException;
    OutputResult updateSimulation(Integer id, Simulation simulation) throws PersistenceException, NotUpdatedException, NotFoundException;
    void deleteSimulation(Integer id) throws PersistenceException, NotFoundException;
    OutputResult[] getAllSimulations() throws PersistenceException, NotFoundException;

    OutputResult[] findSimulationsFiltered(String name, Double skills) throws PersistenceException, NotFoundException;
}
