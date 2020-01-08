package at.ac.tuwien.sepm.assignment.individual.entity;

import at.ac.tuwien.sepm.assignment.individual.rest.dto.HorseJockeyCombinations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class OutputResult {
    private Integer id;
    private String name;
    private LocalDateTime created;
    private ArrayList<HorseJockeyCombinations> horseJockeyCombinations;

    public OutputResult() {
    }

    public OutputResult(Integer id, String name, LocalDateTime created, ArrayList<HorseJockeyCombinations> horseJockeyCombinations) {
        this.id = id;
        this.name = name;
        this.created = created;
        this.horseJockeyCombinations = horseJockeyCombinations;
    }

    public OutputResult(Integer id, String name, LocalDateTime created) {
        this.id = id;
        this.name = name;
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

}
