package at.ac.tuwien.sepm.assignment.individual.persistence.impl;

import at.ac.tuwien.sepm.assignment.individual.entity.OutputResult;
import at.ac.tuwien.sepm.assignment.individual.entity.Simulation;
import at.ac.tuwien.sepm.assignment.individual.exceptions.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exceptions.NotUpdatedException;
import at.ac.tuwien.sepm.assignment.individual.persistence.ISimulationDao;
import at.ac.tuwien.sepm.assignment.individual.persistence.exceptions.PersistenceException;
import at.ac.tuwien.sepm.assignment.individual.persistence.util.DBConnectionManager;
import at.ac.tuwien.sepm.assignment.individual.rest.dto.HorseJockeyCombinations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


@Repository
public class SimulationDao implements ISimulationDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimulationDao.class);
    private final DBConnectionManager dbConnectionManager;

    @Autowired
    public SimulationDao(DBConnectionManager dbConnectionManager) {
        this.dbConnectionManager = dbConnectionManager;
    }

    private static OutputResult dbResultToSimulationDto(ResultSet result) throws SQLException {
        return new OutputResult(
            result.getInt("simulationID"),
            result.getString("name"),
            result.getTimestamp("created").toLocalDateTime());
    }

    private static HorseJockeyCombinations getHorseJockeyCombinationFromResult(ResultSet result) throws SQLException {
        return new HorseJockeyCombinations(
            result.getInt("id"),
            result.getInt("rank"),
            result.getString("horseName"),
            result.getString("jockeyName"),
            result.getDouble("avgSpeed"),
            result.getDouble("horseSpeed"),
            result.getDouble("skills"),
            result.getDouble("luckFactor"));
    }

    private static java.sql.Timestamp getCurrentTimeStamp() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());

    }

    @Override
    public OutputResult findOneById(Integer id) throws PersistenceException, NotFoundException {
        LOGGER.info("Get horse with id " + id);
        String sql = "SELECT sj.id as id, s.id as simulationID,rank,h.name as horsename,j.name as jockeyname,avgspeed,horsespeed,skills,luckfactor" +
            ",s.name as name, s.created as created FROM SIMULATION_JOCKEY_HORSE sj Join Horse h on sj.horseid = h.id " +
            "join jockey j on sj.jockeyid = j.id join simulation s on sj.simulationid = s.id where s.id = ?";
        OutputResult outputResult = null;
        try {
            PreparedStatement statement = dbConnectionManager.getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            ArrayList<HorseJockeyCombinations>horseJockeyCombinations = new ArrayList<>();
            while (result.next()) {
                horseJockeyCombinations.add(getHorseJockeyCombinationFromResult(result));
                outputResult = dbResultToSimulationDto(result);
            }
            if (!horseJockeyCombinations.isEmpty())outputResult.setHorseJockeyCombinations(horseJockeyCombinations);
        } catch (SQLException e) {
            LOGGER.error("Problem while executing SQL select statement for reading simulation with id " + id, e);
            throw new PersistenceException("Could not read simulation with id " + id, e);
        }
        if (outputResult != null) {
            return outputResult;
        } else {
            throw new NotFoundException("Could not find simulation with id " + id);
        }
    }

    @Override
    public OutputResult createSimulation(Simulation simulation) throws PersistenceException, NotUpdatedException, NotFoundException {
        return null;
    }

    @Override
    public OutputResult updateSimulation(Integer id, Simulation simulation) throws PersistenceException, NotUpdatedException, NotFoundException {
        return null;
    }

    @Override
    public void deleteSimulation(Integer id) throws PersistenceException, NotFoundException {

    }

    @Override
    public OutputResult[] getAllSimulations() throws PersistenceException, NotFoundException {
        return new OutputResult[0];
    }

    @Override
    public OutputResult[] findSimulationsFiltered(String name, Double skills) throws PersistenceException, NotFoundException {
        return new OutputResult[0];
    }


}
