package io.evgeny.project3restConsumer.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.evgeny.project3restConsumer.models.Measurement;
import io.evgeny.project3restConsumer.models.Sensor;

public interface MeasurementRepository extends JpaRepository<Measurement, Integer>{
    
    public List<Measurement> findByEmitter(Sensor sensor);
    public List<Measurement> findByRaining(boolean raining);

}
