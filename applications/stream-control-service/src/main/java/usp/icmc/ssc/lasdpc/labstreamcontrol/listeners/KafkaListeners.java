package usp.icmc.ssc.lasdpc.labstreamcontrol.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import usp.icmc.ssc.lasdpc.labstreamcontrol.dtos.inners.*;
import usp.icmc.ssc.lasdpc.labstreamcontrol.entities.Atuador;
import usp.icmc.ssc.lasdpc.labstreamcontrol.entities.Medicao;
import usp.icmc.ssc.lasdpc.labstreamcontrol.entities.Microcontrolador;
import usp.icmc.ssc.lasdpc.labstreamcontrol.entities.Sensor;
import usp.icmc.ssc.lasdpc.labstreamcontrol.entities.StatusCheck;
import usp.icmc.ssc.lasdpc.labstreamcontrol.services.*;

import java.util.Objects;

@Component
public class KafkaListeners {

    @Autowired
    private MicrocontroladorService microcontroladorService;

    @Autowired
    private SensorService sensorService;

    @Autowired
    private AtuadorService atuadorService;

    @Autowired
    private MedicaoService medicaoService;

    @Autowired
    private MedicaoErroService medicaoErroService;

    @Autowired
    private StatusCheckService statusCheckService;

    @KafkaListener(topics = "microcontrolador-salvo", groupId = "icmc", containerFactory = "microcontroladorKafkaListenerFactory")
    public void getMicrocontroladorSalvo(@Payload MicrocontroladorInnerDTO microcontroladorDTO) {
        Microcontrolador microcontroladorSalvo = this.microcontroladorService
                .getPorMicrocontroladorID(microcontroladorDTO.getId());
        Microcontrolador microcontrolador = Microcontrolador.from(microcontroladorDTO);

        if (microcontroladorSalvo != null) {
            microcontrolador.setId(microcontroladorSalvo.getId());
        }

        microcontroladorSalvo = this.microcontroladorService.save(microcontrolador);

        for (SensorInnerDTO sensorItem : microcontroladorDTO.getSensores()) {
            Sensor sensorSalvo = sensorService.getPorSensorID(sensorItem.getId());
            Sensor sensor = Sensor.from(sensorItem, microcontroladorSalvo);

            if (sensorSalvo != null) {
                sensor.setId(sensorSalvo.getId());
            }

            sensorService.salvar(sensor);
        }

        for (AtuadorInnerDTO atuadorItem : microcontroladorDTO.getAtuadores()) {
            Atuador atuadorSalvo = atuadorService.getPorAtuadorID(atuadorItem.getId());
            Atuador atuador = Atuador.from(atuadorItem, microcontroladorSalvo);

            if (atuadorSalvo != null) {
                atuador.setId(atuadorSalvo.getId());
            }

            atuadorService.salvar(atuador);
        }
    }

    @KafkaListener(topics = "smartlab-temperature", groupId = "icmc", concurrency = "${listen.concurrency:2}", containerFactory = "medicaoKafkaListenerFactory")
    public void getTemperatureMeasure(MedicaoInnerDTO medicaoDTO) throws IllegalAccessException {
        Microcontrolador microcontrolador = this.microcontroladorService
                .getPorMicrocontroladorID(medicaoDTO.getMicroID());

        if (microcontrolador == null) {
            this.medicaoErroService.salvar("Microcontrolador não cadastrado: " + medicaoDTO.getMicroID().toString()
                    + ". Listener: temperature-measure.");
            return;
        }

        if (!microcontrolador.isAtivo()) {
            this.medicaoErroService.salvar("Microcontrolador inativo: " + medicaoDTO.getMicroID().toString()
                    + ". Listener: temperature-measure.");
            return;
        }

        if (medicaoDTO.getSensorID() != null) {
            System.out.println("Medição: " + medicaoDTO);
            Sensor sensor = this.sensorService.getPorSensorID(medicaoDTO.getSensorID());
            Medicao medicao = new Medicao(medicaoDTO.getValor(), sensor);
            this.medicaoService.salvar(medicao);
        } else {
            throw new IllegalAccessException();
        }
    }

    @KafkaListener(topics = "smartlab-humidity", groupId = "icmc", concurrency = "${listen.concurrency:2}", containerFactory = "medicaoKafkaListenerFactory")
    public void getHumidityMeasure(MedicaoInnerDTO medicaoDTO) throws IllegalAccessException {
        Microcontrolador microcontrolador = this.microcontroladorService
                .getPorMicrocontroladorID(medicaoDTO.getMicroID());

        if (microcontrolador == null) {
            this.medicaoErroService.salvar("Microcontrolador não cadastrado: " + medicaoDTO.getMicroID().toString()
                    + ". Listener: humidity-measure.");
            return;
        }

        if (!microcontrolador.isAtivo()) {
            this.medicaoErroService.salvar("Microcontrolador inativo: " + medicaoDTO.getMicroID().toString()
                    + ". Listener: humidity-measure.");
            return;
        }

        if (medicaoDTO.getSensorID() != null) {
            Sensor sensor = this.sensorService.getPorSensorID(medicaoDTO.getSensorID());
            Medicao medicao = new Medicao(medicaoDTO.getValor(), sensor);
            this.medicaoService.salvar(medicao);
        } else {
            throw new IllegalAccessException();
        }
    }

