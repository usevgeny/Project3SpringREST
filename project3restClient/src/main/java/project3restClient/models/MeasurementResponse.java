package project3restClient.models;

import java.util.List;

public class MeasurementResponse {

    private List<MeasurementDTO> measurementsDTO;

    public MeasurementResponse(List<MeasurementDTO> measurementsDTO) {
        super();
        this.measurementsDTO = measurementsDTO;
    }

    public MeasurementResponse() {
    }

    public List<MeasurementDTO> getMeasurementsDTO() {
        return measurementsDTO;
    }

    public void setMeasurementsDTO(List<MeasurementDTO> measurementsDTO) {
        this.measurementsDTO = measurementsDTO;
    }

    @Override
    public String toString() {
        return "MeasurementResponse [measurementsDTO=" + measurementsDTO + "]";
    }

}
