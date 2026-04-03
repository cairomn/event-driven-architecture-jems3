package usp.icmc.ssc.lasdpc.labstreamcontrol.entities;

import java.util.Date;

import com.mongodb.lang.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "medicoes")
public class Medicao {

    @Id
    @JsonProperty("id")
    private String id;

    @JsonProperty("valor")
    private String valor;

    @DBRef
    @Nullable
    private Atuador atuador;

    @DBRef
    @Nullable
    private Sensor sensor;

//    @JsonProperty("dataPublicacao")
//    private Date dataPublicacao;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonProperty("dataCadastro")
    private Date dataCadastro;

    public Medicao() {}

    public Medicao(String valor, Atuador atuador) {
        this.setValor(valor);
        this.setAtuador(atuador);

        if (this.getId() == null) {
            this.setDataCadastro(new Date());
        }
    }

    public Medicao(String valor, Sensor sensor) {
        this.setValor(valor);
        this.setSensor(sensor);

        if (this.getId() == null) {
            this.setDataCadastro(new Date());
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValor() {
        return this.valor;
    }

    private void setValor(String valor) {
        this.valor = valor;
    }

//    public Date getDataPublicacao() {
//        return this.dataPublicacao;
//    }
//
//    private void setDataPublicacao(Date date) {
//        this.dataPublicacao = date;
//    }

    public Date getDataCadastro() {
        return this.dataCadastro;
    }

    private void setDataCadastro(Date date) {
        this.dataCadastro = date;
    }

    public Atuador getAtuador() {
        return atuador;
    }

    private void setAtuador(Atuador atuador) {
        this.atuador = atuador;
    }

    public Sensor getSensor() {
        return sensor;
    }

    private void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public String toString() {
        return "{ \"id\":" + this.getId() + ", \"valor\": " + this.getValor() + " }";
    }
}
