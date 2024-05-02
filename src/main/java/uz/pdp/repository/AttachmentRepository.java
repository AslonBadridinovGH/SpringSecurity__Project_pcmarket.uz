package uz.pdp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment,Integer> {
}
