package usp.icmc.ssc.lasdpc.management.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import usp.icmc.ssc.lasdpc.management.dtos.InstituicaoDTO;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@JsonSerialize
@Table(name = "instituicoes")
public class Instituicao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cnpj")
    private String cnpj;

    @OneToMany(mappedBy="instituicao")
    private List<Bloco> blocos;

    public Instituicao() {}

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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public List<Bloco> getBlocos() {
        return blocos;
    }

    public void setBlocos(List<Bloco> blocos) {
        this.blocos = blocos;
    }

    public static Instituicao from(InstituicaoDTO instituicaoDTO) {
        Instituicao instituicao = new Instituicao();
        instituicao.setId(instituicaoDTO.getId());
        instituicao.setCnpj(instituicaoDTO.getCnpj());
        instituicao.setNome(instituicaoDTO.getNome());
        return instituicao;
    }
}
