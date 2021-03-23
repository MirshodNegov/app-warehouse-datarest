package uz.pdp.appwarehousedatarest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwarehousedatarest.entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment,Integer> {
    boolean existsById(Integer id);
}
