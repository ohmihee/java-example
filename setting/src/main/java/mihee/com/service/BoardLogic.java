package mihee.com.service;


import lombok.RequiredArgsConstructor;
import mihee.com.controller.board.sdo.BoardCdo;
import mihee.com.controller.board.sdo.BoardRdo;
import mihee.com.models.board.Board;
import mihee.com.repository.board.BoardRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardLogic {

    private final BoardRepository boardRepository;

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
