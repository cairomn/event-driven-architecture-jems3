package usp.icmc.ssc.lasdpc.labstreamcontrol.enums;

public enum TipoUnidadeMedidaEnum {
    TP_UNIT_MEASURE_CELSIUS(1, "TP_UNIT_MEASURE_CELSIUS", "ºC"),
    TP_UNIT_MEASURE_PERCENTAGE(2, "TP_UNIT_MEASURE_PERCENTAGE", "%"),
    TP_UNIT_MEASURE_KOHM(3, "TP_UNIT_MEASURE_KOHM", "kOhm"),
    TP_UNIT_MEASURE_LUX(4, "TP_UNIT_MEASURE_LUX", "lux"),
    TP_UNIT_MEASURE_PPM(5, "TP_UNIT_MEASURE_PPM", "ppm"),
    TP_UNIT_MEASURE_HPA(6, "TP_UNIT_MEASURE_HPA", "hpa"),
    TP_UNIT_MEASURE_LINEAR_ACELERATION(7, "TP_UNIT_MEASURE_LINEAR_ACELERATION", "");

    private int id;
    private String nome;
    private String descricao;

    TipoUnidadeMedidaEnum(int id, String nome, String descricao) {
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
}
