package io.evgeny.project3restConsumer.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import io.evgeny.project3restConsumer.models.Sensor;
import io.evgeny.project3restConsumer.services.SensorService;

@Component
public class SensorValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    private SensorValidator(SensorService sensorService) {
        super();
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;
        if(sensorService.isSensorExistant(sensor.getName())) {
            errors.rejectValue("name", "", "This sensor name is already taken");
        }
    }

}
