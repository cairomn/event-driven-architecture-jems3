package usp.icmc.ssc.lasdpc.mqttkafkabridge;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.util.concurrent.ListenableFuture;
import usp.icmc.ssc.lasdpc.mqttkafkabridge.dtos.MeasureKafkaDTO;
import usp.icmc.ssc.lasdpc.mqttkafkabridge.dtos.StatusCheckDTO;

import java.sql.Timestamp;
import java.util.concurrent.ExecutionException;

public class MqttMessageHandler implements MessageHandler {
 
    @Autowired
    private KafkaTemplate<String, MeasureKafkaDTO> template;

    @Autowired
    private KafkaTemplate<String, StatusCheckDTO> templateStatusCheck;

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        try {
            String payload = message.getPayload().toString();
            String kafkaTopic = this.getKakfaTopic(message.getHeaders().get("mqtt_receivedTopic", String.class));
            Long timestampMQTT = message.getHeaders().get("timestamp", Long.class);
            JsonObject payloadString = JsonParser.parseString(payload).getAsJsonObject();
            JsonObject jsonObject = JsonParser.parseString(payloadString.get("payload").getAsString()).getAsJsonObject();

            if (kafkaTopic != "status-check") {
                // Creation of object MeasureKafkaDTO
                MeasureKafkaDTO measure = MeasureKafkaDTO.from(jsonObject);
                System.out.println("Message: " + measure);

                // Send to Kakfa topic.
                ListenableFuture future = template.send(kafkaTopic, measure);
                future.get();
            }

            if (kafkaTopic == "status-check") {
                StatusCheckDTO statusCheckDTO = StatusCheckDTO.from(jsonObject);
                System.out.println("Status Check - Message: " + statusCheckDTO);
                ListenableFuture future = templateStatusCheck.send(kafkaTopic, statusCheckDTO);
                future.get();
            }

        } catch (InterruptedException e) {
            System.out.println("Erro Interrupted Exception: " + e.getStackTrace());
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println("Erro Execution Exception: " + e.getStackTrace());
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println("Erro Illegal Exception: " + e.getStackTrace());
            // Error throwed when I post invalid UUID.
            e.printStackTrace();
        }
    }

    private String getKakfaTopic(String mqttTopic) {
        String kafkaTopic = "teste";
        if (mqttTopic.equals("lasdpc/temperature/measure")) kafkaTopic = "smartlab-temperature";
        if (mqttTopic.equals("lasdpc/humidity/measure")) kafkaTopic = "smartlab-humidity";
        if (mqttTopic.equals("lasdpc/co2/measure")) kafkaTopic = "smartlab-co2";
        if (mqttTopic.equals("lasdpc/movement/measure")) kafkaTopic = "smartlab-movement";
        if (mqttTopic.equals("lasdpc/luminosity/measure")) kafkaTopic = "smartlab-luminosity";
        if (mqttTopic.equals("lasdpc/status/check")) kafkaTopic = "status-check";
        return kafkaTopic;
    }
}
