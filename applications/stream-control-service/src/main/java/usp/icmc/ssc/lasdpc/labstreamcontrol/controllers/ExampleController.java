package usp.icmc.ssc.lasdpc.labstreamcontrol.controllers;

import java.sql.Timestamp;
import java.util.Random;
import java.util.UUID;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import usp.icmc.ssc.lasdpc.labstreamcontrol.dtos.inners.ActionMqttDTO;
import usp.icmc.ssc.lasdpc.labstreamcontrol.dtos.inners.MedicaoInnerDTO;
import usp.icmc.ssc.lasdpc.labstreamcontrol.enums.TipoSensorEnum;
import usp.icmc.ssc.lasdpc.labstreamcontrol.enums.TipoUnidadeMedidaEnum;

@RestController
@RequestMapping(value = "/api/v1")
public class ExampleController {

    private KafkaTemplate<String, MedicaoInnerDTO> kafkaTemplate;

    private KafkaTemplate<String, ActionMqttDTO> kafkaTemplateAction;

    ExampleController(
        KafkaTemplate<String, MedicaoInnerDTO> kafkaTemplate,
        KafkaTemplate<String, ActionMqttDTO> kafkaTemplateAction) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTemplateAction = kafkaTemplateAction;
    }

    @GetMapping
    public String test() {
        return "Connected!!!";
    }

    @PostMapping("/temperature")
    public String publishTemperatura() {
        MedicaoInnerDTO medicao = new MedicaoInnerDTO();
        medicao.setValor(String.valueOf(new Random().nextDouble()));
        medicao.setSensorID(UUID.fromString("d29d59ea-c615-4ddd-b8d9-8bd28b19a08b"));
        medicao.setMicroID(UUID.fromString("3a5c9f1e-ce81-4dbb-96a3-0063f695afe2"));
        medicao.setTypeSensor(TipoSensorEnum.TP_SENSOR_TEMPERATURA);
        medicao.setUnit(TipoUnidadeMedidaEnum.TP_UNIT_MEASURE_CELSIUS);
        this.kafkaTemplate.send("smartlab-temperature", medicao);
        return "Published!";
    }

    @PostMapping("/acao")
    public String publishAction() {
        System.out.println("Hora Publicação: " + new Timestamp(System.currentTimeMillis()));
        ActionMqttDTO actionMqttDTO = new ActionMqttDTO();
        actionMqttDTO.setId(UUID.fromString("3a5c9f1e-ce81-4dbb-96a3-0063f695afe2"));
        actionMqttDTO.setMicroID(UUID.fromString("3a5c9f1e-ce81-4dbb-96a3-0063f695afe2"));
        actionMqttDTO.setTopic("lasdpc/lamp/on-off");
        actionMqttDTO.setValor(1.0);
        this.kafkaTemplateAction.send("dispositivo-acao", actionMqttDTO);
        return "Published!";
    }

}
