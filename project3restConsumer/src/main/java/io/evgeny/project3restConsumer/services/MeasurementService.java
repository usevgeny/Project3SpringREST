package io.evgeny.project3restConsumer.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.evgeny.project3restConsumer.DTO.MeasurementDTO;
import io.evgeny.project3restConsumer.models.Measurement;
import io.evgeny.project3restConsumer.models.Sensor;
import io.evgeny.project3restConsumer.repositories.MeasurementRepository;
import io.evgeny.project3restConsumer.utils.MeasurementNotFoundException;

@Service
public class MeasurementService {

    private final MeasurementRepository measurementRepository;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    @Transactional
    public void saveMeasurement(Measurement measurement) {

        enrichMeasurement(measurement);
        measurementRepository.save(measurement);
    }

    @Transactional
    public void updateMeasurement(Integer id, Measurement measurement) {
        measurement.setId(id);
        measurementRepository.save(measurement);
    }

    @Transactional
    public void deleteMeasurementById(Integer id) {
        measurementRepository.deleteById(id);
    }

    public Measurement findOne(Integer measurementId) {
        Optional<Measurement> foundMeasurement = measurementRepository.findById(measurementId);
        return foundMeasurement.orElseThrow(MeasurementNotFoundException::new);
    }

    public List<Measurement> findAllMeasurements() {
        return measurementRepository.findAll();
    }

    public Integer getNumberRainyDays() {
        return measurementRepository.findByRaining(true).size();
    }

    public List<Measurement> findBySensor(Sensor sensor) {
        List<Measurement> allMeasuremmentsOfSensor;
        allMeasuremmentsOfSensor = sensor != null ? measurementRepository.findByEmitter(sensor) : new ArrayList<>();
        return allMeasuremmentsOfSensor;
    }

    private void enrichMeasurement(Measurement measurement) {
        LocalDateTime measurementTime = LocalDateTime.now();
        measurement.setRegisterTime(measurementTime);
    }
}
