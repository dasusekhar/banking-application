package com.example.Banking.application1.controller;

import com.example.Banking.application1.documentupload.DocumentServiceImpl;
import com.example.Banking.application1.documentupload.FileInfo;
import com.example.Banking.application1.documentupload.FileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/file-document")
public class DocumentUploadController {
  @Autowired
  private DocumentServiceImpl documentService;
  @PostMapping("/upload")
  public FileResponse  uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
    FileInfo attachment = null;
    String downloadURl = "";
    attachment = documentService.saveAttachment(file);
    downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
        .path("/file-document/download")
        .path(attachment.getId())
        .toUriString();

    return new FileResponse(attachment.getFileName(),
        downloadURl,
        file.getContentType(),
        file.getSize());
  }

  @GetMapping("/download/{fileId}")
  public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception {
    FileInfo attachment = null;
    attachment = documentService.getAttachment(fileId);
    return  ResponseEntity.ok()
        .contentType(MediaType.parseMediaType(attachment.getFileType()))
         . header(HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=\"" + attachment.getFileName()
                + "\"")
        .body(new ByteArrayResource(attachment.getData()));
  }


}
