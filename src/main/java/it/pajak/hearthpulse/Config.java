package it.pajak.hearthpulse;

import it.pajak.hearthpulse.operation.fetch.Fetch;
import it.pajak.hearthpulse.pulse.mongodb.PulsesRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableAsync
@EnableScheduling
public class Config {
    @Bean
    public Scheduler scheduler(Fetch fetch, PulsesRepository pulsesRepository) {
        return new Scheduler(fetch, pulsesRepository);
    }

    @Bean
    public MappingMongoConverter mappingMongoConverter(
        MongoDbFactory mongoDbFactory,
        MongoMappingContext mongoMappingContext
    ) {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mongoMappingContext);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));

        return converter;
    }
}
