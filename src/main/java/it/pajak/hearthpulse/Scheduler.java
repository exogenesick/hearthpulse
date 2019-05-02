package it.pajak.hearthpulse;

import it.pajak.hearthpulse.operation.fetch.Fetch;
import it.pajak.hearthpulse.pulse.mongodb.Pulse;
import it.pajak.hearthpulse.pulse.mongodb.PulsesRepository;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;

public class Scheduler {
    private Fetch fetchOperation;
    private PulsesRepository pulsesRepository;

    public Scheduler(Fetch fetchOperation, PulsesRepository pulsesRepository) {
        this.fetchOperation = fetchOperation;
        this.pulsesRepository = pulsesRepository;
    }

    private ArrayList<Pulse> fetchPlayers() {
        return fetchOperation.execute();
    }

    @Scheduled(cron = "${pulse.fetch.scheduler}")
    public void scheduled() {
        pulsesRepository.insert(fetchPlayers());
    }
}
