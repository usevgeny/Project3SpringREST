package project3restClient.app;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import project3restClient.models.MeasurementDTO;
import project3restClient.models.MeasurementResponse;
import project3restClient.models.SensorDTO;

public class MeasurementsGetter {
    private static final Logger LOGGER = Logger.getLogger(MeasurementsGetter.class.getName());
    private static final String url = "http://localhost:8080/api/measurements";
    private static final RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) throws JsonMappingException, JsonProcessingException {
        MeasurementResponse response = getObjectsViaPreparedResponseClass();
       // System.out.println(getMeasurementsDto());
        System.out.println(response);
    }

    public static List<MeasurementDTO> getMeasurementsDto() {
        List<MeasurementDTO> measurementsDTO = null;

        try {
            measurementsDTO = getObjectsManually();
        } catch (Exception e) {
            LOGGER.warning(e.getMessage());
        }

        return measurementsDTO;
    }

    public static MeasurementResponse getObjectsViaPreparedResponseClass() {
        LOGGER.info("Sending Get Request");
        // List<MeasurementDTO> response = restTemplate.getForObject(url, List.class);
        MeasurementResponse response = restTemplate.getForObject(url, MeasurementResponse.class);
        System.out.println(response);
        return response;
    }

    public static List<MeasurementDTO> getObjectsManually() throws JsonMappingException, JsonProcessingException {
        List<MeasurementDTO> measurements = new ArrayList<>();

        String response = restTemplate.getForObject(url, String.class);
        // Parsing JSON with Jackson
        ObjectMapper mapper = new ObjectMapper();
        JsonNode objects = mapper.readTree(response);

        for (int i = 0; i < objects.size(); i++) {
            SensorDTO sensorDTO = new SensorDTO(String.valueOf(objects.get(i).get("emitter").get("name").asText()));
            MeasurementDTO measurementDTO = new MeasurementDTO();

            measurementDTO.setEmitter(sensorDTO);
            measurementDTO.setValue(Float.parseFloat(objects.get(i).get("value").asText()));
            measurementDTO.setRaining(Boolean.parseBoolean(objects.get(i).get("raining").asText()));
            measurementDTO.setRegisterTime(LocalDateTime.parse(objects.get(i).get("registerTime").asText()));

            measurements.add(measurementDTO);

        }
        return measurements;
    }
}
