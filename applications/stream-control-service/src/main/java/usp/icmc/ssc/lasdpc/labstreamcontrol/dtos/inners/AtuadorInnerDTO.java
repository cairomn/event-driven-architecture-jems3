package usp.icmc.ssc.lasdpc.labstreamcontrol.dtos.inners;

import java.util.UUID;

public class AtuadorInnerDTO {

    private UUID id;

    private String apelido;

    private int tipoAtuador;

    private Integer status;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public int getTipoAtuador() {
        return tipoAtuador;
    }

    public void setTipoAtuador(int tipoAtuador) {
        this.tipoAtuador = tipoAtuador;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
