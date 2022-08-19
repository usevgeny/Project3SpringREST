package project3restClient.models;

import java.time.LocalDateTime;


public class MeasurementDTO {

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

    public MeasurementDTO(Float value, boolean raining, SensorDTO emitter, LocalDateTime registerTime) {
        super();
        this.value = value;
        this.raining = raining;
        this.emitter = emitter;
        this.registerTime = registerTime;
    }

    public MeasurementDTO() {
        super();
    }

    @Override
    public String toString() {
        return "MeasurementDTO [value=" + value + ", raining=" + raining + ", emitter=" + emitter + ", registerTime="
                + registerTime + "]";
    }



}
