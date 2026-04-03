package usp.icmc.ssc.lasdpc.labstreamcontrol.dtos.inners;

import java.util.UUID;

public class ActionMqttDTO {

    private UUID id;

    private UUID microID;

    private Double valor;

    private String topic;

    public UUID getId() { return this.id; }

    public void setId(UUID id) { this.id = id; }

    public UUID getMicroID() { return this.microID; }

    public void setMicroID(UUID microID) { this.microID = microID; }

    public Double getValor() { return this.valor; }

    public void setValor(Double valor) { this.valor = valor; }

    public String getTopic() { return this.topic; }

    public void setTopic(String topic) { this.topic = topic; }

}
