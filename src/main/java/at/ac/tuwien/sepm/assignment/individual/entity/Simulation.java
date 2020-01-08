package at.ac.tuwien.sepm.assignment.individual.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class Simulation {
    private Integer id;
    private String name;
    private LocalDateTime created;
    private ArrayList<SimulationParticipants> simulationParticipants;

    public Simulation(){

    }

    public Simulation(Integer id, String name, Double skills, LocalDateTime created, LocalDateTime updated) {
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

    public ArrayList<SimulationParticipants> getSimulationParticipants() {
        return simulationParticipants;
    }

    public void setSimulationParticipants(ArrayList<SimulationParticipants> simulationData) {
        this.simulationParticipants = simulationData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Simulation that = (Simulation) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(created, that.created);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, created);
    }

    @Override
    public String toString() {
        return "Simulation{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", created=" + created +
            '}';
    }
}
