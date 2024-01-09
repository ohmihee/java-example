package mihee.com.kafka.kafka.embedded;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    /**
     * 카프카 메시지 전송을 위해 사용된다.
     * */

    public void send(String topic, String payload) {
        LOGGER.info("sending payload='{}' to topic='{}'", payload );
        // 카프카에 전송할 메시지의 내용을 확인하기 위해 로그를 남김
        kafkaTemplate.send(topic, payload);
        /**
         * 토펙에 메시지를 보내기 위해 사용하는 메서드이다.
         * .send([topicName], [전달할 메시지 내용])
         * */
    }
}
