package usp.icmc.ssc.lasdpc.management.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import usp.icmc.ssc.lasdpc.management.exceptions.ObjectRequestException;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Entity
@JsonSerialize
@Table(name = "salas")
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @Column(name = "nome")
    private String nome;

    @ManyToOne
    @JoinColumn(name="piso_id", nullable=false)
    private Piso piso;

    @OneToMany(mappedBy = "sala")
    private List<Microcontrolador> microcontroladores;

    public Sala() {}

    public Sala(UUID id, String nome, Piso piso) {
        this.id = id;
        this.nome = nome;
        this.piso = piso;
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

    public Piso getPiso() {
        return piso;
    }

    public void setPiso(Piso piso) {
        this.piso = piso;
    }

    public List<Microcontrolador> getMicrocontroladores() {
        return microcontroladores;
    }

    public void setMicrocontroladores(List<Microcontrolador> microcontroladores) {
        this.microcontroladores = microcontroladores;
    }

    @JsonProperty("piso")
    private void unpackPiso(Map<String,Object> piso) throws ObjectRequestException {
        String id = (String) piso.get("id");
        if (id.equals("") || id == null) throw new ObjectRequestException("Piso não informado.");
        this.setPiso(new Piso());
        this.getPiso().setId(UUID.fromString(id));
    }
}
