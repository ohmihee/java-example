package mihee.com.baeldung.kafka.streams;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.Serdes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

import static org.apache.kafka.streams.StreamsConfig.*;

@Configuration
@EnableKafka
@EnableKafkaStreams
/**
 * kafka-streams 라이브러리를 사용하여 데이터 스트림을 사용할 수 있음을 알리는 어노테이션이다.
 * */
public class KafkaConfig {
    @Value(value="${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value(value="${spring.kafka.streams.state.dir}")
    private String stateStoreLocation;

    @Bean(name= KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    KafkaStreamsConfiguration kStreamsConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(APPLICATION_ID_CONFIG, "streams-app");
        /**
         * 카프카 스트림 어플리케이션을 식별하기 위한 id
         * */
        props.put(BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        /**
         * 카프카 클러스터의 부트스크랩 서버 주서
         * */
        props.put(DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        /**
         * 카프카 스트림에서 키와 값을 직렬화하거나, 역직렬화 할 때에 사용할 클래스를 설정하는 것이다.
         * - Serdes.String().getClass().getName()
         *   : 직렬화나 역직렬화에서 사용할 클래스명을 반환하여 준다.
         * */
        props.put(STATE_DIR_CONFIG, stateStoreLocation);
        /**
         * 카프카 스트림의 상태 저장소 위치를 설정하여 주는 것이다.
         * */
        return new KafkaStreamsConfiguration(props);
    }

    @Bean
    NewTopic inputTopic () {
        /**카프카 토픽을 생성하는 메서드이다.*/
        return TopicBuilder.name("input-topic")
                .partitions(1)
                /**1개의 파티션*/
                .replicas(1)
                /**1개의 복제본*/
                .build();
    }
}
