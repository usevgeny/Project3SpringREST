package io.evgeny.project3restConsumer.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.evgeny.project3restConsumer.DTO.MeasurementDTO;
import io.evgeny.project3restConsumer.models.Measurement;
import io.evgeny.project3restConsumer.models.Sensor;
import io.evgeny.project3restConsumer.services.MeasurementService;
import io.evgeny.project3restConsumer.services.SensorService;
import io.evgeny.project3restConsumer.utils.MeasurementErrorResponse;
import io.evgeny.project3restConsumer.utils.MeasurementNotCreatedException;
import io.evgeny.project3restConsumer.utils.MeasurementNotFoundException;

@RestController
@RequestMapping("/api/measurements")
public class MeasurementController {

    private final ModelMapper modelMapper;
    private final MeasurementService measurementService;
    private final SensorService sensorService;

    @Autowired
    public MeasurementController(ModelMapper modelMapper, MeasurementService measurementService, SensorService sensorService) {
        this.modelMapper = modelMapper;
        this.measurementService = measurementService;
        this.sensorService = sensorService;
    }

    @GetMapping()
    public List<MeasurementDTO> getAllMeasurements() {
        return measurementService.findAllMeasurements().stream().map(this::convertToMeasurementDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/rainyDaysConut")
    public Map<String, Integer> getRainyDays() {
        Map<String, Integer> responseMap = new HashMap<>();

        responseMap.put("numberOfRainyDays", measurementService.getNumberRainyDays());
        return responseMap;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO,
            BindingResult bindingResult) {

        Measurement measurement = convertToMeasurement(measurementDTO);
        measurement.setEmitter(sensorService.getSensorByName(measurementDTO.getEmitter().getName()));
        System.out.println(measurement.getEmitter().getSensorId());
        
        
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError e : errors) {
                errorMessage.append(e.getField()).append("-").append(e.getDefaultMessage()).append(";");
            }
            throw new MeasurementNotCreatedException(errorMessage.toString());
        }

        measurementService.saveMeasurement(measurement);
        return ResponseEntity.ok(HttpStatus.OK);
    }



    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementNotFoundException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse("Measurement with this id was not found",
                System.currentTimeMillis());
        return new ResponseEntity(response, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementNotCreatedException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }
}
