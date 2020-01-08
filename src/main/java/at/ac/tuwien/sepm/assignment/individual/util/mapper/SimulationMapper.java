package at.ac.tuwien.sepm.assignment.individual.util.mapper;

import at.ac.tuwien.sepm.assignment.individual.entity.OutputResult;
import at.ac.tuwien.sepm.assignment.individual.rest.dto.OutputResultDto;
import org.springframework.stereotype.Component;

@Component
public class SimulationMapper {

    public OutputResultDto entityToDto(OutputResult outputResult) {
        return new OutputResultDto(outputResult.getId(), outputResult.getName(), outputResult.getCreated(),outputResult.getHorseJockeyCombinations());
    }
/*
    public JockeyDto[] entityArrayToDtoArray(Jockey[] allJockeys) {
        JockeyDto[] jockeyDtos = new JockeyDto[allJockeys.length];
        for (int i = 0;i<allJockeys.length;i++){
            //jockeyDtos[i]=entityToDto(allJockeys[i]);
        }
        return jockeyDtos;
    }
    */
}
