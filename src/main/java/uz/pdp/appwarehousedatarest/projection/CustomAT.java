package uz.pdp.appwarehousedatarest.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.appwarehousedatarest.entity.Attachment;
import uz.pdp.appwarehousedatarest.entity.AttachmentContent;

@Projection(types = AttachmentContent.class)
public interface CustomAT {
    Integer getId();
    byte[] getBytes();
    Attachment getAttachment();
}
