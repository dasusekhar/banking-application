package com.example.Banking.application1.repository;

import com.example.Banking.application1.documentupload.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<FileInfo,String> {

}
