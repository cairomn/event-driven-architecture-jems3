package usp.icmc.ssc.lasdpc.management.dtos;

import usp.icmc.ssc.lasdpc.management.enums.TipoAtuadorEnum;

public class TipoAtuadorDTO {

    private int id;

    private String nome;

    private String descricao;

    public TipoAtuadorDTO() {}

    public TipoAtuadorDTO(int id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static TipoAtuadorDTO from(TipoAtuadorEnum e) {
        TipoAtuadorDTO tipoAtuadorDTO = new TipoAtuadorDTO();
        tipoAtuadorDTO.setId(e.getId());
        tipoAtuadorDTO.setNome(e.getNome());
        tipoAtuadorDTO.setDescricao(e.getDescricao());
        return tipoAtuadorDTO;
    }
}
