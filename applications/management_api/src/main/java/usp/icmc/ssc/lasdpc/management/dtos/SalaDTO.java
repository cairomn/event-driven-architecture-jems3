package usp.icmc.ssc.lasdpc.management.dtos;

import usp.icmc.ssc.lasdpc.management.entities.Piso;
import usp.icmc.ssc.lasdpc.management.entities.Sala;

import java.util.UUID;

public class SalaDTO {

    private UUID id;

    private String nome;

    private Piso piso;

    public SalaDTO() {}

    public SalaDTO(UUID id, String nome, Piso piso) {
        this.id = id;
        this.nome = nome;
        this.piso = piso;
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

    public Piso getPiso() {
        return piso;
    }

    public void setPiso(Piso piso) {
        this.piso = piso;
    }

    public static SalaDTO from(Sala sala) {
        SalaDTO salaDTO = new SalaDTO();
        salaDTO.setId(sala.getId());
        salaDTO.setNome(sala.getNome());
        salaDTO.setPiso(sala.getPiso());
        return salaDTO;
    }
}
