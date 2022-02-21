package com.file.fileUploadDownload.repository;

import com.file.fileUploadDownload.Model.FileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileData, String> {
}
