package io.evgeny.project3restConsumer.DTO;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import io.evgeny.project3restConsumer.models.Sensor;

public class MeasurementDTO {

    @NotNull(message = "Should not be null")
    private Float value;

    private boolean raining;

    private SensorDTO emitter;
    
    private LocalDateTime registerTime;

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getEmitter() {
        return emitter;
    }

    public void setEmitter(SensorDTO emitter) {
        this.emitter = emitter;
    }

    public LocalDateTime getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(LocalDateTime registerTime) {
        this.registerTime = registerTime;
    }
    

}
