package usp.icmc.ssc.lasdpc.mqttkafkabridge.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.integration.transformer.HeaderEnricher;
import org.springframework.integration.transformer.support.ExpressionEvaluatingHeaderValueMessageProcessor;
import org.springframework.integration.transformer.support.HeaderValueMessageProcessor;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.StringUtils;
import usp.icmc.ssc.lasdpc.mqttkafkabridge.MqttMessageHandler;
import usp.icmc.ssc.lasdpc.mqttkafkabridge.dtos.MeasureKafkaDTO;
import usp.icmc.ssc.lasdpc.mqttkafkabridge.dtos.StatusCheckDTO;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class AppConfig {
    
    @Value("${kafka.broker.address}")
    private String brokerAddress;

    @Value("${kafka.retries}")
    private Integer kafkaRetries;

    @Value("${kafka.batch.size}")
    private Integer kafkaBatchSize;

    @Value("${kafka.linger.ms}")
    private Integer kafkaLingerMs;

    @Value("${kafka.buffer.memory}")
    private Integer kafkaBufferMemory;

    @Value("${kafka.key.serializer}")
    private Class kafkaKeySerializer;

    @Value("${kafka.value.serializer}")
    private Class kafkaValueSerializer;

    @Value("${mqtt.broker.address}")
    private String mqttBrokerAddress;

    @Value("${mqtt.topic}")
    private String[] mqttTopic;

    @Value("${mqtt.clientId}")
    private String mqttClientId;

    @Value("${mqtt.qos}")
    private Integer mqttQos;

    @Value("${mqtt.completionTimeout}")
    private Integer mqttCompletionTimeout;

    @Value("${mqtt.username}")
    private String mqttUsername;

    @Value("${mqtt.password}")
    private String mqttPassword;

    /**
     * MQTT Input Channel
     */
    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    /**
     * MQTT Message Enrich Channel
     */
    @Bean MessageChannel mqttMessageEnrichChannel() {
        return new DirectChannel();
    }

    /**
     * Message Producer which connects to MQTT server and consumes the configured mqttTopics
     * And will place it in mqttMessageEnrichChannel
     */
    @Bean
    public MessageProducer inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(mqttBrokerAddress, mqttClientId,
                        mqttPahoClientFactory(), mqttTopic);
        adapter.setCompletionTimeout(mqttCompletionTimeout);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(mqttQos);
        adapter.setOutputChannel(mqttMessageEnrichChannel());

        return adapter;
    }

    /**
     * MQTT Client Factory for connecting with MQTT Server with Authentication
     */
    @Bean
    public MqttPahoClientFactory mqttPahoClientFactory() {
        DefaultMqttPahoClientFactory mqttPahoClientFactory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();

        if (StringUtils.hasText(mqttUsername)) {
            options.setUserName(mqttUsername);
        }

        if (StringUtils.hasText(mqttPassword)) {
            options.setPassword(mqttPassword.toCharArray());
        }

        mqttPahoClientFactory.setConnectionOptions(options);
        return mqttPahoClientFactory;
    }

    /**
     * This is a Spring Integration Transformer which takes in mqttMessageEnrichChannel coming directly from MQTT Server
     * and enriches the header kafka_topic from mqtt_topic while replacing any '/' with '.' and finally sinks it to mqttInputChannel
     */
    @Bean
    @Transformer(inputChannel="mqttMessageEnrichChannel", outputChannel="mqttInputChannel")
    public HeaderEnricher enrichHeaders() {
        Map<String, HeaderValueMessageProcessor<?>> headersToAdd = new HashMap<>();
//        Expression expression = new SpelExpressionParser().parseExpression("headers."+ MqttHeaders.TOPIC +".toString().replaceAll(\'/\', \'.\')");
//        headersToAdd.put(KafkaHeaders.TOPIC,
//                new ExpressionEvaluatingHeaderValueMessageProcessor<String>(expression, String.class));
        HeaderEnricher enricher = new HeaderEnricher(headersToAdd);
        return enricher;
    }

    /**
     * This is a Spring Integration Transformer which takes in the Header Enriched Messages from mqttInputChannel and
     * Transforms it to a JSON with payload and timestamp properties
     */
    @Transformer(inputChannel = "mqttInputChannel", outputChannel = "mqttTransformedOutputChannel")
    public Message<String> enrichMessage(Message<String> message) throws Exception {
        Map<String, String> jsonMap = new LinkedHashMap<String, String>();
        jsonMap.put("payload", message.getPayload());
        jsonMap.put("timestamp", String.valueOf(new Date().getTime()));
        return MessageBuilder.createMessage(objectMapper().writeValueAsString(jsonMap), message.getHeaders());
    }

    /**
     * This is a Spring Service Activator which will handle the messages available in mqttTransformedOutputChannel with MqttMessageHandler
     */
    @Bean
    @ServiceActivator(inputChannel = "mqttTransformedOutputChannel")
    public MessageHandler handler() {
        return new MqttMessageHandler();
    }

    /**
     * Kafka Template for Kafka Operations
     */
    @Bean
    public KafkaTemplate<String, MeasureKafkaDTO> kafkaTemplate() {
        return new KafkaTemplate<String, MeasureKafkaDTO>(producerFactory());
    }

    /**
     * Kafka Producer Factory for Kafka Server connection
     */
    @Bean
    public ProducerFactory<String, MeasureKafkaDTO> producerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.brokerAddress);
        props.put(ProducerConfig.RETRIES_CONFIG, this.kafkaRetries);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, this.kafkaBatchSize);
        props.put(ProducerConfig.LINGER_MS_CONFIG, this.kafkaLingerMs);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, this.kafkaBufferMemory);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, this.kafkaKeySerializer);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, this.kafkaValueSerializer);
        return new DefaultKafkaProducerFactory<>(props);
    }

    /**
     * Kafka Template for Kafka Operations
     */
    @Bean
    public KafkaTemplate<String, StatusCheckDTO> statusCheckKafkaTemplate() {
        return new KafkaTemplate<String, StatusCheckDTO>(statusCheckProducerFactory());
    }

    /**
     * Kafka Producer Factory for Kafka Server connection
     */
    @Bean
    public ProducerFactory<String, StatusCheckDTO> statusCheckProducerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.brokerAddress);
        props.put(ProducerConfig.RETRIES_CONFIG, this.kafkaRetries);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, this.kafkaBatchSize);
        props.put(ProducerConfig.LINGER_MS_CONFIG, this.kafkaLingerMs);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, this.kafkaBufferMemory);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, this.kafkaKeySerializer);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, this.kafkaValueSerializer);
        return new DefaultKafkaProducerFactory<>(props);
    }

    /**
     * JSON Object Mapper
     */
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
