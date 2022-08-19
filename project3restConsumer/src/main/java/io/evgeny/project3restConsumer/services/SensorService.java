package io.evgeny.project3restConsumer.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.evgeny.project3restConsumer.models.Sensor;
import io.evgeny.project3restConsumer.repositories.SensorRepository;
import io.evgeny.project3restConsumer.utils.SensorNotFoundException;

@Service
public class SensorService {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public Sensor findOne(Integer sensorId) {
        Optional<Sensor> foundSensor = sensorRepository.findById(sensorId);
        return foundSensor.orElseThrow(SensorNotFoundException::new);
    }
    
    public Sensor getSensorByName(String sensorName) {
        Optional<Sensor> foundSensor = sensorRepository.findByName(sensorName);
        return foundSensor.orElseThrow(SensorNotFoundException::new);
    }

    public boolean isSensorExistant(String sensorName) {
        Optional<Sensor> foundSensor = sensorRepository.findByName(sensorName);
        return foundSensor.isPresent();
    }
    
    public List<Sensor> showAllSensors() {
        
        return sensorRepository.findAll();
    }

    @Transactional
    public void createSensor(Sensor sensor) {
        sensorRepository.save(sensor);
    }

    @Transactional
    public void updateSensor(Integer id, Sensor sensor) {
        sensor.setSensorId(id);
        sensorRepository.save(sensor);
    }

    @Transactional
    public void deleteSensor(Integer id) {
        sensorRepository.deleteById(id);
    }

}
