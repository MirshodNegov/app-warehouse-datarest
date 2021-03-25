package uz.pdp.appwarehousedatarest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appwarehousedatarest.entity.Attachment;
import uz.pdp.appwarehousedatarest.sevice.AttachmentService;

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


    @GetMapping
    public HttpEntity<?> get(@RequestParam(defaultValue = "0") Integer page,@RequestParam(defaultValue = "10") Integer size){
        Page<Attachment> attachmentPage=attachmentService.get(page,size);
        return ResponseEntity.ok(attachmentPage);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id){
        Attachment attachment=attachmentService.getOne(id);
        return ResponseEntity.status(attachment!=null?200:409).body(attachment);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id,MultipartHttpServletRequest request){
        Attachment savedAttachment=attachmentService.edit(id,request);
        return ResponseEntity.status(savedAttachment!=null?202:409).body(savedAttachment);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        boolean success = attachmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
