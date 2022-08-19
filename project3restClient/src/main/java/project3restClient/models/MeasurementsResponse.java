package project3restClient.models;

import java.util.ArrayList;
import java.util.List;

public class MeasurementsResponse {
    List<MeasurementDTO> measurements = new ArrayList<>();

    public List<MeasurementDTO> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<MeasurementDTO> measurements) {
        this.measurements = measurements;
    }
}
