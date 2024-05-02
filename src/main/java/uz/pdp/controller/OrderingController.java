package uz.pdp.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.entity.Ordering;
import uz.pdp.payload.OrderingDto;
import uz.pdp.payload.Result;
import uz.pdp.service.OrderingService;
import java.util.List;

@RestController
@RequestMapping("/api/ordering")
public class OrderingController {

    @Autowired
    OrderingService orderingService;

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN')")
    @PostMapping
    public HttpEntity<?> addOrdering(@RequestBody OrderingDto orderingDto){
        Result result = orderingService.addOrdering(orderingDto);
        return ResponseEntity.status(201).body(result);
    }

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN')")
    @GetMapping
    public HttpEntity<?> getOrdering(){
        List<Ordering> orderingList = orderingService.getOrdering();
        return ResponseEntity.ok(orderingList);
    }

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN')")
    @GetMapping("/{id}")
    public  HttpEntity<?>  getOrderingById(@PathVariable Integer id){
        Ordering orderingById = orderingService.getOrderingById(id);
        return ResponseEntity.status(orderingById !=null ? HttpStatus.OK:HttpStatus.CONFLICT).body(orderingById);
    }

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN')")
    @PutMapping("/{id}")
    public  Result editOrdering(@RequestBody OrderingDto orderingDto, @PathVariable Integer id){
        Result result = orderingService.editOrderingById(orderingDto,id);
        return result;
    }

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    public  Result deleteOrdering(@PathVariable Integer id){
        Result result = orderingService.deleteOrdering(id);
        return result;
    }


}
