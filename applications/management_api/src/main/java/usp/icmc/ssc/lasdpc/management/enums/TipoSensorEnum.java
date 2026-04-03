package usp.icmc.ssc.lasdpc.management.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoSensorEnum {
    TP_SENSOR_TEMPERATURA(1, "TP_SENSOR_TEMPERATURA", "Sensor de Temperatura"),
    TP_SENSOR_UMIDADE(2, "TP_SENSOR_UMIDADE", "Sensor de Umidade"),
    TP_SENSOR_CO2(3, "TP_SENSOR_CO2", "Sensor de Gás"),
    TP_SENSOR_MOVIMENTO(4, "TP_SENSOR_MOVIMENTO", "Sensor de Movimento"),
    TP_SENSOR_ILUMINACAO(5, "TP_SENSOR_ILUMINACAO", "Sensor de Iluminação");

    private int id;
    private String nome;
    private String descricao;

    TipoSensorEnum(int id, String nome, String descricao) {
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
    public static TipoSensorEnum forValues(
        @JsonProperty("id") int id
    ) {
        for (TipoSensorEnum tipoSensorEnum : TipoSensorEnum.values()) {
            if (tipoSensorEnum.id == id) {
                return tipoSensorEnum;
            }
        }

        return null;
    }
}
