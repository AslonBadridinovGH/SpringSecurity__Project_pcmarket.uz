package uz.pdp.payload;

import lombok.Data;

import java.util.List;

@Data
public class CategoryDto {

    private String  name;
    private String  description;
    private Integer parentCategoryId;
    private List<Integer> photosIds;

}
