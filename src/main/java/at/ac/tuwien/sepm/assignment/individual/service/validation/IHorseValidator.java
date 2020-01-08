package at.ac.tuwien.sepm.assignment.individual.service.validation;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.exceptions.ValidationException;

public interface IHorseValidator {
    boolean checkCreateHorse(Horse horse) throws ValidationException;
    boolean checkUpdateHorse(Horse horse) throws ValidationException;
}
