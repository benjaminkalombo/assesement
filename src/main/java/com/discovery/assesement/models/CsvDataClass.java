package com.discovery.assesement.models;
import javax.persistence.*;

@Entity
@Table(name = "graph")
public class CsvDataClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fromPlanet;
    private String toPlanet;
    private Long distance;

    public String getFromPlanet() {
        return fromPlanet;
    }

    public void setFromPlanet(String fromPlanet) {
        this.fromPlanet = fromPlanet;
    }

    public String getToPlanet() {
        return toPlanet;
    }

    public void setToPlanet(String toPlanet) {
        this.toPlanet = toPlanet;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }


}
