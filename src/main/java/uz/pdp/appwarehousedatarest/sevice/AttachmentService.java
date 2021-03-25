package uz.pdp.appwarehousedatarest.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appwarehousedatarest.entity.Attachment;
import uz.pdp.appwarehousedatarest.entity.AttachmentContent;
import uz.pdp.appwarehousedatarest.repository.AttachmentContentRepository;
import uz.pdp.appwarehousedatarest.repository.AttachmentRepository;

import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

@Service
public class AttachmentService {

    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    public Page<Attachment> get(Integer page, Integer size) {
        Pageable pageable= PageRequest.of(page,size);
        return attachmentRepository.findAll(pageable);
    }

    public Attachment getOne(Integer id) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        return optionalAttachment.orElse(null);
    }

    public Attachment add(MultipartHttpServletRequest request) {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        if (file != null) {
            String filename = file.getName();
            long size = file.getSize();
            String contentType = file.getContentType();
            Attachment attachment=new Attachment();
            attachment.setName(filename);
            attachment.setSize(size);
            attachment.setContentType(contentType);
            Attachment savedAttachment = attachmentRepository.save(attachment);
            AttachmentContent attachmentContent=new AttachmentContent();
            try {
                attachmentContent.setBytes(file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            attachmentContent.setAttachment(savedAttachment);
            attachmentContentRepository.save(attachmentContent);
            return savedAttachment;
    }
        return null;
    }

    public Attachment edit(Integer id, MultipartHttpServletRequest request) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (!optionalAttachment.isPresent())
            return null;
        Attachment attachment = optionalAttachment.get();
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        if (file==null)
            return null;
        String filename = file.getName();
        long size = file.getSize();
        String contentType = file.getContentType();
        attachment.setName(filename);
        attachment.setSize(size);
        attachment.setContentType(contentType);
        Attachment savedAttachment = attachmentRepository.save(attachment);
        AttachmentContent attachmentContent=new AttachmentContent();
        try {
            attachmentContent.setBytes(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        attachmentContent.setAttachment(savedAttachment);
        attachmentContentRepository.save(attachmentContent);
        return savedAttachment;
    }

    public boolean delete(Integer id) {
        boolean exists = attachmentRepository.existsById(id);
        if (!exists)
            return false;
        attachmentRepository.deleteById(id);
        AttachmentContent attachmentContentByAttachment_id = attachmentContentRepository.findAttachmentContentByAttachment_Id(id);
        attachmentContentRepository.delete(attachmentContentByAttachment_id);
        return true;
    }
}
