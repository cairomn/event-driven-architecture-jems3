package usp.icmc.ssc.lasdpc.labstreamcontrol.dtos.resps;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import usp.icmc.ssc.lasdpc.labstreamcontrol.entities.Microcontrolador;

public class MicrocontroladorResp {
    
    private String id;

    private UUID microcontroladorID;
    
    private List<SensorResp> sensores;

    private List<AtuadorResp> atuadores;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UUID getMicrocontroladorID() {
        return microcontroladorID;
    }

    public void setMicrocontroladorID(UUID microcontroladorID) {
        this.microcontroladorID = microcontroladorID;
    }

    public List<SensorResp> getSensores() {
        return sensores;
    }

    public void setSensores(List<SensorResp> sensores) {
        this.sensores = sensores;
    }

    public List<AtuadorResp> getAtuadores() {
        return atuadores;
    }

    public void setAtuadores(List<AtuadorResp> atuadores) {
        this.atuadores = atuadores;
    }

    public void addAtuador(AtuadorResp a) {
        if (this.atuadores == null) {
            this.atuadores = new ArrayList<>();
        }

        this.atuadores.add(a);
    }

    public void addSensor(SensorResp a) {
        if (this.sensores == null) {
            this.sensores = new ArrayList<>();
        }

        this.sensores.add(a);
    }

    public static MicrocontroladorResp from(Microcontrolador microcontrolador) {
        MicrocontroladorResp microcontroladorResp = new MicrocontroladorResp();
        microcontroladorResp.setId(microcontrolador.getId());
        microcontroladorResp.setMicrocontroladorID(microcontrolador.getMicrocontroladorID());
        return microcontroladorResp;
    }

}
