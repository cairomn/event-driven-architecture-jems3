package usp.icmc.ssc.lasdpc.labstreamcontrol.entities;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import usp.icmc.ssc.lasdpc.labstreamcontrol.dtos.inners.SensorInnerDTO;

@Document(collection = "sensores")
public class Sensor {

    @Id
    private String id;

    @Field("sensorID")
    private UUID sensorID;

    @Field("st_ativo")
    private boolean isAtivo = true;

    @DBRef
    private Microcontrolador microcontrolador;

    public Sensor(UUID sensorID, Microcontrolador microcontrolador) {
        this.sensorID = sensorID;
        this.microcontrolador = microcontrolador;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UUID getSensorID() {
        return sensorID;
    }

    public void setSensorID(UUID sensorID) {
        this.sensorID = sensorID;
    }

    public boolean isAtivo() {
        return isAtivo;
    }

    public void setAtivo(boolean ativo) {
        isAtivo = ativo;
    }

    public Microcontrolador getMicrocontrolador() {
        return microcontrolador;
    }

    public void setMicrocontrolador(Microcontrolador microcontrolador) {
        this.microcontrolador = microcontrolador;
    }
    
    public static Sensor from(SensorInnerDTO sensorInnerDTO, Microcontrolador microcontrolador) {
        Sensor sensor = new Sensor(sensorInnerDTO.getId(), microcontrolador);
        sensor.setAtivo(sensorInnerDTO.getStatus() == 1);
        return sensor;
    } 
}
