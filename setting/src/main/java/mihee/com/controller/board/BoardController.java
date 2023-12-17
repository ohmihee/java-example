package mihee.com.controller.board;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/board")
@RequiredArgsConstructor
public class BoardController {
   // private final
    @PostMapping()
    public String registerBoard() {
        return null;
    }


}
