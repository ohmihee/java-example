package mihee.com.board.controller.category.sdo;

import lombok.RequiredArgsConstructor;
import mihee.com.board.service.impl.CategoryServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryServiceImpl categoryServiceImpl;

    @GetMapping("/test")
    public void test(){
        categoryServiceImpl.test();
    }

}
