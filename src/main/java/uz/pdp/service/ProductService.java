package uz.pdp.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.entity.Attachment;
import uz.pdp.entity.Category;
import uz.pdp.entity.Product;
import uz.pdp.payload.ProductDto;
import uz.pdp.payload.Result;
import uz.pdp.repository.AttachmentRepository;
import uz.pdp.repository.CategoryRepository;
import uz.pdp.repository.ProductRepository;

import java.util.*;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    AttachmentRepository attachmentRepository;


    public Result addProduct(@RequestBody ProductDto productDto) {

        boolean exists = productRepository.existsByNameAndPhotoId(productDto.getName(), productDto.getPhotoId());
        if (exists) {
            return new Result("Such Product with thies Photo is already exist",false);
        }


        Product product=new Product();
      Set<Category> categories=new HashSet<>();

     /*   boolean exists = productRepository.existsByNameAndCategories(productDto.getName(), categories);
        if (exists) {
            return new Result("it is exist",false);
    }*/

     List<Integer> categoryIds = productDto.getCategoryIds();
     for (Integer categoryId : categoryIds) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (!optionalCategory.isPresent())
            return new Result("such category does not exist", false);
        categories.add(optionalCategory.get());

     /*
     boolean exists = productRepository.existsByNameAndCategoryId(productDto.getName(),categoryId);
     if (exists) {
         return new Result("it is exist", false);
     }*/

    }
    product.setCategories(categories);

        //CHECK PHOTO
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (!optionalAttachment.isPresent())
            return new Result("such photo does not exist",false);
    product.setPhoto(optionalAttachment.get());
    product.setName(productDto.getName());
    product.setDiscounted(productDto.isDiscounted());
    product.setDescription(productDto.getDescription());
    product.setAvailable(productDto.isAvailable());
    product.setOnlyInCollections(productDto.isOnlyInCollections());
    product.setFactoryName(productDto.getFactoryName());
    product.setRecommend(productDto.isRecommend());
    productRepository.save(product);
        return new Result("Product was saved",true);
    }

    public Product getProductById(Integer id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent()) {
            return new Product();
        }
        Product product = optionalProduct.get();
        return product;
    }

    public List<Product> getProduct(){
        List<Product> productList = productRepository.findAll();
        return productList;
    }

    public Result editProductById(ProductDto productDto, Integer id) {

        boolean exists = productRepository.existsByNameAndPhotoIdNot(productDto.getName(), productDto.getPhotoId());
        if (exists)
            return new Result("Such Product with thies Photo is already exist",false);

        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent())
            return new Result("Product was not found", false);
        Product product = optionalProduct.get();

        Set<Category>categories=new HashSet<>();
        List<Integer> categoryIds = productDto.getCategoryIds();
        for (Integer categoryId : categoryIds) {

            Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
            if (!optionalCategory.isPresent())
                return new Result("such category does not exist", false);
            categories.add(optionalCategory.get());

 /*      boolean exists = productRepository.existsByNameAndCategoryIdAndIdNot(productDto.getName(), categoryId, id);
       // boolean exists = productRepository.existsByNameAndCategoriesNot(productDto.getName(),categories);
            if (exists) {
                return new Result("this product exist", false);
}*/
        }
        product.setCategories(categories);

            Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
            if (!optionalAttachment.isPresent())
                return new Result("such photo does not exist", false);

        product.setPhoto(optionalAttachment.get());
        product.setName(productDto.getName());
        product.setDiscounted(productDto.isDiscounted());
        product.setDescription(productDto.getDescription());
        product.setAvailable(productDto.isAvailable());
        product.setOnlyInCollections(productDto.isOnlyInCollections());
        product.setFactoryName(productDto.getFactoryName());
        product.setRecommend(productDto.isRecommend());

        productRepository.save(product);
        return new Result("Product was edited", true);
     }

     public Result deleteProduct (Integer id){
            try {
                productRepository.deleteById(id);
                return new Result("Product was deleted", true);
            } catch (Exception e) {
                return new Result("Product was not deleted", false);
            }
        }

    }
