package it.pajak.hearthpulse.operation.fetch;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.pajak.hearthpulse.pulse.mongodb.Pulse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Fetch {
    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;
    private FetchProperties fetchProperties;

    public Fetch(RestTemplate restTemplate, ObjectMapper objectMapper, FetchProperties fetchProperties) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.fetchProperties = fetchProperties;
    }

    public ArrayList<Pulse> execute() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(fetchProperties.getSourceUrl())
            .queryParam("filterId", "hearthstone-standard-streams");

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<String> response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, String.class);

        String jsonString = response.getBody();
        Pulse[] pulses;

        try {
            pulses = objectMapper.readValue(jsonString, Pulse[].class);
        } catch (IOException e) {
            return null;
        }

        return new ArrayList<>(Arrays.asList(pulses));
    }
}
