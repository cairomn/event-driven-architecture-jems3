package usp.icmc.ssc.lasdpc.management.dtos;

import usp.icmc.ssc.lasdpc.management.entities.Bloco;
import usp.icmc.ssc.lasdpc.management.entities.Instituicao;
import usp.icmc.ssc.lasdpc.management.entities.Piso;

import java.util.List;
import java.util.UUID;

public class BlocoDTO {

    private UUID id;

    private String nome;

    private Instituicao instituicao;

    private List<Piso> pisos;

    public BlocoDTO() {}

    public BlocoDTO(UUID id, String nome, Instituicao instituicao, List<Piso> pisos) {
        this.id = id;
        this.nome = nome;
        this.instituicao = instituicao;
        this.pisos = pisos;
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

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public List<Piso> getPisos() {
        return pisos;
    }

    public void setPisos(List<Piso> pisos) {
        this.pisos = pisos;
    }

    public static BlocoDTO from(Bloco bloco) {
        BlocoDTO blocoDTO = new BlocoDTO();
        blocoDTO.setId(bloco.getId());
        blocoDTO.setNome(bloco.getNome());
        blocoDTO.setInstituicao(bloco.getInstituicao());
        blocoDTO.setPisos(bloco.getPisos());
        return blocoDTO;
    }

}
