package usp.icmc.ssc.lasdpc.management.dtos;

import usp.icmc.ssc.lasdpc.management.entities.Atuador;
import usp.icmc.ssc.lasdpc.management.entities.Microcontrolador;
import usp.icmc.ssc.lasdpc.management.entities.Sensor;

import java.util.List;
import java.util.UUID;

public class MicrocontroladorDTO {

    private UUID id;

    private String macAddress;

    private String apelido;

    private List<Sensor> sensores;

    private List<Atuador> atuadores;

    public MicrocontroladorDTO() {}

    public MicrocontroladorDTO(UUID id, String macAddress, List<Sensor> sensores, List<Atuador> atuadores) {
        this.id = id;
        this.macAddress = macAddress;
        this.sensores = sensores;
        this.atuadores = atuadores;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public List<Sensor> getSensores() {
        return sensores;
    }

    public void setSensores(List<Sensor> sensores) {
        this.sensores = sensores;
    }

    public List<Atuador> getAtuadores() {
        return atuadores;
    }

    public void setAtuadores(List<Atuador> atuadores) {
        this.atuadores = atuadores;
    }

    public static MicrocontroladorDTO from(Microcontrolador microcontrolador) {
        MicrocontroladorDTO microcontroladorDTO = new MicrocontroladorDTO();
        microcontroladorDTO.setId(microcontrolador.getId());
        microcontroladorDTO.setMacAddress(microcontrolador.getMacAddress());
        microcontroladorDTO.setApelido(microcontrolador.getApelido());
        microcontroladorDTO.setSensores(microcontrolador.getSensores());
        microcontroladorDTO.setAtuadores(microcontrolador.getAtuadores());
        return microcontroladorDTO;
    }
}
