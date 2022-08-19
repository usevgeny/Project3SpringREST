package io.evgeny.project3restConsumer.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name="measurement")
public class Measurement {
    

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    
    @Column(name="value")
    @NotNull(message="Should not be null")
    private Float value;
    
    @Column(name="raining")
    private boolean raining;
    
    @Column(name="register_time")
    private LocalDateTime registerTime;
    
    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "sensor_id")
    //@JsonBackReference can be used to avoid infinite recursion 
    private Sensor emitter;

    public Measurement(int id, Float value, boolean raining, LocalDateTime registerTime,
            Sensor emitter) {
        super();
        this.id = id;
        this.value = value;
        this.raining = raining;
        this.registerTime = registerTime;
        this.emitter = emitter;
    }
    
    public Measurement() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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


    public Sensor getEmitter() {
        return emitter;
    }

    public void setEmitter(Sensor emitter) {
        this.emitter = emitter;
    }

    public LocalDateTime getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(LocalDateTime registerTime) {
        this.registerTime = registerTime;
    }




}