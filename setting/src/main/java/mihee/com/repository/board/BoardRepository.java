package mihee.com.repository.board;

import mihee.com.models.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, String> {
    @Query("SELECT a.id, a.title, a.writerId, a.writerName, a.createdAt FROM Board a order by a.createdAt")
    List<Board> findAllBoardWriterNameLike(String writerName);
}
