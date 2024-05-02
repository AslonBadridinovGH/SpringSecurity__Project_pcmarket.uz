package uz.pdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.entity.Product;
import uz.pdp.payload.Result;
import uz.pdp.repository.ProductRepository;
import uz.pdp.repository.ShoppingVentureRepository;
import uz.pdp.entity.ShoppingVenture;
import uz.pdp.payload.ShoppingVentureDto;

import java.util.*;

@Service
public class ShoppingVentureService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ShoppingVentureRepository shoppingVentureRepository;


    public Result addShoppingVenture(@RequestBody ShoppingVentureDto shoppingVentureDto) {

        ShoppingVenture shoppingVenture=new ShoppingVenture();

        Set<Product>productList=new HashSet<>();

        List<Integer> productsIds = shoppingVentureDto.getProductsIds();
        for (Integer productsId : productsIds)
        {

            Optional<Product> optionalProduct = productRepository.findById(productsId);
            if (!optionalProduct.isPresent())
                return new Result("Product was not found", false);
            productList.add(optionalProduct.get());
        }
        shoppingVenture.setProducts(productList);
        shoppingVenture.setCount(shoppingVentureDto.getCount());
        shoppingVenture.setDescription(shoppingVentureDto.getDescription());
        shoppingVenture.setSubtotal(shoppingVentureDto.getSubtotal());
        shoppingVenture.setTotal(shoppingVentureDto.getTotal());
        try {
            shoppingVentureRepository.save(shoppingVenture);
        }catch (Exception e){
            return new Result("ShoppingVenture was not added, Product is already used", false);
        }
        return new Result("ShoppingVenture was added", true);
    }

    public ShoppingVenture getShoppingVentureById(Integer id){
        Optional<ShoppingVenture> optional = shoppingVentureRepository.findById(id);
        if (!optional.isPresent()) {
            return new ShoppingVenture();
        }
        ShoppingVenture shoppingVenture = optional.get();
        return shoppingVenture;
    }

    public List<ShoppingVenture> getShoppingVentures(){
        List<ShoppingVenture> shoppingVentures = shoppingVentureRepository.findAll();
        return shoppingVentures;
    }

    public Result editShoppingVenturesById(ShoppingVentureDto shoppingVentureDto, Integer id) {

        Optional<ShoppingVenture> optional = shoppingVentureRepository.findById(id);
        if (!optional.isPresent())
            return new Result("shoppingVenture was not found", false);

        ShoppingVenture shoppingVenture = optional.get();
        Set<Product> productList = new HashSet<>();

        List<Integer> productsIds = shoppingVentureDto.getProductsIds();
        for (Integer productsId : productsIds) {
            Optional<Product> optionalProduct = productRepository.findById(productsId);
            if (!optionalProduct.isPresent())
                return new Result("Product was not found", false);
            productList.add(optionalProduct.get());
        }
            shoppingVenture.setProducts(productList);
            shoppingVenture.setCount(shoppingVentureDto.getCount());
            shoppingVenture.setDescription(shoppingVentureDto.getDescription());
            shoppingVenture.setSubtotal(shoppingVentureDto.getSubtotal());
            shoppingVenture.setTotal(shoppingVentureDto.getTotal());

        try {
             shoppingVentureRepository.save(shoppingVenture);
         }catch (Exception e){
             return new Result("ShoppingVenture was not edited,Product was already used", false);
         }
         return new Result("ShoppingVenture was edited", true);
     }

     public Result deleteShoppingVenture(Integer id){
            try {
                shoppingVentureRepository.deleteById(id);
                return new Result("shoppingVentures was deleted", true);
            } catch (Exception e) {
                return new Result("shoppingVentures was not deleted", false);
            }
        }

    }
