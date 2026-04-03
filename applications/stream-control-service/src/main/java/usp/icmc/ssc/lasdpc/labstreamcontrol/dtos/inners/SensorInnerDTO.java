package usp.icmc.ssc.lasdpc.labstreamcontrol.dtos.inners;

import java.util.UUID;

public class SensorInnerDTO {

    private UUID id;

    private String apelido;

    private int tipoSensor;

    private Integer status;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public int getTipoSensor() {
        return tipoSensor;
    }

    public void setTipoSensor(int tipoSensor) {
        this.tipoSensor = tipoSensor;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
