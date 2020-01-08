package at.ac.tuwien.sepm.assignment.individual.rest.dto;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class OutputResultDto {
    private Integer id;
    private String name;
    private LocalDateTime created;
    private ArrayList<HorseJockeyCombinations> horseJockeyCombinations;


    public OutputResultDto(){

    }

    public OutputResultDto(Integer id, String name, LocalDateTime created, ArrayList<HorseJockeyCombinations> horseJockeyCombinations) {
        this.id = id;
        this.name = name;
        this.horseJockeyCombinations = horseJockeyCombinations;
        this.created = created;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public ArrayList<HorseJockeyCombinations> getHorseJockeyCombinations() {
        return horseJockeyCombinations;
    }

    public void setHorseJockeyCombinations(ArrayList<HorseJockeyCombinations> horseJockeyCombinations) {
        this.horseJockeyCombinations = horseJockeyCombinations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OutputResultDto jockeyDto = (OutputResultDto) o;
        return Objects.equals(id, jockeyDto.id) &&
            Objects.equals(name, jockeyDto.name) &&
            Objects.equals(created, jockeyDto.created) ;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, created);
    }

    @Override
    public String toString() {
        return "Jockey{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", created=" + created +
            '}';
    }
}
