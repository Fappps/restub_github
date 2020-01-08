package at.ac.tuwien.sepm.assignment.individual.persistence.impl;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.entity.Jockey;
import at.ac.tuwien.sepm.assignment.individual.exceptions.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exceptions.NotUpdatedException;
import at.ac.tuwien.sepm.assignment.individual.persistence.IHorseDao;
import at.ac.tuwien.sepm.assignment.individual.persistence.IJockeyDao;
import at.ac.tuwien.sepm.assignment.individual.persistence.exceptions.PersistenceException;
import at.ac.tuwien.sepm.assignment.individual.persistence.util.DBConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


@Repository
public class JockeyDao implements IJockeyDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(JockeyDao.class);
    private final DBConnectionManager dbConnectionManager;

    @Autowired
    public JockeyDao(DBConnectionManager dbConnectionManager) {
        this.dbConnectionManager = dbConnectionManager;
    }

    private static Jockey dbResultToJockeyDto(ResultSet result) throws SQLException {
        return new Jockey(
            result.getInt("id"),
            result.getString("name"),
            result.getDouble("skills"),
            result.getTimestamp("created").toLocalDateTime(),
            result.getTimestamp("updated").toLocalDateTime());
    }

    private static java.sql.Timestamp getCurrentTimeStamp() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());

    }

    @Override
    public Jockey findOneById(Integer id) throws PersistenceException, NotFoundException {
        LOGGER.info("Get horse with id " + id);
        String sql = "SELECT * FROM Jockey WHERE id=?";
        Jockey jockey = null;
        try {
            PreparedStatement statement = dbConnectionManager.getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                jockey = dbResultToJockeyDto(result);
            }
        } catch (SQLException e) {
            LOGGER.error("Problem while executing SQL select statement for reading jockey with id " + id, e);
            throw new PersistenceException("Could not read jockey with id " + id, e);
        }
        if (jockey != null) {
            return jockey;
        } else {
            throw new NotFoundException("Could not find jockey with id " + id);
        }
    }

    @Override
    public Jockey createJockey(Jockey jockey) throws PersistenceException, NotUpdatedException, NotFoundException {
        LOGGER.info("Create jockey" + jockey);
        String sql = "INSERT INTO Jockey (name,skills,created,updated) VALUES (?,?,?,?)";
        int result;
        int key = 0;
        try {
            PreparedStatement statement = dbConnectionManager.getConnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, jockey.getName());
            statement.setObject(2, jockey.getSkills());
            statement.setTimestamp(3, getCurrentTimeStamp());
            statement.setTimestamp(4, getCurrentTimeStamp());
            result = statement.executeUpdate();
            ResultSet generatedKeys =statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                key = generatedKeys.getInt(1);
            }

        } catch (SQLException e) {
            LOGGER.error("Problem while executing SQL insert statement for a new jockey " + jockey, e);
            throw new PersistenceException("Could not create jockey" + jockey, e);
        }
        if (result == 0) {
            throw new NotUpdatedException("The operation didn't have an effect");
        }else {
            return findOneById(key);
        }
    }

    @Override
    public Jockey updateJockey(Integer id, Jockey jockey) throws PersistenceException, NotUpdatedException, NotFoundException {
        LOGGER.info("Update jockey " + id);
        Jockey oldJockey = findOneById(id);
        String sql = "UPDATE Jockey SET name = ?,skills = ?,updated = ? WHERE id=?";
        int result;
        try {
            PreparedStatement statement = dbConnectionManager.getConnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            if(jockey.getName()!=null){
                statement.setString(1, jockey.getName());
            }else {
                statement.setString(1, oldJockey.getName());
            }
            if(jockey.getSkills()!=null){
                statement.setObject(2, jockey.getSkills());
            }else {
                statement.setObject(2, oldJockey.getSkills());
            }
            statement.setTimestamp(3, getCurrentTimeStamp());
            statement.setInt(4, id);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Problem while executing SQL insert statement for a new jockey " + jockey, e);
            throw new PersistenceException("Could not create jockey " + jockey, e);
        }
        if (result == 0) {
            throw new NotUpdatedException("The operation didn't have an effect");
        }else {
            return findOneById(id);
        }
    }

    @Override
    public void deleteJockey(Integer id) throws PersistenceException, NotFoundException {
        LOGGER.info("Delete Jockey with id " + id);
        String sql = "DELETE FROM Jockey WHERE id=?";
        int result;
        try {
            PreparedStatement statement = dbConnectionManager.getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Problem while executing SQL select statement for reading jockey with id " + id, e);
            throw new PersistenceException("Could not read jockeys with id " + id, e);
        }
        if (result==0) {
            throw new NotFoundException("Could not find jockey with id " + id);
        }
    }

    @Override
    public Jockey[] getAllJockeys() throws PersistenceException, NotFoundException {
        LOGGER.info("Get all jockey");
        String sql = "SELECT * FROM Jockey";
        ArrayList<Jockey>jockeyArrayList = new ArrayList<>();
        try {
            PreparedStatement statement = dbConnectionManager.getConnection().prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                jockeyArrayList.add(dbResultToJockeyDto(result));
            }
        } catch (SQLException e) {
            LOGGER.error("Problem while executing SQL select statement for reading jockeys", e);
            throw new PersistenceException("Could not read jockeys", e);
        }
        Jockey[]jockeyArray = jockeyArrayList.toArray(new Jockey[jockeyArrayList.size()]);
        if (jockeyArray.length==0) {
            throw new NotFoundException("Could not find jockeys");
        }
        return jockeyArray;
    }

    @Override
    public Jockey[] findJockeysFiltered(String name, Double skills) throws PersistenceException, NotFoundException {
        LOGGER.info("Find Jockeys with " + name + ", " + skills);
        String sql = "SELECT * FROM Jockey WHERE";
        if (name != null) sql+=" name LIKE ? AND";
        if (skills != null) sql+=" breed LIKE ? AND";
        if (sql.substring(sql.length()-3,sql.length()).equals("AND"))sql= sql.substring(0,sql.length()-3);
        ArrayList<Jockey>jockeysArrayList = new ArrayList<>();
        try {
            PreparedStatement statement = dbConnectionManager.getConnection().prepareStatement(sql);
            int i = 1;

            if (name!=null){
                name = "%" + name +"%";
                statement.setString(i, name);
                i++;
            }
            if (skills!=null) {
                statement.setDouble(i, skills);
                i++;
            }
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                jockeysArrayList.add(dbResultToJockeyDto(result));
            }
        } catch (SQLException e) {
            LOGGER.error("Problem while executing SQL select statement for reading jockeys", e);
            throw new PersistenceException("Could not read jockeys", e);
        }
        Jockey[]jockeyArray = jockeysArrayList.toArray(new Jockey[jockeysArrayList.size()]);
        if (jockeyArray.length==0) {
            throw new NotFoundException("Could not find horses");
        }
        return jockeyArray;
    }
}
