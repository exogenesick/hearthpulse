package it.pajak.hearthpulse.pulse.mongodb;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "pulses")
public class Pulse {
    public String username;
    public String display_name;
    public String current_hearthstone_standard_rank;
    public Date timestamp;
}
