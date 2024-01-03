package mihee.com.baeldung.spring.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Farewell {
    private String message;
    private Integer remainingMinutes;

    @Override
    public String toString() {
        return message + ". In " + remainingMinutes + "!";
    }
}
