package io.evgeny.project3restConsumer.utils;

import java.util.List;

import io.evgeny.project3restConsumer.DTO.MeasurementDTO;

public class MeasurementResponse {
    
    private List<MeasurementDTO> measurementsDTO;

    private MeasurementResponse(List<MeasurementDTO> measurementsDTO) {
        super();
        this.measurementsDTO = measurementsDTO;
    }

    public List<MeasurementDTO> getMeasurementsDTO() {
        return measurementsDTO;
    }

    public void setMeasurementsDTO(List<MeasurementDTO> measurementsDTO) {
        this.measurementsDTO = measurementsDTO;
    }
    

}
