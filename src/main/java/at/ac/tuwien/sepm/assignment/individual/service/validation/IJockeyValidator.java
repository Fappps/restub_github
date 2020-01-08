package at.ac.tuwien.sepm.assignment.individual.service.validation;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.entity.Jockey;
import at.ac.tuwien.sepm.assignment.individual.exceptions.ValidationException;

public interface IJockeyValidator {
    boolean checkCreateJockey(Jockey jockey) throws ValidationException;
    boolean checkUpdateJockey(Jockey jockey) throws ValidationException;
}
