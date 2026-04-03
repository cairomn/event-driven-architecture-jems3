package usp.icmc.ssc.lasdpc.management.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import usp.icmc.ssc.lasdpc.management.exceptions.ObjectRequestException;

import javax.persistence.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Entity
@JsonSerialize
@Table(name = "pisos")
public class Piso {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @Column(name = "nome")
    private String nome;

    @ManyToOne
    @JoinColumn(name="bloco_id", nullable=false)
    private Bloco bloco;

    @OneToMany(mappedBy = "piso")
    private List<Sala> salas;

    public Piso() {}

    public Piso(UUID id, String nome, Bloco bloco, List<Sala> salas) {
        this.id = id;
        this.nome = nome;
        this.bloco = bloco;
        this.salas = salas;
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

    @JsonIgnore
    public Bloco getBloco() {
        return bloco;
    }

    public void setBloco(Bloco bloco) {
        this.bloco = bloco;
    }

    @JsonIgnore
    public List<Sala> getSalas() {
        return salas;
    }

    public void setSalas(List<Sala> salas) {
        this.salas = salas;
    }

    @JsonProperty("bloco")
    private void unpackBloco(Map<String,Object> bloco) throws ObjectRequestException {
        String id = (String) bloco.get("id");
        if (id.equals("") || id == null) throw new ObjectRequestException("Bloco não informado.");
        this.setBloco(new Bloco());
        this.getBloco().setId(UUID.fromString(id));
    }
}
