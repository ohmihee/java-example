package mihee.com.kafka.countingmessages;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ToMapExample {
    public static void main(String[] arsg) {
        List<String> words = List.of("apple", "banana", "orange");
        Map<String, String> wordMap = words.stream()
                .collect(Collectors.toMap(Function.identity(), word -> word));
        System.out.println(wordMap);
        // {banana=banana, orange=orange, apple=apple}
    }
}
//public class ToMapExample {
//    public static void main(String[] args) {
//        List<String> words = List.of("apple", "banana", "orange");
//
//        // 각 단어를 자기 자신을 키로 하는 맵으로 변환
//        Map<String, String> wordMap = words.stream()
//                .collect(Collectors.toMap(Function.identity(), word -> word));
//
//        // 결과 출력
//        System.out.println(wordMap);
//    }
//}