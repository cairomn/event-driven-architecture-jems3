package usp.icmc.ssc.lasdpc.management.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.UUID;

@Entity
@Table(name = "sensores")
@JsonSerialize
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @Column(name = "apelido", length = 100)
    private String apelido;

    @Column(name="tp_sensor")
    private int tipoSensor;

    @Column(name = "st_sensor")
    private Integer status;

    @ManyToOne(targetEntity = Microcontrolador.class)
    @JoinColumn(name = "microcontrolador_id")
    private Microcontrolador microcontrolador;

    public static final int ST_SENSOR_ATIVO = 1;
    public static final int ST_SENSOR_INATIVO = 2;

    public Sensor() {}

    public Sensor(UUID id, int tipoSensor, Microcontrolador microcontrolador) {
        this.id = id;
        this.tipoSensor = tipoSensor;
        this.microcontrolador = microcontrolador;
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

    public int getTipoSensor() {
        return tipoSensor;
    }

    public void setTipoSensor(int tipoSensor) {
        this.tipoSensor = tipoSensor;
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
