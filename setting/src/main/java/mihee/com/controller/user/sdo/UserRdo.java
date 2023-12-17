package mihee.com.controller.user.sdo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mihee.com.models.user.User;
import mihee.com.models.user.vo.UserType;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRdo {
    private String id;
    private String name;
    private String userNickname;
    private UserType userType;
    private LocalDateTime createdAt;

    public void toRdo (User user) {
        BeanUtils.copyProperties(user, this);
    }
}
