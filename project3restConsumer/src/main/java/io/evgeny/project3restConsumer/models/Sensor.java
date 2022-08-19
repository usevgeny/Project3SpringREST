package io.evgeny.project3restConsumer.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@Table(name = "Sensor")
public class Sensor {

    @Id
    @Column(name = "sensor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sensorId;

    @Column(name = "name")
    @NotEmpty(message = "Should not be empty")
    @Size(min = 2, max = 30, message = "Should be min 2 symmbols max 3O symbols")
    private String name;

    @OneToMany(mappedBy = "emitter")
    private List<Measurement> measurements;

    private Sensor() {
    }

    private Sensor(int sensorId, String name, List<Measurement> measurements) {
        super();
        this.sensorId = sensorId;
        this.name = name;
        this.measurements = measurements;
    }

    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }
}