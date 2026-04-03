package usp.icmc.ssc.lasdpc.management.dtos;

import usp.icmc.ssc.lasdpc.management.enums.TipoSensorEnum;

public class TipoSensorDTO {

    private int id;

    private String nome;

    private String descricao;

    public TipoSensorDTO() {}

    public TipoSensorDTO(int id, String nome, String descricao) {
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

    public static TipoSensorDTO from(TipoSensorEnum e) {
        TipoSensorDTO tipoSensorDTO = new TipoSensorDTO();
        tipoSensorDTO.setId(e.getId());
        tipoSensorDTO.setNome(e.getNome());
        tipoSensorDTO.setDescricao(e.getDescricao());
        return tipoSensorDTO;
    }
}
