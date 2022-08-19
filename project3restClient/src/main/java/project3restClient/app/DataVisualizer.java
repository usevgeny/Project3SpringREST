package project3restClient.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import project3restClient.models.MeasurementDTO;
import project3restClient.models.MeasurementsResponse;

public class DataVisualizer {

    public static MeasurementsGetter measurementsGetter;

    public static void main(String[] args) {
        List<Float> data = getTemperaturesFromServer();
        visualizeData(data);
    }

    private static List<Float> getTemperaturesFromServer() {
        final RestTemplate restTemplate = new RestTemplate();
        final String url = "http://localhost:8080/api/measurements";
        List<Float> temps = new ArrayList<>();

        List<MeasurementDTO> jsonResponse = MeasurementsGetter.getMeasurementsDto();

        if (jsonResponse == null)
            return Collections.emptyList();

        //getting values for collected temperatures
        for (MeasurementDTO measurement : jsonResponse) {
            temps.add(measurement.getValue());
        }
        return jsonResponse.stream().map(MeasurementDTO::getValue).collect(Collectors.toList());
    }



    private static void visualizeData(List<Float> temperatures) {
        double[] xData = IntStream.range(0, temperatures.size()).asDoubleStream().toArray();
        double[] yData = temperatures.stream().mapToDouble(x -> x).toArray();

        XYChart chart = QuickChart.getChart("Temperatures", "X", "Y", "temperature", xData, yData);

        new SwingWrapper(chart).displayChart();
    }
}