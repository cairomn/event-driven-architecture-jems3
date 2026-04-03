package usp.icmc.ssc.lasdpc.management.configs;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import usp.icmc.ssc.lasdpc.management.utils.Constantes;

 @Configuration
public class MicrocontroladorTopics {

     @Bean
     public NewTopic publicarProdutoSalvoTopic() {
         return TopicBuilder.name(Constantes.PUBLISH_MICROCONTROLADOR_SALVO_TOPIC)
                 .partitions(2)
                 .replicas(1)
                 .compact()
                 .build();
     }

     @Bean
     public NewTopic publicarAcaoDispositivoTopic() {
         return TopicBuilder.name(Constantes.PUBLISH_ON_OFF_AIR_COND_ACTION_TOPIC)
                 .partitions(2)
                 .replicas(1)
                 .compact()
                 .build();
     }

}
