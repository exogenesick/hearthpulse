package it.pajak.hearthpulse.pulse.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface PulsesRepository extends MongoRepository<Pulse, String> {
    List<Pulse> findByTimestampGreaterThan(Date dateFrom);
}
