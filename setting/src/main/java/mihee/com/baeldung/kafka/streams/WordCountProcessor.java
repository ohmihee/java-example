package mihee.com.baeldung.kafka.streams;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class WordCountProcessor {
    private static final Serde<String> STRING_SERDE = Serdes.String();
    /**
     * 문자열을 직렬화 또는 역직렬화 하기 위한 Serde 객체를 정의, 문자열을 사용
     * Serde
     *  : 직렬화와 역직렬화를 위해 사용되는 도구로, 데이터를 이진형식으로 변환하거나, 이진 형식에서 객체로 변환하는 작업을 수행한다.
     * */

    @Autowired
    void buildPipeline(StreamsBuilder streamsBuilder) {
        KStream<String, String> messageStream = streamsBuilder
                .stream("input-topic", Consumed.with(STRING_SERDE, STRING_SERDE));
        /**
         * input-topic 에서 메시지를 수신하며, 해당 메시지를 받을 때 키와 값의 직렬화, 역직렬화는 STRING_SERDE로 한다.
         * 첫번째 인자는 키에 대해, 두번째 인자는 값에 대해
         * */
        KTable<String, Long> wordCounts = messageStream
                .mapValues((ValueMapper<String, String>) String::toLowerCase)
                /**
                 * messageStream의 모든 값을 소문자로 변환
                 * */
                .flatMapValues(value -> Arrays.asList(value.split("\\W+")))
                /**
                 * 값을 공백으로 분리하고, 그 결과들을 리스트로 저장
                 * */
                .groupBy((key, word) -> word, Grouped.with(STRING_SERDE, STRING_SERDE))
                /**
                 * 단어를 기준으로 그룹화하고, 그룹화 시 직렬화와 역직렬화 방식은 STRING_SERDE
                 * */
                .count(Materialized.as("counts"));
                /**
                 * counts로 저장
                 * */

        wordCounts.toStream().to("output-topic");
        /**
         * 스트림으로 변환하여 output-topic에 전송
         * */
    }
}
