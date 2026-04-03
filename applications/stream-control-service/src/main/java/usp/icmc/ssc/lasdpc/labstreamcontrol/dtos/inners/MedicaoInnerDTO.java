package usp.icmc.ssc.lasdpc.labstreamcontrol.dtos.inners;

import java.util.Date;
import java.util.UUID;

import usp.icmc.ssc.lasdpc.labstreamcontrol.enums.TipoSensorEnum;
import usp.icmc.ssc.lasdpc.labstreamcontrol.enums.TipoUnidadeMedidaEnum;

public class MedicaoInnerDTO {
    
    private Date timestamp;

    private Date timestampBridge;

    private String valor;

    private TipoUnidadeMedidaEnum unit;

    private TipoSensorEnum typeSensor;

    private UUID sensorID;

    private UUID microID;

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Date getTimestampBridge() {
        return timestampBridge;
    }

    public void setTimestampBridge(Date timestampBridge) {
        this.timestampBridge = timestampBridge;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public TipoUnidadeMedidaEnum getUnit() {
        return unit;
    }

    public void setUnit(TipoUnidadeMedidaEnum unit) {
        this.unit = unit;
    }

    public TipoSensorEnum getTypeSensor() {
        return typeSensor;
    }

    public void setTypeSensor(TipoSensorEnum typeSensor) {
        this.typeSensor = typeSensor;
    }

    public UUID getSensorID() {
        return sensorID;
    }

    public void setSensorID(UUID sensorID) {
        this.sensorID = sensorID;
    }

    public UUID getMicroID() {
        return microID;
    }

    public void setMicroID(UUID microID) {
        this.microID = microID;
    }

    @Override
    public String toString() {
        return "{ \"timestamp\":" + this.getTimestamp() + ", \"valor\": " + this.getValor() + " }";
    }
}
