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
@Table(name = "blocos")
public class Bloco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @Column(name = "nome")
    private String nome;

    @ManyToOne
    @JoinColumn(name="instituicao_id", nullable=false)
    private Instituicao instituicao;

    @OneToMany(mappedBy = "bloco", fetch = FetchType.LAZY)
    private List<Piso> pisos;

    public Bloco() {}

    public Bloco(UUID id, String nome, Instituicao instituicao, List<Piso> pisos) {
        this.id = id;
        this.nome = nome;
        this.instituicao = instituicao;
        this.pisos = pisos;
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
    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public List<Piso> getPisos() {
        return pisos;
    }

    public void setPisos(List<Piso> pisos) {
        this.pisos = pisos;
    }

    @JsonProperty("instituicao")
    private void unpackInstituicao(Map<String,Object> instituicao) throws ObjectRequestException {
        String id = (String) instituicao.get("id");
        if (id.equals("")) throw new ObjectRequestException("Instituição não informada.");
        this.setInstituicao(new Instituicao());
        this.getInstituicao().setId(UUID.fromString(id));
    }
}
