package mihee.com.controller.category.sdo;

import lombok.RequiredArgsConstructor;
import mihee.com.service.CategoryLogic;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryLogic categoryLogic;

    @GetMapping("/test")
    public void test(){
        categoryLogic.test();
    }

}
