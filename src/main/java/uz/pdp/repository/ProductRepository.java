package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.entity.Product;


public interface ProductRepository extends JpaRepository<Product,Integer> {

      boolean existsByNameAndPhotoId(String name, Integer photo_id);

      boolean existsByNameAndPhotoIdNot(String name, Integer photo_id);
}

