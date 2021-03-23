package uz.pdp.appwarehousedatarest.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehousedatarest.entity.Attachment;
import uz.pdp.appwarehousedatarest.repository.AttachmentRepository;

import java.util.Optional;

@Service
public class AttachmentService {

    @Autowired
    AttachmentRepository attachmentRepository;

    public Page<Attachment> get(Integer page, Integer size) {
        Pageable pageable= PageRequest.of(page,size);
        return attachmentRepository.findAll(pageable);
    }

    public Attachment getOne(Integer id) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        return optionalAttachment.orElse(null);
    }

    public Attachment add(Attachment attachment) {
        return attachmentRepository.save(attachment);
    }

    public Attachment edit(Integer id, Attachment attachment) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (!optionalAttachment.isPresent())
            return null;
        Attachment attachmentB = optionalAttachment.get();
        attachmentB.setName(attachment.getName());
        attachmentB.setSize(attachment.getSize());
        attachmentB.setContentType(attachment.getContentType());
        return attachmentRepository.save(attachmentB);
    }

    public boolean delete(Integer id) {
        boolean exists = attachmentRepository.existsById(id);
        if (!exists)
            return false;
        attachmentRepository.deleteById(id);
        return true;
    }
}