    @KafkaListener(topics = "smartlab-co2", groupId = "icmc", concurrency = "${listen.concurrency:2}", containerFactory = "medicaoKafkaListenerFactory")
    public void getCO2Measure(MedicaoInnerDTO medicaoDTO) throws IllegalAccessException {
        Microcontrolador microcontrolador = this.microcontroladorService
                .getPorMicrocontroladorID(medicaoDTO.getMicroID());

        if (microcontrolador == null) {
            this.medicaoErroService.salvar("Microcontrolador não cadastrado: " + medicaoDTO.getMicroID().toString()
                    + ". Listener: co2-measure.");
            return;
        }

        if (!microcontrolador.isAtivo()) {
            this.medicaoErroService.salvar("Microcontrolador inativo: " + medicaoDTO.getMicroID().toString()
                    + ". Listener: co2-measure.");
            return;
        }

        if (medicaoDTO.getSensorID() != null) {
            Sensor sensor = this.sensorService.getPorSensorID(medicaoDTO.getSensorID());
            Medicao medicao = new Medicao(medicaoDTO.getValor(), sensor);
            this.medicaoService.salvar(medicao);
        } else {
            throw new IllegalAccessException();
        }
    }

    @KafkaListener(topics = "smartlab-movement", groupId = "icmc", concurrency = "${listen.concurrency:2}", containerFactory = "medicaoKafkaListenerFactory")
    public void getMovimentMeasure(MedicaoInnerDTO medicaoDTO) throws IllegalAccessException {
        Microcontrolador microcontrolador = this.microcontroladorService
                .getPorMicrocontroladorID(medicaoDTO.getMicroID());

        if (microcontrolador == null) {
            this.medicaoErroService.salvar("Microcontrolador não cadastrado: " + medicaoDTO.getMicroID().toString()
                    + ". Listener: movement-measure.");
            return;
        }

        if (!microcontrolador.isAtivo()) {
            this.medicaoErroService.salvar("Microcontrolador inativo: " + medicaoDTO.getMicroID().toString()
                    + ". Listener: movement-measure.");
            return;
        }

        if (medicaoDTO.getSensorID() != null) {
            Sensor sensor = this.sensorService.getPorSensorID(medicaoDTO.getSensorID());
            Medicao medicao = new Medicao(medicaoDTO.getValor(), sensor);
            this.medicaoService.salvar(medicao);
        } else {
            throw new IllegalAccessException();
        }
    }

    @KafkaListener(topics = "smartlab-luminosity", groupId = "icmc", concurrency = "${listen.concurrency:2}", containerFactory = "medicaoKafkaListenerFactory")
    public void getLuminosityMeasure(MedicaoInnerDTO medicaoDTO) throws IllegalAccessException {
        Microcontrolador microcontrolador = this.microcontroladorService
                .getPorMicrocontroladorID(medicaoDTO.getMicroID());

        if (microcontrolador == null) {
            this.medicaoErroService.salvar("Microcontrolador não cadastrado: " + medicaoDTO.getMicroID().toString()
                    + ". Listener: luminosity-measure.");
            return;
        }

        if (!microcontrolador.isAtivo()) {
            this.medicaoErroService.salvar("Microcontrolador inativo: " + medicaoDTO.getMicroID().toString()
                    + ". Listener: luminosity-measure.");
            return;
        }

        if (medicaoDTO.getSensorID() != null) {
            Sensor sensor = this.sensorService.getPorSensorID(medicaoDTO.getSensorID());
            Medicao medicao = new Medicao(medicaoDTO.getValor(), sensor);
            this.medicaoService.salvar(medicao);
        } else {
            throw new IllegalAccessException();
        }
    }

    @KafkaListener(topics = "status-check", groupId = "icmc", concurrency = "${listen.concurrency:2}", containerFactory = "statusCheckKafkaListenerFactory")
    public void getStatusCheck(StatusCheckInnerDTO statusCheckDTO) {
        if (Objects.isNull(statusCheckDTO.getAtuador()) && Objects.isNull(statusCheckDTO.getSensor())) {
            this.medicaoErroService.salvar("Atuador ou Sensor não informado. Listener: status-check.");
        }

        StatusCheck statusCheck = StatusCheck.from(statusCheckDTO);
        statusCheck.setSensor(sensorService.getPorSensorID(statusCheckDTO.getSensor()));
        statusCheck.setAtuador(atuadorService.getPorAtuadorID(statusCheckDTO.getAtuador()));

        if (Objects.isNull(statusCheck.getAtuador()) && Objects.isNull(statusCheck.getSensor())) {
            this.medicaoErroService.salvar("Atuador e Sensor não encontrado. Listener: status-check.");
        }

        if (!Objects.isNull(statusCheck.getAtuador()) && Objects.isNull(statusCheck.getAtuador().getId())) {
            this.medicaoErroService.salvar("Atuador não encontrado. Listener: status-check.");
        }

        if (!Objects.isNull(statusCheck.getSensor()) && Objects.isNull(statusCheck.getSensor().getId())) {
            this.medicaoErroService.salvar("Sensor não encontrado. Listener: status-check.");
        }

        this.statusCheckService.salvar(statusCheck);
    }
}
