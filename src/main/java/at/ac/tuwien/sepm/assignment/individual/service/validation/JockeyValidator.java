package at.ac.tuwien.sepm.assignment.individual.service.validation;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.entity.Jockey;
import at.ac.tuwien.sepm.assignment.individual.exceptions.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class JockeyValidator implements IJockeyValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(JockeyValidator.class);

    @Override
    public boolean checkCreateJockey(Jockey jockey) throws ValidationException {
        LOGGER.info("Validating Jockey " + jockey);
        if (jockey==null) throw new ValidationException("No Object given");
        if (jockey.getName()==null){
            throw new ValidationException("The horse needs a name");
        }else {
            checkName(jockey.getName());
        }
        return true;
    }

    @Override
    public boolean checkUpdateJockey(Jockey jockey) throws ValidationException {
        LOGGER.info("Validating Update Jockey " + jockey);
        if (jockey.getName()!=null)checkName(jockey.getName());
        return true;
    }

    public void checkName(String name) throws ValidationException {
        switch (name){
            case "": throw new ValidationException("Name must be set");
            default:
        }
    }
}
