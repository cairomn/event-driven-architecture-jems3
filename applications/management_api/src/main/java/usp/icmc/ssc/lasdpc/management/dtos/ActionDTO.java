package usp.icmc.ssc.lasdpc.management.dtos;

import java.util.UUID;

public class ActionDTO {

    private UUID actuatorID;

    private UUID microID;

    private Double valor;

    private String topic;

    private String k6Topic;

    public UUID getActuatorID() { return this.actuatorID; }

    public void setActuatorID(UUID id) { this.actuatorID = id; }

    public UUID getMicroID() { return this.microID; }

    public void setMicroID(UUID microID) { this.microID = microID; }

    public Double getValor() { return this.valor; }

    public void setValor(Double valor) { this.valor = valor; }

    public String getTopic() { return this.topic; }

    public void setTopic(String topic) { this.topic = topic; }

    public String getK6Topic() { return this.topic; }

    public void setK6Topic(String topic) { this.topic = topic; }
}
