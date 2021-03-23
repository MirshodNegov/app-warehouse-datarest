package uz.pdp.appwarehousedatarest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehousedatarest.entity.Attachment;
import uz.pdp.appwarehousedatarest.entity.AttachmentContent;
import uz.pdp.appwarehousedatarest.sevice.AttachmentContentService;
import uz.pdp.appwarehousedatarest.sevice.AttachmentService;

@RestController
@RequestMapping("/api/attachmentContent")
public class AttachmentContentController {

    @Autowired
    AttachmentContentService attachmentContentService;

    @PostMapping
    public HttpEntity<?> add(@RequestBody AttachmentContentDto attachmentContentDto){
        AttachmentContent savedAttachmentContent = attachmentContentService.add(attachmentContentDto);
        return ResponseEntity.status(savedAttachmentContent!=null?202:409).body(savedAttachmentContent);
    }


    @GetMapping
    public HttpEntity<?> get(@RequestParam(defaultValue = "0") Integer page,@RequestParam(defaultValue = "10") Integer size){
        Page<AttachmentContent> attachmentContentPage=attachmentContentService.get(page,size);
        return ResponseEntity.ok(attachmentContentPage);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id){
        AttachmentContent attachmentContent=attachmentContentService.getOne(id);
        return ResponseEntity.status(attachmentContent!=null?200:409).body(attachmentContent);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id,@RequestBody AttachmentContentDto attachmentContentDto){
        AttachmentContent savedAttachmentContent = attachmentContentService.edit(id,attachmentContentDto);
        return ResponseEntity.status(savedAttachmentContent!=null?202:409).body(savedAttachmentContent);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        boolean success = attachmentContentService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
