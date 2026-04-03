package usp.icmc.ssc.lasdpc.management.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.UUID;

@Entity
@Table(name = "atuadores")
@JsonSerialize
public class Atuador {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @Column(name = "apelido", length = 100)
    private String apelido;

    @Column(name = "tp_atuador")
    private int tipoAtuador;

    @Column(name = "st_atuador", nullable = true)
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "microcontrolador_id")
    private Microcontrolador microcontrolador;

    public final static int ST_ATUADOR_ATIVO = 1;
    public final static int ST_ATUADOR_INATIVO = 2;

    public Atuador() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getApelido() {
        return this.apelido;
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

    @JsonIgnore
    public Microcontrolador getMicrocontrolador() {
        return microcontrolador;
    }

    public void setMicrocontrolador(Microcontrolador microcontrolador) {
        this.microcontrolador = microcontrolador;
    }
}
