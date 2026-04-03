package usp.icmc.ssc.lasdpc.labstreamcontrol.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongodb.lang.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;
import usp.icmc.ssc.lasdpc.labstreamcontrol.dtos.inners.StatusCheckInnerDTO;

import java.util.Date;

@Document(collection = "status-check")
public class StatusCheck {

    @Id
    private String id;

    @Field("on")
    private Boolean on;

    @Field("temp")
    private Integer temp;

    @Field("mode")
    private Integer mode;

    @DBRef
    @Nullable
    private Atuador atuador;

    @DBRef
    @Nullable
    private Sensor sensor;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonProperty("dataCadastro")
    private Date dataCadastro;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getOn() {
        return on;
    }

    public void setOn(Boolean on) {
        this.on = on;
    }

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

    public Date getDataCadastro() {
        return this.dataCadastro;
    }

    private void setDataCadastro(Date date) {
        this.dataCadastro = date;
    }

    public Atuador getAtuador() {
        return atuador;
    }

    public void setAtuador(Atuador atuador) {
        this.atuador = atuador;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public static StatusCheck from(StatusCheckInnerDTO statusCheckInnerDTO) {
        StatusCheck statusCheck = new StatusCheck();
        statusCheck.setOn(statusCheckInnerDTO.getOn());
        statusCheck.setMode(statusCheckInnerDTO.getMode());
        statusCheck.setTemp(statusCheckInnerDTO.getTemp());
        statusCheck.setDataCadastro(new Date());
        return statusCheck;
    }
}
