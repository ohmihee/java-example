package mihee.com.config;

import mihee.com.models.testEntity.Greeting;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
    @Value(value="${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;
    private String groupId = "foo";

    @Bean
    public ConsumerFactory<String, String> consumerFactory () {
        Map<String, Object> props = new HashMap<>();
        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress
        );
        props.put(
                ConsumerConfig.GROUP_ID_CONFIG, groupId
        );
        props.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class
        );
        props.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class
        );
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, Greeting> greetingConsumerFactory () {
        Map<String, Object> props = new HashMap<>();
        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress
        );
        props.put(
                ConsumerConfig.GROUP_ID_CONFIG, groupId
        );
        props.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class
        );
        props.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class
        );
        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                new JsonDeserializer<>(Greeting.class)
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Greeting>
    greetingKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Greeting> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(greetingConsumerFactory());
        return factory;
    }

//    @KafkaListener(topics = "baeldung", groupId = "foo")
//    //@KafkaListener(topics = "baeldung1, baendung2", groupId = "foo")
//    public void listenGroupFoo (String message) {
//        System.out.println("Received Message in group foo: " + message);
//    }
//
//    @KafkaListener(topics = "baeldung", groupId = "foo")
//    public void listenWithHeaders (
//            @Payload String message,
//            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition
//    ) {
//        System.out.println(
//                "Received Messasge: "+message+"+ from partition: "+ partition
//        );
//    }

//    @KafkaListener(topicPartitions
//            = @TopicPartition(topic = "topicName", partitions = { "0", "1" }))
//    @KafkaListener(
//        topicPartitions = @TopicPartition(topic = "baeldung",
//                partitionOffsets = {
//                        @PartitionOffset(partition = "0", initialOffset = "0"),
//                        @PartitionOffset(partition = "3", initialOffset = "0")}),
//        containerFactory = "partitionsKafkaListenerContainerFactory")
//    public void listenToPartition(
//            @Payload String message,
//            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition
//    ) {
//        System.out.println(
//                "Received message: " + message
//                + "from partition: " + partition
//        );
//    }


    @KafkaListener( topicPartitions = @TopicPartition(topic = "baeldung", partitions ={"0","1"}))
    public void listenToPartition(
            @Payload String message,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition
    ) {
        System.out.println(
                "Received message: " + message
                        + "from partition: " + partition
        );
    }

    @KafkaListener(
            topics = "baeldung",
            containerFactory = "greetingKafkaListenerContainerFactory")
    public void greetingListener(Greeting greeting) {
        //
    }

//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, String> filterKafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        factory.setRecordFilterStrategy(
//                record -> record.value().contains("World")
//        );
//        return factory;
//    }
//
//    @KafkaListener(
//            topics = "baeldung",
//            containerFactory = "filterKafkaListenerContainerFactory"
//    )
//    public void listenerWithFilter(String message) {
//        System.out.println("Received Message in filtered listener: "+message);
//    }
}
