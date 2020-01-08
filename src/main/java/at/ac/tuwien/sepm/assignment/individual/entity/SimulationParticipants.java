package at.ac.tuwien.sepm.assignment.individual.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class SimulationParticipants {
    private Integer horseId;
    private Integer jockeyId;
    private Integer luckFactor;

    public SimulationParticipants(){

    }

    public SimulationParticipants(Integer horseId, Integer jockeyId, Integer luckFactor) {
        this.horseId = horseId;
        this.jockeyId = jockeyId;
        this.luckFactor = luckFactor;
    }

    public Integer getHorseId() {
        return horseId;
    }

    public void setHorseId(Integer horseId) {
        this.horseId = horseId;
    }

    public Integer getJockeyId() {
        return jockeyId;
    }

    public void setJockeyId(Integer jockeyId) {
        this.jockeyId = jockeyId;
    }

    public Integer getLuckFactor() {
        return luckFactor;
    }

    public void setLuckFactor(Integer luckFactor) {
        this.luckFactor = luckFactor;
    }
}
