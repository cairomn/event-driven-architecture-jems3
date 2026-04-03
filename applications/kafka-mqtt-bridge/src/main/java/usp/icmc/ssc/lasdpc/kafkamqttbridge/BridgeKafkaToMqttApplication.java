package usp.icmc.ssc.lasdpc.kafkamqttbridge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@EnableAutoConfiguration
@Component
@ComponentScan
public class BridgeKafkaToMqttApplication {

	public static void main(String[] args) {
		SpringApplication.run(BridgeKafkaToMqttApplication.class, args);
	}

}
