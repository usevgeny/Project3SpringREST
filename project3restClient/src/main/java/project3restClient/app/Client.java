package project3restClient.app;

import java.util.Random;
import java.util.logging.Logger;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import project3restClient.models.MeasurementDTO;
import project3restClient.models.SensorDTO;

public class Client {

    private static final Logger LOGGER = Logger.getLogger(Client.class.getName());

    public static void main(String[] args) {

        // create new sensor
        SensorDTO sensor = new SensorDTO("sensor2");

        // register new sensor
        registerSensor(sensor);

        // Create measurement MeasurementDTO measurementDTO = new
        // MeasurementDTO(4.5F, true, sensor);

        MeasurementDTO measurementDTO = new MeasurementDTO();
        measurementDTO.setEmitter(sensor);
        Random random = new Random();

        Float minTemperature = 0F;
        Float maxTemperature = 35.0F;
        for (int i = 0; i < 1000; i++) {
            LOGGER.info("MEASUREMENT N°: "+ i + " HAS BEEN SUCCESSFULLY SENT");
            measurementDTO.setValue(random.nextFloat() * maxTemperature);
            measurementDTO.setRaining(random.nextBoolean());
            sendMeasurement(measurementDTO);
        }
    }

    private static void sendMeasurement(MeasurementDTO measurementDTO) {
        final String urlPost = "http://localhost:8080/api/measurements/add";
        sendRequestJSON(urlPost, measurementDTO);
    }

    private static void sendRequestJSON(String url, MeasurementDTO measurementDTO) {
        final RestTemplate restTemplate = new RestTemplate();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> request = new HttpEntity<>(measurementDTO, headers);

        try {
            restTemplate.postForObject(url, request, String.class);

            LOGGER.info("MEASUREMENT HAS BEEN SUCCESSFULLY SENT");
        } catch (HttpClientErrorException e) {
            LOGGER.warning(("AN ERROR HAS OCCURED: " + e.getMessage()));

        }
    }

    private static void registerSensor(SensorDTO sensorDTO) {

        final String url = "http://localhost:8080/api/sensors/registration";
        final RestTemplate restTemplate = new RestTemplate();
        final HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> request = new HttpEntity<>(sensorDTO, headers);

        try {
            restTemplate.postForObject(url, request, String.class);
            LOGGER.info("SENSOR HAS BEEN SUCCESSFULLY REGISTERED");
        } catch (HttpClientErrorException e) {
            LOGGER.warning(("AN ERROR HAS OCCURED DURING SENSOR REGISRATION: " + e.getMessage()));

        }

    }

}
