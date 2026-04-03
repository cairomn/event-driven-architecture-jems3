package usp.icmc.ssc.lasdpc.management.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import usp.icmc.ssc.lasdpc.management.dtos.TipoSensorDTO;
import usp.icmc.ssc.lasdpc.management.entities.Microcontrolador;
import usp.icmc.ssc.lasdpc.management.entities.Sensor;
import usp.icmc.ssc.lasdpc.management.enums.TipoSensorEnum;
import usp.icmc.ssc.lasdpc.management.exceptions.BusinessException;
import usp.icmc.ssc.lasdpc.management.exceptions.NotFoundRegisterException;
import usp.icmc.ssc.lasdpc.management.repositories.SensorRepository;
import usp.icmc.ssc.lasdpc.management.utils.Constantes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    public Page<Sensor> all() {
        Pageable pageable = PageRequest.of(1, 10);
        return this.sensorRepository.findAll(pageable);
    }

    public Sensor save(Sensor sensor) throws BusinessException {
        if (sensor.getId() == null) {
            sensor.setStatus(Sensor.ST_SENSOR_ATIVO);
        }

        this.validarCampos(sensor);
        return sensorRepository.save(sensor);
    }

    public Sensor find(UUID id) throws NotFoundRegisterException {
        Optional<Sensor> sensor = this.sensorRepository.findById(id);

        if (sensor.isEmpty()) {
            throw new NotFoundRegisterException(Constantes.MSG_NAO_ENCONTRADO);
        }

        return sensor.get();
    }

    public List<Sensor> getByMicrocontrolador(Microcontrolador microcontrolador) {
        return this.sensorRepository.getByMicrocontrolador(microcontrolador);
    }

    public void delete(UUID id) throws NotFoundRegisterException {
        Sensor sensor = this.find(id);
        this.sensorRepository.delete(sensor);
    }

    public ArrayList<TipoSensorDTO> getTiposSensores() {
        ArrayList<TipoSensorDTO> tiposSensoresDTO = new ArrayList<TipoSensorDTO>();
        tiposSensoresDTO.add(TipoSensorDTO.from(TipoSensorEnum.TP_SENSOR_TEMPERATURA));
        tiposSensoresDTO.add(TipoSensorDTO.from(TipoSensorEnum.TP_SENSOR_UMIDADE));
        tiposSensoresDTO.add(TipoSensorDTO.from(TipoSensorEnum.TP_SENSOR_ILUMINACAO));
        tiposSensoresDTO.add(TipoSensorDTO.from(TipoSensorEnum.TP_SENSOR_MOVIMENTO));
        return tiposSensoresDTO;
    }

    private void validarCampos(Sensor sensor) throws BusinessException {
        if (sensor.getApelido().equals("") || sensor.getApelido() == null) {
            throw new BusinessException(Constantes.MSG_SENSOR_CAMPO_OBRIGATORIO_APELIDO);
        }

        if (sensor.getTipoSensor() < 1) {
            throw new BusinessException(Constantes.MSG_SENSOR_CAMPO_OBRIGATORIO_TIPO_ATUADOR);
        }

        if (sensor.getMicrocontrolador() == null) {
            throw new BusinessException(Constantes.MSG_SENSOR_CAMPO_OBRIGATORIO_MICROCONTROLADOR);
        }
    }
}
