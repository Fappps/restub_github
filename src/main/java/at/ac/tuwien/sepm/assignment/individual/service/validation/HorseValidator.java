package at.ac.tuwien.sepm.assignment.individual.service.validation;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.exceptions.ValidationException;
import at.ac.tuwien.sepm.assignment.individual.persistence.exceptions.PersistenceException;
import at.ac.tuwien.sepm.assignment.individual.service.exceptions.ServiceException;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.exceptions.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exceptions.NotUpdatedException;
import at.ac.tuwien.sepm.assignment.individual.persistence.IHorseDao;
import at.ac.tuwien.sepm.assignment.individual.persistence.exceptions.PersistenceException;
import at.ac.tuwien.sepm.assignment.individual.service.IHorseService;
import at.ac.tuwien.sepm.assignment.individual.service.exceptions.ServiceException;
import at.ac.tuwien.sepm.assignment.individual.service.impl.HorseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class HorseValidator implements IHorseValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(HorseValidator.class);

    public boolean checkCreateHorse(Horse horse) throws ValidationException {
        LOGGER.info("Validating Horse " + horse);
        if (horse==null) throw new ValidationException("No Object given");
        if (horse.getName()==null){
            throw new ValidationException("The horse needs a name");
        }else {
            checkName(horse.getName());
        }
        if (horse.getBreed()!=null){
            checkBreed(horse.getBreed());
        }
        if (horse.getMinSpeed()==null) throw new ValidationException("The horse needs minspeed");
        if (horse.getMaxSpeed()==null) throw new ValidationException("The horse needs maxspeed");
        checkMinMaxSpeedConditions(horse.getMinSpeed(),horse.getMaxSpeed());
        return true;
    }

    @Override
    public boolean checkUpdateHorse(Horse horse) throws ValidationException {
        LOGGER.info("Validating Update Horse " + horse);
        if (horse.getName()!=null){
            checkName(horse.getName());
        }
        if (horse.getBreed()!=null){
            checkBreed(horse.getBreed());
        }
        if (horse.getMinSpeed()!=null){
            checkMinSpeed(horse.getMinSpeed());
        }
        if (horse.getMaxSpeed()!=null){
            checkMaxSpeed(horse.getMaxSpeed());
        }
        if (horse.getMaxSpeed()!=null&&horse.getMinSpeed()!=null){
            checkMinMaxSpeed(horse.getMinSpeed(),horse.getMaxSpeed());
        }
        return true;
    }

    private void checkMinMaxSpeedConditions(Double minSpeed, Double maxSpeed) throws ValidationException {
        checkMinSpeed(minSpeed);
        checkMaxSpeed(maxSpeed);
        checkMinMaxSpeed(minSpeed,maxSpeed);
    }

    private void checkMinMaxSpeed(Double minSpeed, Double maxSpeed) throws ValidationException {
        if(minSpeed>maxSpeed){
            throw new ValidationException("Maxspeed has to be higher than Minspeed");
        }
    }

    private void checkMaxSpeed(Double maxSpeed) throws ValidationException {
        if (maxSpeed>60){
            throw new ValidationException("Maxspeed has to be lower than 60");
        }
    }

    private void checkMinSpeed(Double minSpeed) throws ValidationException {
        if (minSpeed<40){
            throw new ValidationException("Minspeed has to be above 40");
        }
    }

    private void checkBreed(String breed) throws ValidationException {
        switch (breed){
            case "": throw new ValidationException("Breed not valid");
            default:
        }
    }

    public void checkName(String name) throws ValidationException {
        switch (name){
            case "": throw new ValidationException("Name must be set");
            default:
        }
    }
}
