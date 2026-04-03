package usp.icmc.ssc.lasdpc.labstreamcontrol.configs;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

 @Configuration
public class KafkaTopicConfig {

     @Value("${spring.kafka.bootstrap-servers}")
     private String bootstrapServers;

     @Bean
     public KafkaAdmin kafkaAdmin() {
         Map<String, Object> configs = new HashMap<>();
         configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
         return new KafkaAdmin(configs);
     }

     @Bean
     public NewTopic createTemperatureMeasureTopic() {
         return TopicBuilder
                 .name("temperature-measure")
                 .partitions(4)
                 .replicas(2)
                 .build();
     }

     @Bean
     public NewTopic createHumidityMeasureTopic() {
         return TopicBuilder
                 .name("humidity-measure")
                 .partitions(4)
                 .replicas(2)
                 .build();
     }

     @Bean
     public NewTopic createCO2MeasureTopic() {
         return TopicBuilder
                 .name("co2-measure")
                 .partitions(4)
                 .replicas(2)
                 .build();
     }

     @Bean
     public NewTopic createMovimentMeasureTopic() {
         return TopicBuilder
                 .name("moviment-measure")
                 .partitions(4)
                 .replicas(2)
                 .build();
     }

     @Bean
     public NewTopic createLuminosityMeasureTopic() {
         return TopicBuilder
                 .name("luminosity-measure")
                 .partitions(4)
                 .replicas(2)
                 .build();
     }

     @Bean
     public NewTopic statusCheckTopic() {
         return TopicBuilder
                 .name("status-check")
                 .partitions(4)
                 .replicas(2)
                 .build();
     }

     @Bean
     public NewTopic createLampOnOffTopic() {
         return TopicBuilder
                 .name("lamp-on-off")
                 .partitions(2)
                 .replicas(1)
                 .build();
     }

     @Bean
     public NewTopic createAirConditionerOnOffTopic() {
         return TopicBuilder
                 .name("air-conditioner-on-off")
                 .partitions(2)
                 .replicas(1)
                 .build();
     }

     @Bean
     public NewTopic createAirConditionerChangeTemperatureTopic() {
         return TopicBuilder
                 .name("air-conditioner-change-temperature")
                 .partitions(2)
                 .replicas(1)
                 .build();
     }
}
