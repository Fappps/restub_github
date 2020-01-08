package at.ac.tuwien.sepm.assignment.individual.rest;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.exceptions.NotUpdatedException;
import at.ac.tuwien.sepm.assignment.individual.exceptions.ValidationException;
import at.ac.tuwien.sepm.assignment.individual.rest.dto.HorseDto;
import at.ac.tuwien.sepm.assignment.individual.exceptions.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.service.IHorseService;
import at.ac.tuwien.sepm.assignment.individual.service.exceptions.ServiceException;
import at.ac.tuwien.sepm.assignment.individual.util.mapper.HorseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/horses")
public class HorseEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(HorseEndpoint.class);
    private static final String BASE_URL = "/api/v1/horses";
    private final IHorseService horseService;
    private final HorseMapper horseMapper;

    @Autowired
    public HorseEndpoint(IHorseService horseService, HorseMapper horseMapper) {
        this.horseService = horseService;
        this.horseMapper = horseMapper;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HorseDto getOneById(@PathVariable("id") Integer id) {
        LOGGER.info("GET " + BASE_URL + "/" + id);
        try {
            return horseMapper.entityToDto(horseService.findOneById(id));
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during read horse with id " + id, e);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error during reading horse: " + e.getMessage(), e);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HorseDto createHorse(@RequestBody Horse horse, HttpServletResponse response) {
        LOGGER.info("POST " + BASE_URL);
        try {
            response.setStatus(HttpServletResponse.SC_CREATED);
            return horseMapper.entityToDto(horseService.createHorse(horse));
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during create horse " + horse, e);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error during create horse: " + e.getMessage(), e);
        } catch (NotUpdatedException e) {
            throw new ResponseStatusException(HttpStatus.NOT_MODIFIED, "Error during create horse: " + e.getMessage(), e);
        } catch (ValidationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Error during create horse: " + e.getMessage(), e);
        }
    }

    @PutMapping(value = "/{id}")
    public HorseDto updateHorse(@PathVariable("id") Integer id,@RequestBody Horse horse) {
        LOGGER.info("PUT " + BASE_URL + "/" + id);
        try {
            return horseMapper.entityToDto(horseService.updateHorse(id,horse));
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during update horse with id " + id, e);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error during reading horse: " + e.getMessage(), e);
        } catch (NotUpdatedException e) {
            throw new ResponseStatusException(HttpStatus.NOT_MODIFIED, "Error during update horse: " + e.getMessage(), e);
        } catch (ValidationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Error during update horse: " + e.getMessage(), e);
        }
    }

    @DeleteMapping(value = "/{id}")
    public void deleteHorse(@PathVariable("id") Integer id) {
        LOGGER.info("PUT " + BASE_URL + "/" + id);
        try {
            horseService.deleteHorse(id);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during deleting horse with id " + id, e);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error during deleting horse: " + e.getMessage(), e);
        }
    }

    @GetMapping()
    public HorseDto[] findHorseFiltered(@RequestParam(required = false) String name,@RequestParam(required = false) String breed,@RequestParam(required = false) Double minSpeed,@RequestParam(required = false) Double maxSpeed) {
        try {
            if (name!=null||breed!=null||minSpeed!=null||maxSpeed!=null){
                LOGGER.info("GET " + BASE_URL + "?name=" + name + "&=" + breed + "&minSpeed=" + minSpeed +  "&maxSpeed=" + maxSpeed);
                return horseMapper.entityArrayToDtoArray(horseService.findHorsesFiltered(name,breed,minSpeed,maxSpeed));

            }else {
                LOGGER.info("GET " + BASE_URL);
                return horseMapper.entityArrayToDtoArray(horseService.getAllHorses());
            }
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during read horse", e);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error during reading horse: " + e.getMessage(), e);
        }
    }

}
