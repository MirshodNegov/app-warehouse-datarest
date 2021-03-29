package uz.pdp.appwarehousedatarest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appwarehousedatarest.entity.Attachment;
import uz.pdp.appwarehousedatarest.sevice.AttachmentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/attachment")
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;

    @PostMapping
    public HttpEntity<?> add(MultipartHttpServletRequest request){
        Attachment savedAttachment = attachmentService.add(request);
        return ResponseEntity.status(savedAttachment!=null?202:409).body(savedAttachment);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        boolean bool = attachmentService.getOne(id, response);
        return ResponseEntity.status(bool?200:409).body("Download");
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        boolean success = attachmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
