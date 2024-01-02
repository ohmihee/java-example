package mihee.com.models.category;

import jakarta.persistence.*;
import lombok.Data;
import mihee.com.util.ListTypeConverter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String parentCategory;
    @Convert(converter = ListTypeConverter.class)
    private List<String> test = new ArrayList<>();
}
