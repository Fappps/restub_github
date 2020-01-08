package at.ac.tuwien.sepm.assignment.individual.rest;

import at.ac.tuwien.sepm.assignment.individual.entity.Jockey;
import at.ac.tuwien.sepm.assignment.individual.exceptions.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exceptions.NotUpdatedException;
import at.ac.tuwien.sepm.assignment.individual.exceptions.ValidationException;
import at.ac.tuwien.sepm.assignment.individual.rest.dto.JockeyDto;
import at.ac.tuwien.sepm.assignment.individual.service.IJockeyService;
import at.ac.tuwien.sepm.assignment.individual.service.exceptions.ServiceException;
import at.ac.tuwien.sepm.assignment.individual.util.mapper.JockeyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/jockeys")
public class JockeyEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(JockeyEndpoint.class);
    private static final String BASE_URL = "/api/v1/jockeys";
    private final IJockeyService jockeyService;
    private final JockeyMapper jockeyMapper;

    @Autowired
    public JockeyEndpoint(IJockeyService jockeyService, JockeyMapper jockeyMapper) {
        this.jockeyService = jockeyService;
        this.jockeyMapper = jockeyMapper;
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public JockeyDto getOneById(@PathVariable("id") Integer id) {
        LOGGER.info("GET " + BASE_URL + "/" + id);
        try {
            return jockeyMapper.entityToDto(jockeyService.findOneById(id));
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during read jockey with id " + id, e);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error during reading jockey: " + e.getMessage(), e);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public JockeyDto createJockey(@RequestBody Jockey jockey,HttpServletResponse response) {
        LOGGER.info("POST " + BASE_URL);
        try {
            response.setStatus(HttpServletResponse.SC_CREATED);
            return jockeyMapper.entityToDto(jockeyService.createJockey(jockey));
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during create jockey " + jockey, e);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error during create jockey: " + e.getMessage(), e);
        } catch (NotUpdatedException e) {
            throw new ResponseStatusException(HttpStatus.NOT_MODIFIED, "Error during create jockey: " + e.getMessage(), e);
        } catch (ValidationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Error during create jockey: " + e.getMessage(), e);
        }
    }

    @PutMapping(value = "/{id}")
    public JockeyDto updateJockey(@PathVariable("id") Integer id,@RequestBody Jockey jockey) {
        LOGGER.info("PUT " + BASE_URL + "/" + id);
        try {
            return jockeyMapper.entityToDto(jockeyService.updateJockey(id,jockey));
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during update jockey with id " + id, e);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error during reading jockey: " + e.getMessage(), e);
        } catch (NotUpdatedException e) {
            throw new ResponseStatusException(HttpStatus.NOT_MODIFIED, "Error during update jockey: " + e.getMessage(), e);
        } catch (ValidationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Error during update jockey: " + e.getMessage(), e);
        }
    }

    @DeleteMapping(value = "/{id}")
    public void deleteJockey(@PathVariable("id") Integer id) {
        LOGGER.info("PUT " + BASE_URL + "/" + id);
        try {
            jockeyService.deleteJockey(id);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during deleting jockey with id " + id, e);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error during deleting jockey: " + e.getMessage(), e);
        }
    }

    @GetMapping()
    public JockeyDto[] findJockeyFiltered(@RequestParam(required = false) String name,@RequestParam(required = false) Double skills) {
        try {
            if (name!=null||skills!=null){
                LOGGER.info("GET " + BASE_URL + "?name=" + name + "&skill=" + skills);
                return jockeyMapper.entityArrayToDtoArray(jockeyService.findJockeysFiltered(name,skills));

            }else {
                LOGGER.info("GET " + BASE_URL);
                return jockeyMapper.entityArrayToDtoArray(jockeyService.getAllJockeys());
            }
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during read jockey", e);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error during reading jockey: " + e.getMessage(), e);
        }
    }

}
