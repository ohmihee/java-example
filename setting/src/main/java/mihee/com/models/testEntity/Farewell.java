package mihee.com.models.testEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Farewell {
    private String message;
    private Integer remainingMinutes;
}
