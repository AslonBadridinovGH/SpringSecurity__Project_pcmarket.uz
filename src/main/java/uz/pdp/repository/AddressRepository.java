package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.projection.AddressInter;
import uz.pdp.entity.Address;


@RepositoryRestResource(path = "address", excerptProjection = AddressInter.class)
public interface AddressRepository extends JpaRepository<Address,Integer> {
}
