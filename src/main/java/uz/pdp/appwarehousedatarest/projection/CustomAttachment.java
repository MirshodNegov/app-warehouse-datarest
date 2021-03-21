package uz.pdp.appwarehousedatarest.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.appwarehousedatarest.entity.Attachment;

@Projection(types = Attachment.class)
public interface CustomAttachment {
    Integer getId();
    String getName();
    long getSize();
    String getContentType();
}
