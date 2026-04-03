package usp.icmc.ssc.lasdpc.management.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import usp.icmc.ssc.lasdpc.management.dtos.ActionDTO;
import usp.icmc.ssc.lasdpc.management.entities.Atuador;
import usp.icmc.ssc.lasdpc.management.entities.Microcontrolador;
import usp.icmc.ssc.lasdpc.management.entities.Sala;
import usp.icmc.ssc.lasdpc.management.entities.Sensor;
import usp.icmc.ssc.lasdpc.management.exceptions.BusinessException;
import usp.icmc.ssc.lasdpc.management.exceptions.NotFoundRegisterException;
import usp.icmc.ssc.lasdpc.management.repositories.MicrocontroladorRepository;
import usp.icmc.ssc.lasdpc.management.utils.Constantes;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MicrocontroladorService {

    @Autowired
    private MicrocontroladorRepository microcontroladorRepository;

    @Autowired
    private AtuadorService atuadorService;

    @Autowired
    private SensorService sensorService;

    @Autowired
    private KafkaTemplate<String, Object> template;

    public List<Microcontrolador> all() {
        return this.microcontroladorRepository.findAll();
    }

    @Transactional
    public Microcontrolador save(Microcontrolador microcontrolador)
            throws BusinessException, NotFoundRegisterException {
        this.validarCamposMicrocontrolador(microcontrolador);
        boolean isNovoRegistro = microcontrolador.getId() == null;
        Microcontrolador save = this.microcontroladorRepository.save(microcontrolador);

        for (Atuador atuador : microcontrolador.getAtuadores()) {
            atuador.setMicrocontrolador(new Microcontrolador());
            atuador.getMicrocontrolador().setId(save.getId());
            atuadorService.save(atuador);
        }

        for (Sensor sensor : microcontrolador.getSensores()) {
            sensor.setMicrocontrolador(new Microcontrolador());
            sensor.getMicrocontrolador().setId(save.getId());
            sensorService.save(sensor);
        }

        if (!isNovoRegistro) {
            List<Atuador> atuadorList = this.atuadorService.getByMicrocontrolador(microcontrolador);
            List<Sensor> sensorList = this.sensorService.getByMicrocontrolador(microcontrolador);

            int countAtuador;
            for (Atuador atuador : atuadorList) {
                countAtuador = 0;
                for (Atuador atuadorInserido : microcontrolador.getAtuadores()) {
                    if (atuador.getId() == atuadorInserido.getId())
                        countAtuador++;
                }

                if (countAtuador == 0 && atuador.getId() != null)
                    this.atuadorService.delete(atuador.getId());
            }

            int countSensor;
            for (Sensor sensor : sensorList) {
                countSensor = 0;
                for (Sensor sensorInserido : microcontrolador.getSensores()) {
                    if (sensor.getId() == sensorInserido.getId())
                        countSensor++;
                }

                if (countSensor == 0 && sensor.getId() != null)
                    this.sensorService.delete(sensor.getId());
            }
        }

        save.setAtuadores(this.atuadorService.getByMicrocontrolador(microcontrolador));
        save.setSensores(this.sensorService.getByMicrocontrolador(microcontrolador));

        this.publishMicrocontrolador(save);
        return save;
    }

    public Microcontrolador find(UUID id) throws NotFoundRegisterException {
        Optional<Microcontrolador> microcontrolador = this.microcontroladorRepository.findById(id);

        if (microcontrolador.isEmpty()) {
            throw new NotFoundRegisterException(Constantes.MSG_NAO_ENCONTRADO);
        }

        return microcontrolador.get();
    }

    public List<Microcontrolador> findBySala(Sala sala) {
        return this.microcontroladorRepository.findBySala(sala);
    }

    public Page<Microcontrolador> getPorSalaPaginada(Sala sala, int pagNum, int pagSize) {
        return this.microcontroladorRepository.findBySalaAndRemoved(sala, false,
                PageRequest.of(pagNum, pagSize, Sort.by("apelido").ascending()));
    }

    public void delete(UUID id) throws NotFoundRegisterException {
        Microcontrolador microcontrolador = this.find(id);
        microcontrolador.setRemoved(true);
        this.microcontroladorRepository.save(microcontrolador);
    }

    public List<Sensor> getSensores(UUID id) {
        Microcontrolador microcontrolador = new Microcontrolador();
        microcontrolador.setId(id);
        return this.sensorService.getByMicrocontrolador(microcontrolador);
    }

    public List<Atuador> getAtuadores(UUID id) {
        Microcontrolador microcontrolador = new Microcontrolador();
        microcontrolador.setId(id);
        return this.atuadorService.getByMicrocontrolador(microcontrolador);
    }

    public void changeStatusAirCond(ActionDTO action) {
        action.setTopic("lasdpc/air-conditioner/on-off");
        this.publishAction(action, Constantes.PUBLISH_ON_OFF_AIR_COND_ACTION_TOPIC);
    }

    public void changeTempAirCond(ActionDTO action) throws BusinessException {
        if (action.getValor() < 16 || action.getValor() > 32) {
            throw new BusinessException("Temperatura fora do alcance do ar condicionado.");
        }

        action.setTopic("lasdpc/air-cond/change-temp");
        this.publishAction(action, Constantes.PUBLISH_CHANGE_TEMP_AIR_COND_ACTION_TOPIC);
    }

    public void changeModeAirCond(ActionDTO action) throws BusinessException {
        if (action.getValor() < 0 || action.getValor() > 4) {
            throw new BusinessException("Modulo fora do alcance do ar condicionado.");
        }

        action.setTopic("lasdpc/air-cond/change-mode");
        this.publishAction(action, Constantes.PUBLISH_CHANGE_MODE_AIR_COND_ACTION_TOPIC);
    }

    private void validarCamposMicrocontrolador(Microcontrolador microcontrolador) throws BusinessException {
        if (microcontrolador.getSensores().size() == 0 && microcontrolador.getAtuadores().size() == 0) {
            throw new BusinessException(Constantes.MSG_MICROCONTROLADOR_INFORME_ATUADOR_OU_SENSOR);
        }
    }

    private void publishMicrocontrolador(Microcontrolador microcontrolador) {
        ListenableFuture<SendResult<String, Object>> future = this.template.send(
            Constantes.PUBLISH_MICROCONTROLADOR_SALVO_TOPIC,
            microcontrolador.getId().toString(),
            microcontrolador
        );

        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onSuccess(SendResult<String, Object> result) {
                System.out.println("Publicado com sucesso!");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        });
    }

    private void publishAction(ActionDTO action, String topic) {
        ListenableFuture<SendResult<String, Object>> future = this.template.send(
            topic,
            action.getActuatorID().toString(),
            action
        );

        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onSuccess(SendResult<String, Object> result) {
                System.out.println(topic + ": " + action.getActuatorID().toString());
            }

            @Override
            public void onFailure(Throwable ex) {
                ex.printStackTrace();
                throw new RuntimeException(ex);
            }
        });
    }
}
