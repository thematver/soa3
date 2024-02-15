package xyz.anomatver.soa3.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConsulServiceResponse {
    @JsonProperty("Port")
    Integer port;

    @JsonProperty("Address")
    String address;

    public Integer getPort() {
        return this.port;
    }

    public String getAddress() {
        return this.address;
    }

    @JsonProperty("Port")
    public void setPort(Integer port) {
        this.port = port;
    }

    @JsonProperty("Address")
    public void setAddress(String address) {
        this.address = address;
    }
}