package at.ac.tuwien.sepm.assignment.individual.util.mapper;

import at.ac.tuwien.sepm.assignment.individual.entity.Jockey;
import at.ac.tuwien.sepm.assignment.individual.rest.dto.JockeyDto;
import org.springframework.stereotype.Component;

@Component
public class JockeyMapper {

    public JockeyDto entityToDto(Jockey jockey) {
        return new JockeyDto(jockey.getId(), jockey.getName(), jockey.getSkills(), jockey.getCreated(), jockey.getUpdated());
    }

    public JockeyDto[] entityArrayToDtoArray(Jockey[] allJockeys) {
        JockeyDto[] jockeyDtos = new JockeyDto[allJockeys.length];
        for (int i = 0;i<allJockeys.length;i++){
            jockeyDtos[i]=entityToDto(allJockeys[i]);
        }
        return jockeyDtos;
    }
}
