package com.example.Banking.application1.documentupload;

import com.example.Banking.application1.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DocumentServiceImpl {
@Autowired
private DocumentRepository documentRepository;
  public FileInfo saveAttachment(MultipartFile file) throws Exception {
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    try {
      if(fileName.contains("..")) {
        throw  new Exception("Filename contains invalid path sequence "
            + fileName);
      }

      FileInfo attachment
          = new FileInfo(fileName,
          file.getContentType(),
          file.getBytes());
      return documentRepository.save(attachment);

    } catch (Exception e) {
      throw new Exception("Could not save File: " + fileName);
    }
  }

  public FileInfo getAttachment(String fileId) throws Exception {
    return documentRepository
        .findById(fileId)
        .orElseThrow(
            () -> new Exception("File not found with Id: " + fileId));
  }

}
