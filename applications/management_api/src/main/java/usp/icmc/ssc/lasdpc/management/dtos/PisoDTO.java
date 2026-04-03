package usp.icmc.ssc.lasdpc.management.dtos;

import usp.icmc.ssc.lasdpc.management.entities.Bloco;
import usp.icmc.ssc.lasdpc.management.entities.Piso;
import usp.icmc.ssc.lasdpc.management.entities.Sala;

import java.util.List;
import java.util.UUID;

public class PisoDTO {

    private UUID id;

    private String nome;

    private Bloco bloco;

    private List<Sala> salas;

    public PisoDTO() {}

    public PisoDTO(UUID id, String nome, Bloco bloco, List<Sala> salas) {
        this.id = id;
        this.nome = nome;
        this.bloco = bloco;
        this.salas = salas;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Bloco getBloco() {
        return bloco;
    }

    public void setBloco(Bloco bloco) {
        this.bloco = bloco;
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public void setSalas(List<Sala> salas) {
        this.salas = salas;
    }

    public static PisoDTO from(Piso piso) {
        PisoDTO pisoDTO = new PisoDTO();
        pisoDTO.setId(piso.getId());
        pisoDTO.setNome(piso.getNome());
        pisoDTO.setBloco(piso.getBloco());
        pisoDTO.setSalas(piso.getSalas());
        return pisoDTO;
    }
}
