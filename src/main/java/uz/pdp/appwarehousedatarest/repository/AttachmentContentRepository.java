package uz.pdp.appwarehousedatarest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwarehousedatarest.entity.AttachmentContent;

import java.util.Optional;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent,Integer> {
    boolean existsById(Integer id);
    Optional<AttachmentContent> findByAttachmentId(Integer attachment_id);
}
