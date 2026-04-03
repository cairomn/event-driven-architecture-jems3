package usp.icmc.ssc.lasdpc.kafkamqttbridge;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import usp.icmc.ssc.lasdpc.kafkamqttbridge.dtos.ActionMqttDTO;

@Component
public class KafkaMessageListener {

    @Value("${mqtt.broker.address}")
    private String address;

    @Value("${mqtt.clientId}")
    private String clientId;

    @KafkaListener(topics = "dispositivo-acao", groupId = "icmc", containerFactory = "actionKafkaListenerFactory")
    public void getMicrocontroladorSalvo(@Payload ActionMqttDTO actionMqttDTO) {
        String topico = actionMqttDTO.getTopic() + "/"  +  actionMqttDTO.getMicroID().toString();
        final String payload = "{\"val\":" + actionMqttDTO.getValor() + ",\"actID\":\"" + actionMqttDTO.getActuatorID() + "\",\"k6Topic\":\"" + actionMqttDTO.getK6Topic() + "\"}";
        System.out.println(topico + ": " + payload);
        this.publishOnMqtt(topico, payload, 1, false);
    }

    private void publishOnMqtt(final String topic, final String payload, int qos, boolean retained) {
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setPayload(payload.getBytes());
        mqttMessage.setQos(qos);
        mqttMessage.setRetained(retained);
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            IMqttClient mqttClient = new MqttClient(address, clientId, persistence);
            mqttClient.connectWithResult(null);
            mqttClient.publish(topic, mqttMessage);
            System.out.println(topic + ": " + mqttClient);
            mqttClient.disconnect();
        } catch (MqttException e) {

            throw new RuntimeException(e);
        }
    }
}
