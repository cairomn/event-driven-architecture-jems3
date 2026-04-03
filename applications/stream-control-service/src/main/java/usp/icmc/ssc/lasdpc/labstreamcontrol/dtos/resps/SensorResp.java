package usp.icmc.ssc.lasdpc.labstreamcontrol.dtos.resps;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import usp.icmc.ssc.lasdpc.labstreamcontrol.entities.Sensor;

public class SensorResp {

    private String id;

    private UUID sensorID;

    private List<MedicaoResp> medicoes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UUID getSensorID() {
        return sensorID;
    }

    public void setSensorID(UUID sensorID) {
        this.sensorID = sensorID;
    }

    public List<MedicaoResp> getMedicoes() {
        return medicoes;
    }

    public void setMedicoes(List<MedicaoResp> medicoes) {
        this.medicoes = medicoes;
    }

    public void addMedicao(MedicaoResp medicaoResp) {
        if (this.medicoes == null) {
            this.medicoes = new ArrayList<>();
        }

        this.medicoes.add(medicaoResp);
    }

    public static SensorResp from(Sensor sensor) {
        SensorResp sensorResp = new SensorResp();
        sensorResp.setId(sensor.getId());
        sensorResp.setSensorID(sensor.getSensorID());
        return sensorResp;
    }
}
