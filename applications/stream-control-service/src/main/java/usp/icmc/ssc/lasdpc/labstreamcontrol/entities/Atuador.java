package usp.icmc.ssc.lasdpc.labstreamcontrol.entities;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import usp.icmc.ssc.lasdpc.labstreamcontrol.dtos.inners.AtuadorInnerDTO;

@Document(collection = "atuadores")
public class Atuador {

    @Id
    private String id;

    @Field("atuadorID")
    private UUID atuadorID;

    @Field("st_ativo")
    private boolean isAtivo = true;

    @DBRef
    private Microcontrolador microcontrolador;

    public Atuador(UUID atuadorID, Microcontrolador microcontrolador) {
        this.atuadorID = atuadorID;
        this.microcontrolador = microcontrolador;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isAtivo() {
        return isAtivo;
    }

    public UUID getAtuadorID() {
        return atuadorID;
    }

    public void setAtuadorID(UUID atuadorID) {
        this.atuadorID = atuadorID;
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

    public static Atuador from(AtuadorInnerDTO sensorInnerDTO, Microcontrolador microcontrolador) {
        Atuador atuador = new Atuador(sensorInnerDTO.getId(), microcontrolador);
        atuador.setAtivo(sensorInnerDTO.getStatus() == 1);
        return atuador;
    }
}
