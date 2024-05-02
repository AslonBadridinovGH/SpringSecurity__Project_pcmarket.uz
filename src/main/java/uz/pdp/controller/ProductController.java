package uz.pdp.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.entity.Product;
import uz.pdp.payload.ProductDto;
import uz.pdp.payload.Result;
import uz.pdp.service.ProductService;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;


    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR','OPERATOR')")
    @PostMapping
    public HttpEntity<?> addProduct(@RequestBody ProductDto productDto){
        Result result = productService.addProduct(productDto);
        return ResponseEntity.status(201).body(result);
    }

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','OPERATOR')")
    @GetMapping
    public HttpEntity<?> getProduct(){
        List<Product> productsList = productService.getProduct();
        return ResponseEntity.ok(productsList);
    }

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','OPERATOR')")
    @GetMapping("/{id}")
    public  HttpEntity<?> getProductById(@PathVariable Integer id){
        Product productById = productService.getProductById(id);
        return  ResponseEntity.status(productById !=null? HttpStatus.OK:HttpStatus.CONFLICT).body(productById);
    }

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PutMapping("/{id}")
    public  HttpEntity<?> editProduct(@RequestBody ProductDto productDto, @PathVariable Integer id){
        Result result = productService.editProductById(productDto, id);
        return ResponseEntity.status(result !=null ? 202:409).body(result);
    }

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    public  HttpEntity<?> deleteProduct(@PathVariable Integer id){
        Result result = productService.deleteProduct(id);
        return ResponseEntity.status(result.isSuccess()?202:409).body(result);
    }


}
