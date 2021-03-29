package uz.pdp.appwarehousedatarest.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appwarehousedatarest.entity.Attachment;
import uz.pdp.appwarehousedatarest.entity.AttachmentContent;
import uz.pdp.appwarehousedatarest.repository.AttachmentContentRepository;
import uz.pdp.appwarehousedatarest.repository.AttachmentRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

@Service
public class AttachmentService {

    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    public boolean getOne(Integer id, HttpServletResponse response) throws IOException {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if(optionalAttachment.isPresent()){
            Attachment attachment = optionalAttachment.get();
            Optional<AttachmentContent> contentOptional = attachmentContentRepository.findByAttachmentId(id);
            if (contentOptional.isPresent()){
                AttachmentContent attachmentContent = contentOptional.get();

                response.setHeader("Content-Disposition","attachment;filename=\""+
                        attachment.getName()+"\"");

                response.setContentType(attachment.getContentType());
                FileCopyUtils.copy(attachmentContent.getBytes(),response.getOutputStream());
                return true;
            }
        }
        return false;
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

    public boolean delete(Integer id) {
        boolean exists = attachmentRepository.existsById(id);
        if (!exists)
            return false;
        attachmentRepository.deleteById(id);
        Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepository.findByAttachmentId(id);
        if (optionalAttachmentContent.isPresent()){
            attachmentContentRepository.delete(optionalAttachmentContent.get());
            return true;
        }
        return false;
    }
}
