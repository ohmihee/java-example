package mihee.com.kafka.spring.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Greeting {
    private String msg;
    private String name;

    @Override
    public String toString() {
        return msg + ", " + name + "!";
    }

}
