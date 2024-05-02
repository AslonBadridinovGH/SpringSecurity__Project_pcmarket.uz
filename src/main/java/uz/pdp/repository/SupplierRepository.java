package uz.pdp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.projection.SupplierInter;
import uz.pdp.entity.Supplier;

@RepositoryRestResource(path = "supplier",excerptProjection = SupplierInter.class)
public interface SupplierRepository extends JpaRepository<Supplier,Integer> {
}
