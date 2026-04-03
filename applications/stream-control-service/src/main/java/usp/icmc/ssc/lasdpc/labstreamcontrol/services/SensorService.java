package usp.icmc.ssc.lasdpc.labstreamcontrol.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import usp.icmc.ssc.lasdpc.labstreamcontrol.entities.Medicao;
import usp.icmc.ssc.lasdpc.labstreamcontrol.entities.Microcontrolador;
import usp.icmc.ssc.lasdpc.labstreamcontrol.entities.Sensor;
import usp.icmc.ssc.lasdpc.labstreamcontrol.repositories.SensorRepository;

@Service
public class SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private MedicaoService medicaoService;

    public Sensor salvar(Sensor sensor) {
        return this.sensorRepository.save(sensor);
    }

    public Sensor getPorId(String id) throws IllegalAccessException {
        Optional<Sensor> sensor = this.sensorRepository.findById(id);

        if (sensor.isEmpty()) {
            throw new IllegalAccessException();
        }

        return sensor.get();
    }

    public Sensor getPorSensorID(UUID sensorID) {
        return this.sensorRepository.getBySensorID(sensorID);
    }

    public List<Sensor> getSensoresPorMicrocontrolador(Microcontrolador microcontrolador) {
        return this.sensorRepository.getByMicrocontrolador(microcontrolador);
    }

    public Page<Medicao> getMediacoes(UUID sensorID, int i, int b) {
        return this.medicaoService.getPorSensor(this.sensorRepository.getBySensorID(sensorID), i, b);
    }
}
