package com.example.Banking.application1.documentupload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileResponse {
  private String fileName;
  private String downloadURL;
  private String fileType;
  private long fileSize;



}
