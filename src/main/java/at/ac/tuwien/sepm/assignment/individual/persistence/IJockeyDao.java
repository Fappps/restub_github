package at.ac.tuwien.sepm.assignment.individual.persistence;

import at.ac.tuwien.sepm.assignment.individual.entity.Jockey;
import at.ac.tuwien.sepm.assignment.individual.exceptions.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exceptions.NotUpdatedException;
import at.ac.tuwien.sepm.assignment.individual.persistence.exceptions.PersistenceException;

public interface IJockeyDao {

    /**
     * @param id of the horse to find.
     * @return the horse with the specified id.
     * @throws PersistenceException will be thrown if something goes wrong during the database access.
     * @throws NotFoundException    will be thrown if the horse could not be found in the database.
     */
    Jockey findOneById(Integer id) throws PersistenceException, NotFoundException;
    Jockey createJockey(Jockey horse) throws PersistenceException, NotUpdatedException, NotFoundException;
    Jockey updateJockey(Integer id, Jockey horse) throws PersistenceException, NotUpdatedException, NotFoundException;
    void deleteJockey(Integer id) throws PersistenceException, NotFoundException;
    Jockey[] getAllJockeys() throws PersistenceException, NotFoundException;

    Jockey[] findJockeysFiltered(String name, Double skills) throws PersistenceException, NotFoundException;
}
