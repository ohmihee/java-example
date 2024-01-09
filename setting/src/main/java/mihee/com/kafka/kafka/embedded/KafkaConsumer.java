package mihee.com.kafka.kafka.embedded;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class KafkaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    private CountDownLatch latch = new CountDownLatch(1);
    /**
     * 자바는 멀티 쓰레드 환경을 지원하는 프로그래밍 언어로, 동시에 여러 쓰레드가 실행되는 것이 가능하기 때문에
     * 각 쓰레드 간 동기화와 조정이 필요한다.
     * CountDownLatch는 이러한 멀티 쓰레드 환경에서 쓰레드의 작업이 완료되기를 기다려 줌으로써
     * 쓰레드 간 동기화를 지원하여 주는 도구이다.
     * 위와 같이 CountDownLatch를 초기화하여 준다.
     * 그리고 특정 작업이 완료되면 countDown()메서드를 호출하여 countDownLatch를 감소시킨다.
     * countdownlatch는 값이 0이 될 때까지 대기 상태가 되어, 쓰레드 간 동기화를 지원한다.
     * */

    private String payload;

    @KafkaListener(topics = "${test.topic}")
    public void receive(ConsumerRecord<?,?> comsumerRecord) {
        LOGGER.info("received payload='{}'", comsumerRecord.toString());

        payload = comsumerRecord.toString();
        // 수신함 메시지를 payload에 담아둔다.
        latch.countDown();
        /**
         * */
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public void resetLatch () {
        latch = new CountDownLatch(1);
    }

    public String getPayload() {
        return payload;
    }
}
