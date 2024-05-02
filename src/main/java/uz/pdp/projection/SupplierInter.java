package uz.pdp.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.entity.Supplier;


@Projection(types = Supplier.class)
public interface SupplierInter {

    Integer getId();

    String  getName();

    String getEmail();

    String getDescription();

    String getMessage();
}
