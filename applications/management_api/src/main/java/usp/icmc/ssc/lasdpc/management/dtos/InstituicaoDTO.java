package usp.icmc.ssc.lasdpc.management.dtos;

import usp.icmc.ssc.lasdpc.management.entities.Instituicao;

import java.util.UUID;

public class InstituicaoDTO {

    private UUID id;

    private String nome;

    private String cnpj;

    public InstituicaoDTO() {}

    public InstituicaoDTO(UUID id, String nome, String cnpj) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public static InstituicaoDTO from(Instituicao instituicao) {
        InstituicaoDTO instituicaoDTO = new InstituicaoDTO();
        instituicaoDTO.setId(instituicao.getId());
        instituicaoDTO.setNome(instituicao.getNome());
        instituicaoDTO.setCnpj(instituicao.getCnpj());
        return instituicaoDTO;
    }

}
