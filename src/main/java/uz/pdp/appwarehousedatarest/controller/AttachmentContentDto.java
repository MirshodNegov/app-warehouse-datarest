package uz.pdp.appwarehousedatarest.controller;

import lombok.Data;

@Data
public class AttachmentContentDto {
    private Integer id;
    private byte[] bytes;
    private Integer attachmentId;
}
