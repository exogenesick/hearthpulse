package it.pajak.hearthpulse.pulse.http;

import it.pajak.hearthpulse.pulse.mongodb.Pulse;
import it.pajak.hearthpulse.pulse.mongodb.PulsesRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
public class PulsesController {
    private PulsesRepository pulsesRepository;

    public PulsesController(PulsesRepository pulsesRepository) {
        this.pulsesRepository = pulsesRepository;
    }

    @RequestMapping(value = "/online")
    public List<Pulse> online(
    ) {
        List<Pulse> pulses = null;
        try {
            pulses = pulsesRepository.findByTimestampGreaterThan(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse("2017-08-16T14:00:00.439Z"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return pulses;
    }
}
