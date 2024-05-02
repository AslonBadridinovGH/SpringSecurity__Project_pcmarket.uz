package uz.pdp.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.entity.Attachment;


@Projection(types = Attachment.class)
public interface AddressInter {

     Integer getId();

     String getHouseNumber();

     String getStreet();

}
