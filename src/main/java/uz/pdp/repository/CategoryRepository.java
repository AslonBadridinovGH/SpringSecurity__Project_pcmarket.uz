package uz.pdp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.entity.Category;



public interface CategoryRepository extends JpaRepository<Category, Integer> {

    boolean existsByNameAndParentCategoryId(String name, Integer parentCategory_id);
    boolean existsByNameAndParentCategoryIdAndIdNot(String name, Integer parentCategory_id, Integer id);

}
