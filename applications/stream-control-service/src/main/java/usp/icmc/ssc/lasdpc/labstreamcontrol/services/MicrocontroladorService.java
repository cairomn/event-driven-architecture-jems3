package usp.icmc.ssc.lasdpc.labstreamcontrol.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import usp.icmc.ssc.lasdpc.labstreamcontrol.dtos.resps.AtuadorResp;
import usp.icmc.ssc.lasdpc.labstreamcontrol.dtos.resps.MedicaoResp;
import usp.icmc.ssc.lasdpc.labstreamcontrol.dtos.resps.MicrocontroladorResp;
import usp.icmc.ssc.lasdpc.labstreamcontrol.dtos.resps.SensorResp;
import usp.icmc.ssc.lasdpc.labstreamcontrol.entities.Atuador;
import usp.icmc.ssc.lasdpc.labstreamcontrol.entities.Medicao;
import usp.icmc.ssc.lasdpc.labstreamcontrol.entities.Microcontrolador;
import usp.icmc.ssc.lasdpc.labstreamcontrol.entities.Sensor;
import usp.icmc.ssc.lasdpc.labstreamcontrol.repositories.MicrocontroladoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MicrocontroladorService {

    @Autowired
    private MicrocontroladoRepository microcontroladorRepository;

    @Autowired
    private SensorService sensorService;

    @Autowired
    private AtuadorService atuadorService;

    @Autowired
    private MedicaoService medicaoService;

    public Microcontrolador register(MicrocontroladorResp microcontroladorResp) {
        Microcontrolador microcontrolador = this.microcontroladorRepository.save(new Microcontrolador(microcontroladorResp.getMicrocontroladorID()));

        if (microcontroladorResp.getAtuadores() != null) {
            for (AtuadorResp atuadorResp: microcontroladorResp.getAtuadores()) {
                this.atuadorService.salvar(new Atuador(atuadorResp.getAtuadorID(), microcontrolador));
            }
        }

        if (microcontroladorResp.getSensores() != null) {
            for (SensorResp sensorResp : microcontroladorResp.getSensores()) {
                this.sensorService.salvar(new Sensor(sensorResp.getSensorID(), microcontrolador));
            }
        }

        return microcontrolador;
    }

    public Microcontrolador save(Microcontrolador microcontrolador) {
        return this.microcontroladorRepository.save(microcontrolador);
    }

    public Microcontrolador getPorId(String id) throws IllegalAccessException {
        Optional<Microcontrolador> microcontrolador = this.microcontroladorRepository.findById(id);
        if (microcontrolador.isEmpty())
            throw new IllegalAccessException();
        return microcontrolador.get();
    }

    public Microcontrolador getPorMicrocontroladorID(UUID microcontroladorID) {
        return this.microcontroladorRepository.getByMicrocontroladorID(microcontroladorID);
    }

    public MicrocontroladorResp getMicrocontroladorComSensoresAtuadores(UUID microcontroladorID) {
        Microcontrolador microcontrolador = this.getPorMicrocontroladorID(microcontroladorID);
        MicrocontroladorResp microcontroladorResp = MicrocontroladorResp.from(microcontrolador);

        List<Atuador> atuadores = this.atuadorService.getAtuadoresPorMicrocontrolador(microcontrolador);
        for (Atuador atuador : atuadores) {
            AtuadorResp atuadorResp = AtuadorResp.from(atuador);
            List<Medicao> medicoes = this.medicaoService.getPorAtuador(atuador);

            for (Medicao medicao: medicoes) {
                atuadorResp.addMedicao(MedicaoResp.from(medicao));
            }

            microcontroladorResp.addAtuador(atuadorResp);
        }

        List<Sensor> sensores = this.sensorService.getSensoresPorMicrocontrolador(microcontrolador);
        for (Sensor sensor: sensores) {
            SensorResp sensorResp = SensorResp.from(sensor);
            List<Medicao> medicoes = this.medicaoService.getPorSensor(sensor);

            for (Medicao medicao: medicoes) {
                sensorResp.addMedicao(MedicaoResp.from(medicao));
            }

            microcontroladorResp.addSensor(sensorResp);
        }

        return microcontroladorResp;
    }
}
