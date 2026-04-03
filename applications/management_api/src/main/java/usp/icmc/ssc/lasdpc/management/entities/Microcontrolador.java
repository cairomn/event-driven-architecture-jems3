package usp.icmc.ssc.lasdpc.management.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import usp.icmc.ssc.lasdpc.management.exceptions.ObjectRequestException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Entity
@JsonSerialize
@Table(name = "microcontroladores")
public class Microcontrolador  { 

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @Column(name = "apelido")
    private String apelido;

    @Column(name = "mac_address")
    private String macAddress;

    @Column(name = "isDeleted", columnDefinition = "boolean default false")
    private Boolean removed = false;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(mappedBy = "microcontrolador", targetEntity = Sensor.class)
    private List<Sensor> sensores = new ArrayList<Sensor>();;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(mappedBy = "microcontrolador", targetEntity = Atuador.class)
    private List<Atuador> atuadores = new ArrayList<Atuador>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sala_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Sala sala;

    public Microcontrolador() {}

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

    public Boolean getRemoved() {
        return removed;
    }

    public void setRemoved(Boolean removed) {
        this.removed = removed;
    }

    @JsonIgnore
    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public List<Sensor> getSensores() {
        return sensores;
    }

    public void setSensores(List<Sensor> sensores) {
        this.sensores = sensores;
    }

    public List<Atuador> getAtuadores() {
        return atuadores;
    }

    public void setAtuadores(List<Atuador> atuadores) {
        this.atuadores = atuadores;
    }

    public void addSensor(Sensor sensor) {
        if (this.sensores.isEmpty()) {
            this.sensores = new ArrayList<Sensor>();
        }

        this.sensores.add(sensor);
    }

    public void addAtuador(Atuador atuador) {
        if (this.atuadores.isEmpty()) {
            this.atuadores = new ArrayList<Atuador>();
        }

        this.atuadores.add(atuador);
    }

    @JsonProperty("sala")
    private void unpackSala(Map<String,Object> sala) throws ObjectRequestException {
        String id = (String) sala.get("id");
        if (id.equals("")) throw new ObjectRequestException("Sala não informada.");
        this.setSala(new Sala());
        this.getSala().setId(UUID.fromString(id));
    }
}
