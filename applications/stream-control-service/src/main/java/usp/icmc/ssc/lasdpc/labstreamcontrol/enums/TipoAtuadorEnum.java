package usp.icmc.ssc.lasdpc.labstreamcontrol.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoAtuadorEnum {
    TP_ATUADOR_AR_CONDICIONADO(1, "TP_ATUADOR_AR_CONDICIONADO", "Ar Condicionado"),
    TP_ACTUATOR_LAMP(2, "TP_ATUADOR_AR_CONDICIONADO", "Ligar/Desligar Lâmpada"),
    TP_ACTUATOR_RELE(3, "TP_ATUADOR_AR_CONDICIONADO", "Ligar/Desligar Relê");

    private int id;
    private String nome;
    private String descricao;

    TipoAtuadorEnum(int id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    @JsonValue
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static TipoAtuadorEnum forValues(int id) {
        for (TipoAtuadorEnum tipoAtuadorEnum : TipoAtuadorEnum.values()) {
            if (tipoAtuadorEnum.id == id) {
                return tipoAtuadorEnum;
            }
        }

        return null;
    }
}
