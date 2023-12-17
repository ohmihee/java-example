package mihee.com.models.board;

import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mihee.com.controller.board.sdo.BoardCdo;
import mihee.com.models.BaseEntityWithId;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

@Entity
@Table(schema = "board",
        indexes = {@Index(name="writer_idx", columnList = "writerId", unique = false),
                @Index(name="board_idx", columnList = "id", unique = true)
        })
@NoArgsConstructor
@Getter
@Setter
public class Board extends BaseEntityWithId {
    @Size(max = 100, message = "Title must be at most 100 characters")
    private String title;
    private String content;
    private String writerId;
    private String writerName;

    public void setId() {
        this.setId(UUID.randomUUID().toString());
        this.setCreatedBy(this.getId());
        this.setLastModifiedBy(this.getId());
    }

    public static Board register(BoardCdo boardCdo) {
        Board board = new Board();
        BeanUtils.copyProperties(boardCdo, board);
        board.setId();
        return board;
    }

}
