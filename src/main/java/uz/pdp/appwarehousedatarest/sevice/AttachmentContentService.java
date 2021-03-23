package uz.pdp.appwarehousedatarest.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehousedatarest.controller.AttachmentContentDto;
import uz.pdp.appwarehousedatarest.entity.Attachment;
import uz.pdp.appwarehousedatarest.entity.AttachmentContent;
import uz.pdp.appwarehousedatarest.repository.AttachmentContentRepository;
import uz.pdp.appwarehousedatarest.repository.AttachmentRepository;

import java.util.Optional;

@Service
public class AttachmentContentService {

    @Autowired
    AttachmentContentRepository attachmentContentRepository;
    @Autowired
    AttachmentRepository attachmentRepository;

    public Page<AttachmentContent> get(Integer page, Integer size) {
        Pageable pageable= PageRequest.of(page,size);
        return attachmentContentRepository.findAll(pageable);
    }

    public AttachmentContent getOne(Integer id) {
        Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepository.findById(id);
        return optionalAttachmentContent.orElse(null);
    }

    public AttachmentContent add(AttachmentContentDto attachmentContentDto) {
        AttachmentContent attachmentContent=new AttachmentContent();
        attachmentContent.setBytes(attachmentContentDto.getBytes());
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(attachmentContentDto.getAttachmentId());
        if (!optionalAttachment.isPresent())
            return null;
        Attachment attachment = optionalAttachment.get();
        attachmentContent.setAttachment(attachment);
        return attachmentContentRepository.save(attachmentContent);
    }

    public AttachmentContent edit(Integer id, AttachmentContentDto attachmentContentDto) {
        Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepository.findById(id);
        if (!optionalAttachmentContent.isPresent())
            return null;
        AttachmentContent attachmentContent = optionalAttachmentContent.get();
        attachmentContent.setBytes(attachmentContentDto.getBytes());
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(attachmentContentDto.getAttachmentId());
        if (!optionalAttachment.isPresent())
            return null;
        Attachment attachment = optionalAttachment.get();
        attachmentContent.setAttachment(attachment);
        return attachmentContentRepository.save(attachmentContent);
    }

    public boolean delete(Integer id) {
        boolean exists = attachmentContentRepository.existsById(id);
        if (!exists)
            return false;
        attachmentContentRepository.deleteById(id);
        return true;
    }
}
