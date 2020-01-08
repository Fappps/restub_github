package at.ac.tuwien.sepm.assignment.individual.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Jockey {
    private Integer id;
    private String name;
    private Double skills;
    private LocalDateTime created;
    private LocalDateTime updated;

    public Jockey(){

    }

    public Jockey(Integer id, String name, Double skills, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.name = name;
        this.skills = skills;
        this.created = created;
        this.updated = updated;
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

    public Double getSkills() {
        return skills;
    }

    public void setSkills(Double skills) {
        this.skills = skills;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jockey jockey = (Jockey) o;
        return Objects.equals(id, jockey.id) &&
            Objects.equals(name, jockey.name) &&
            Objects.equals(skills, jockey.skills) &&
            Objects.equals(created, jockey.created) &&
            Objects.equals(updated, jockey.updated);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, skills, created, updated);
    }

    @Override
    public String toString() {
        return "Jockey{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", skills=" + skills +
            ", created=" + created +
            ", updated=" + updated +
            '}';
    }
}
