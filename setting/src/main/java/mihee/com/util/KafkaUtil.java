package mihee.com.util;

import lombok.RequiredArgsConstructor;
import mihee.com.models.testEntity.Farewell;
import mihee.com.models.testEntity.Greeting;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Component
public class KafkaUtil {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaTemplate<String, Greeting> greetingKafkaTemplate;
    private final KafkaTemplate<String, Object> multiKafkaTemplate;
    public void sendMessage(String message) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send("baeldung", message);
        future.whenComplete((result, ex) -> {
            if (ex==null){
                System.out.println("Sent message=[" + message + "] with offset =[" +result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" + message +"] due to: "+ ex.getMessage());
            }
        });
        greetingKafkaTemplate.send("baeldung", new Greeting("Hello", "World"));

        multiKafkaTemplate.send("baeldung", new Greeting("Greetings", "World!"));
        multiKafkaTemplate.send("baeldung", new Farewell("Farewell", 25));
        multiKafkaTemplate.send("baeldung", "Simple string message");


    }
}
