package usp.icmc.ssc.lasdpc.labstreamcontrol.dtos.inners;

import java.util.UUID;

public class StatusCheckInnerDTO {

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
}
