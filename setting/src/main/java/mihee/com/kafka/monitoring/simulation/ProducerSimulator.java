package mihee.com.kafka.monitoring.simulation;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.ExecutionException;

import static mihee.com.kafka.monitoring.util.MonitoringUtil.endTime;
import static mihee.com.kafka.monitoring.util.MonitoringUtil.time;

@Service
public class ProducerSimulator {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topicName;
    private final boolean enabled;

    @Autowired
    public ProducerSimulator(KafkaTemplate<String, String> kafkaTemplate,
                             @Value( value = "${monitory.topic.name}}")String topicName,
                             @Value(value="${monitory.producer.simulate}") String enabled)
    {
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
        this.enabled = BooleanUtils.toBoolean(enabled);
    }

    @Scheduled(fixedDelay =1L, initialDelay = 5L)
    public void sendMessages() throws ExecutionException, InterruptedException {
        if (enabled) {
            if (endTime.after(new Date())) {
                String message = "msg- " + time();
                SendResult<String, String> result = kafkaTemplate
                    .send(topicName, message)
                        .get();
            }
        }
    }
}
