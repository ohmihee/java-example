package mihee.com.board.models.board;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mihee.com.board.models.BaseEntityWithId;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Comment extends BaseEntityWithId {
    @NotBlank(message = "Text is required")
    @Size(max = 500, message = "Text must be at most 500 characters")
    private String text;
    private String boardId;

}
