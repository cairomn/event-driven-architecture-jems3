package usp.icmc.ssc.lasdpc.mqttkafkabridge.dtos;

import com.google.gson.JsonObject;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class MeasureKafkaDTO implements Serializable {

    private Date timestamp;
    private Date timestampBridge;
    private String valor;
    private Integer unit;
    private Integer typeSensor;
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

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Integer getTypeSensor() {
        return typeSensor;
    }

    public void setTypeSensor(Integer typeSensor) {
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

    public static MeasureKafkaDTO from(JsonObject json) {
        MeasureKafkaDTO measureKafkaDTO = new MeasureKafkaDTO();
        measureKafkaDTO.setUnit(json.get("uni").getAsInt());
        measureKafkaDTO.setValor(json.get("val").getAsString());
        measureKafkaDTO.setTypeSensor(json.get("sen").getAsInt());
        measureKafkaDTO.setMicroID(UUID.fromString(json.get("mid").getAsString()));
        measureKafkaDTO.setSensorID(UUID.fromString(json.get("sid").getAsString()));
        measureKafkaDTO.setTimestamp(new Date(json.get("tim").getAsLong()));
        return measureKafkaDTO;
    }

    @Override
    public String toString() {
        return "{ \"timestamp\":" + this.getTimestamp() + ", \"valor\": " + this.getValor() + " }";
    }
}
