package mihee.com.board.service.impl;


import lombok.RequiredArgsConstructor;
import mihee.com.board.controller.board.sdo.BoardCdo;
import mihee.com.board.models.board.Board;
import mihee.com.board.repository.board.BoardRepository;
import mihee.com.board.service.BoardService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public String register (BoardCdo boardCdo) {
        Board board = Board.register(boardCdo);
        String boardId = board.getId();
        Board dbBoard = boardRepository.findById(boardId).orElse(null);
        if (dbBoard != null) {
            register(boardCdo);
        } else {
            return boardRepository.save(board).getId();
        }
        return null;
    }

//    public List<BoardRdo> findAll(Pageable pageable) {
//
//    }
}
