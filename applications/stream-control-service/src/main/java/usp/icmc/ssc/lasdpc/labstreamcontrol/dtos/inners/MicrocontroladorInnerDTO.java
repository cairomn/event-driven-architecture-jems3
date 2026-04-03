package usp.icmc.ssc.lasdpc.labstreamcontrol.dtos.inners;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MicrocontroladorInnerDTO {

    private UUID id;

    private String apelido;

    private String macAddress;

    private List<SensorInnerDTO> sensores = new ArrayList<SensorInnerDTO>();;

    private List<AtuadorInnerDTO> atuadores = new ArrayList<>();

    private SalaInnerDTO sala;

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

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public List<SensorInnerDTO> getSensores() {
        return sensores;
    }

    public void setSensores(List<SensorInnerDTO> sensores) {
        this.sensores = sensores;
    }

    public List<AtuadorInnerDTO> getAtuadores() {
        return atuadores;
    }

    public void setAtuadores(List<AtuadorInnerDTO> atuadores) {
        this.atuadores = atuadores;
    }

    public SalaInnerDTO getSala() {
        return sala;
    }

    public void setSala(SalaInnerDTO sala) {
        this.sala = sala;
    }
}
