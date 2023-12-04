package xyz.anomatver.soa3.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ConsulServiceResponse {
    @JsonProperty("Port")
    Integer port;

    @JsonProperty("Address")
    String address;

}