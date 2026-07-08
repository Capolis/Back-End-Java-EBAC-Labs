package br.com.processing_service.config;

import br.com.processing_service.model.OrderDocument;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<String, OrderDocument> consumerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        
        // Grupo isolado exclusivo para o processamento do Banco de Dados
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "processing-group");
        
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        
        // Ignora o cabeçalho original e força a conversão direto para OrderDocument
        JsonDeserializer<OrderDocument> jsonDeserializer = new JsonDeserializer<>(OrderDocument.class, false);
        jsonDeserializer.addTrustedPackages("*");

        ErrorHandlingDeserializer<OrderDocument> errorHandlingDeserializer = new ErrorHandlingDeserializer<>(jsonDeserializer);

        return new DefaultKafkaConsumerFactory<>(configProps, new StringDeserializer(), errorHandlingDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, OrderDocument> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, OrderDocument> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        
        // Habilita a observação para o Zipkin registrar a entrada no banco de dados!
        factory.getContainerProperties().setObservationEnabled(true);
        
        return factory;
    }
}