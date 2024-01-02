package mihee.com.baeldung.countingmessages;

import jakarta.annotation.PostConstruct;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class KafkaCountingMessagesComponent {
    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    public static Map<String, Object> props = new HashMap<>();

    @PostConstruct
    /**
     * 스트링에서 제공하는 어노테이션으로 빈이 초기화 된 후에 @PostConstruct 어노테이션이 사용된 메서드의 코드가 실행된다.
     * 즉 @PostConstruct 어노테이션은 빈이 생성되고, 의존성이 주입된 후 호출되기 때문에, 초기화 작업을 수행하기 위한 코드를 해당 어노테이션을 통해 실행시킬 수 있다.
     * */
    public void init() {
        System.out.println(getTotalNumberOfMessagesInATopic("baeldung"));
    }

    public Long getTotalNumberOfMessagesInATopic(String topic) {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(getProps());
        List<TopicPartition> partitions = consumer.partitionsFor(topic).stream()
                /**
                 * partitionsFor([topicName])
                 *  : 인자로 주어진 토픽에 대한 모든 파티션의 정보를 PartitionInfo 객체 리스트로 반환하여 준다..
                 *
                 * */
                .map(p -> new TopicPartition(topic, p.partition()))
                /**
                 * topicName과 파티션 id를 통해 TopicPartition 객체로 변환한다.
                 * */
                .collect(Collectors.toList());
        consumer.assign(partitions);
        /**
         * 컨슈머에게 파티션을 할당하고, 해당 파티션의 메시지를 소비하게 한다.
         * 컨슈머가 파티션의 데이터를 어떻게 처리
         * - assign
         *  : 특정 토픽의 특정 파티션의 메시지를 소비하도록 할당하여 준다.
         * - subscribe
         *  : 특정 토픽의 메시지를 소비하도록 한다. 즉 assign과 달리 subscribe으로 구독하는 토픽의 모든 파티션을 동적으로 할당받게 된다.
         *    assign의 경우 토픽은 물론 파티션까지 지정하여 주는 반면, subscribe는 토픽만을 지정하여 주고, 파티션은 카프카 컨슈머 그룹을 통해 공평하게 분배하여 동적으로 할당한다.
         *    subscribe를 사용하는 것이 리벨런싱을 자동으로 수행하여 주기 때문에 더욱 선호되며, assign은 특정한 경우에만 사용이 권장된다.
         *
         * */
        consumer.seekToEnd(Collections.emptySet());
        /**
         * 컨슈머는 각 파티션에서 메시지를 어디까지 가져왔는지 추적한다.
         * 컨슈머의 커서를 할당된 파티션의 가장 마지막으로 이동시킨다.(메시지의 소비는 기본적으로 커서의 위치부터 소비한다.) 이를 통해 이후에 해당 파티션에서 컨슈머가 다시 메시지를 가져올 때 가장 최신의 메시지를 가져올 수 있게 한다.
         * */
        Map<TopicPartition, Long> engPartitions = partitions.stream()
                .collect(Collectors.toMap(Function.identity(), consumer::position));
        /**
         * 현재 컨슈머의 커서 위치를 확인하고, 다음에 가져올 오프셋의 정보를 반환한다.
         * Function.identity()는 함수형 인터페이스의 정적 메서드 중 하나로 입력 값을 그대로 반환하여 주는 역할을 한다.
         *
         * */
        return partitions.stream().mapToLong(p -> engPartitions.get(p)).sum();
    }

    /**
     * 카프카 컨슈머의 속성을 설정
     * props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
     *  : 카프카 클러스터의 부트스트랩 서버 주소 지정
     * props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
     * props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
     *  : 카프카에서는 메시지를 효율적으로 전달하기 위해 데이터를 이진형식으로 직렬화아여 전송한다.
     *    이렇게 이진 형식으로 직렬화하여 전송된 데이터를 카프카 컨슈머에서 다시 역직렬화하여 사용하게 된다.
     *    위의 코드는 카프카 컨슈머가 데이터를 역직렬화 하는 과정에서 어떠한 형식으로 역직렬화 할 것인가에 대한 설정을 해주는 것이다.
     *    StringDeserializer.class로 설정할 경우 문자열 형식으로 역질력화하여 준다.
     *    - serializer : 데이터를 바이트(이진형식)로 변환하는 것
     *    - deserializer : 바이트(이진형식)를 원래의 데이터 형식으로 변환하는 것
     *
     * props.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, "20971520");
     *   : 카프카에서 한 번의 요청으로 가져올 수 있는 최대 데이터 양을 설정하여 주는 것이다.
     *     예를 들어 컨슈머가 파티션에서 한 번에 가져올 수 있는 데이터의 양이 지나치게 작을 경우, 컨슈머는 데이터를 전부 가져오기 위해 여러 번의 요청이 필요하다.
     * props.put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG, "20971521");
     *   : 카프카프에 한 번의 요청으로 가져올 최소한의 데이터 양을 설정하여 주는 것이다.
     *     컨슈머는 최소한 일정 크기 이상의 데이터가 되어야만 파티션에서 데이터를 가져올 수 있기 때문에, 작은 양의 데이터가 계속해서 전송되어, 네트워크 및 리소스를 낭비하지 않도록 해준다.
     * */
    public Map<String, Object> getProps () {
        if (props.isEmpty()) {
            props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
            props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
            props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
            props.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, "20971520");
            props.put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG, "20971521");
        }
        return props;
    }


}
