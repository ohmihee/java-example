package mihee.com.board.models.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mihee.com.board.controller.user.sdo.UserCdo;
import mihee.com.board.models.BaseEntityWithId;
import mihee.com.board.models.user.vo.UserType;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

@Entity
@Table(schema = "user")
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntityWithId {
    @NotBlank(message = "username is required")
    @Column(name="userName")
    private String name;
    @Column(name="userNickname")
    private String userNickname;
    @NotBlank(message = "password is required")
    @Column(name="password")
    private String userPassword;
    @Enumerated(EnumType.STRING)
    private UserType userType;

    public void setId() {
        this.setId(UUID.randomUUID().toString());
        this.setCreatedBy(this.getId());
        this.setLastModifiedBy(this.getId());
    }
    public static User toEntity(UserCdo userCdo) {
        User user = new User();
        BeanUtils.copyProperties(userCdo, user);
        user.setId();
        return user;
    }

}

