package usp.icmc.ssc.lasdpc.mqttkafkabridge.dtos;

import com.google.gson.JsonObject;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import java.util.Objects;

public class StatusCheckDTO implements Serializable {

    private String id;
    private Boolean on;
    private Integer temp;
    private Integer mode;
    private UUID atuador;
    private UUID sensor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getOn() {
        return on;
    }

    public void setOn(Boolean on) {
        this.on = on;
    }

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

    public UUID getAtuador() {
        return atuador;
    }

    public void setAtuador(UUID atuador) {
        this.atuador = atuador;
    }

    public UUID getSensor() {
        return sensor;
    }

    public void setSensor(UUID sensor) {
        this.sensor = sensor;
    }

    public static StatusCheckDTO from(JsonObject json) {
        StatusCheckDTO statusCheckDTO = new StatusCheckDTO();
        statusCheckDTO.setOn(!json.get("on").isJsonNull() ? json.get("on").getAsBoolean() : null);
        statusCheckDTO.setTemp(!json.get("tem").isJsonNull() ? json.get("tem").getAsInt() : null);
        statusCheckDTO.setMode(!json.get("mod").isJsonNull() ? json.get("mod").getAsInt() : null);
        statusCheckDTO.setAtuador(!json.get("aid").isJsonNull() ? UUID.fromString(json.get("aid").getAsString()) : null);
        statusCheckDTO.setSensor(!json.get("sid").isJsonNull() ? UUID.fromString(json.get("sid").getAsString()) : null);
        return statusCheckDTO;
    }

    @Override
    public String toString() {
        return "{ \"on\":" + this.getOn() + ", \"temp\": " + this.getTemp() + ", \"mode\": " +  this.getMode() + ", \"sensor\": " + this.getSensor() + ", \"atuador\": " + this.getAtuador() + " }";
    }
}
