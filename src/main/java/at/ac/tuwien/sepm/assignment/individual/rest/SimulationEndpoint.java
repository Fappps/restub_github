package at.ac.tuwien.sepm.assignment.individual.rest;

import at.ac.tuwien.sepm.assignment.individual.entity.OutputResult;
import at.ac.tuwien.sepm.assignment.individual.entity.Simulation;
import at.ac.tuwien.sepm.assignment.individual.exceptions.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.rest.dto.OutputResultDto;
import at.ac.tuwien.sepm.assignment.individual.service.ISimulationService;
import at.ac.tuwien.sepm.assignment.individual.service.exceptions.ServiceException;
import at.ac.tuwien.sepm.assignment.individual.util.mapper.SimulationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/simulations")
public class SimulationEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimulationEndpoint.class);
    private static final String BASE_URL = "/api/v1/simulations";
    private final ISimulationService simulationService;
    private final SimulationMapper simulationMapper;

    @Autowired
    public SimulationEndpoint(ISimulationService simulationService, SimulationMapper simulationMapper) {
        this.simulationService = simulationService;
        this.simulationMapper = simulationMapper;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public OutputResultDto getOneById(@PathVariable("id") Integer id) {
        LOGGER.info("GET " + BASE_URL + "/" + id);

        /*
        HorseSimulationCombinations h = new HorseSimulationCombinations(1,1,"lol","lel",3.0,3.0,3.0,3.0);
        HorseSimulationCombinations l = new HorseSimulationCombinations(2,2,"lol","lel",3.0,3.0,3.0,3.0);
        HorseSimulationCombinations e = new HorseSimulationCombinations(3,3,"lol","lel",3.0,3.0,3.0,3.0);
        ArrayList<HorseSimulationCombinations> horseSimulationCombinations = new ArrayList<>();
        horseSimulationCombinations.add(h);
        horseSimulationCombinations.add(l);
        horseSimulationCombinations.add(e);
        OutputResultDto resultDto = new OutputResultDto(1,"oje",horseSimulationCombinations);
        return resultDto;

        */
        try {
            return simulationMapper.entityToDto(simulationService.findOneById(id));
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during read simulation with id " + id, e);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error during reading simulation: " + e.getMessage(), e);
        }
    }

    private static java.sql.Timestamp getCurrentTimeStamp() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());

    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public OutputResultDto createSimulation(@RequestBody Simulation simulation, HttpServletResponse response) {
        LOGGER.info("POST " + BASE_URL);



        /*
        try {
            response.setStatus(HttpServletResponse.SC_CREATED);
            return simulationMapper.entityToDto(simulationService.createSimulation(simulation));
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during create simulation " + simulation, e);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error during create simulation: " + e.getMessage(), e);
        } catch (NotUpdatedException e) {
            throw new ResponseStatusException(HttpStatus.NOT_MODIFIED, "Error during create simulation: " + e.getMessage(), e);
        } catch (ValidationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Error during create simulation: " + e.getMessage(), e);
        }

    */
    }
 /*
    @PutMapping(value = "/{id}")
    public SimulationDto updateSimulation(@PathVariable("id") Integer id,@RequestBody Simulation simulation) {
        LOGGER.info("PUT " + BASE_URL + "/" + id);
        try {
            return simulationMapper.entityToDto(simulationService.updateSimulation(id,simulation));
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during update simulation with id " + id, e);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error during reading simulation: " + e.getMessage(), e);
        } catch (NotUpdatedException e) {
            throw new ResponseStatusException(HttpStatus.NOT_MODIFIED, "Error during update simulation: " + e.getMessage(), e);
        } catch (ValidationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Error during update simulation: " + e.getMessage(), e);
        }
    }

    @DeleteMapping(value = "/{id}")
    public void deleteSimulation(@PathVariable("id") Integer id) {
        LOGGER.info("PUT " + BASE_URL + "/" + id);
        try {
            simulationService.deleteSimulation(id);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during deleting simulation with id " + id, e);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error during deleting simulation: " + e.getMessage(), e);
        }
    }

    @GetMapping()
    public SimulationDto[] findSimulationFiltered(@RequestParam(required = false) String name,@RequestParam(required = false) Double skills) {
        try {
            if (name!=null||skills!=null){
                LOGGER.info("GET " + BASE_URL + "?name=" + name + "&skill=" + skills);
                return simulationMapper.entityArrayToDtoArray(simulationService.findSimulationsFiltered(name,skills));

            }else {
                LOGGER.info("GET " + BASE_URL);
                return simulationMapper.entityArrayToDtoArray(simulationService.getAllSimulations());
            }
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during read simulation", e);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error during reading simulation: " + e.getMessage(), e);
        }
    }
*/
}
