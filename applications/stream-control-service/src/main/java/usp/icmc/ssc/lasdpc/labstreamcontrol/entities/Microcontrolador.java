package usp.icmc.ssc.lasdpc.labstreamcontrol.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import usp.icmc.ssc.lasdpc.labstreamcontrol.dtos.inners.MicrocontroladorInnerDTO;

import java.util.UUID;

@Document(collection = "microcontroladores")
public class Microcontrolador {

    @Id
    private String id;

    @Field("macAddress")
    private String macAddress;

    @Field("microcontroladorID")
    private UUID microcontroladorID;

    @Field("st_ativo")
    private boolean isAtivo = true;

    public Microcontrolador(UUID microcontroladorID) {
        this.microcontroladorID = microcontroladorID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public UUID getMicrocontroladorID() {
        return microcontroladorID;
    }

    public void setMicrocontroladorID(UUID microcontroladorID) {
        this.microcontroladorID = microcontroladorID;
    }

    public boolean isAtivo() {
        return isAtivo;
    }

    public void setAtivo(boolean ativo) {
        isAtivo = ativo;
    }

    public static Microcontrolador from(MicrocontroladorInnerDTO microcontroladorInnerDTO) {
        Microcontrolador microcontrolador = new Microcontrolador(microcontroladorInnerDTO.getId());
        microcontrolador.setMacAddress(microcontroladorInnerDTO.getMacAddress());
        microcontrolador.setAtivo(true); // Verificar essa regra.
        return microcontrolador;
    }
}
