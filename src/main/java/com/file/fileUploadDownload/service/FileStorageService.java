package com.file.fileUploadDownload.service;

import com.file.fileUploadDownload.Model.FileData;
import com.file.fileUploadDownload.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.stream.Stream;

@Service
public class FileStorageService {

    @Autowired
    private FileRepository fileRepository;

    public FileData store(MultipartFile file) throws Exception{
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        FileData fileData = new FileData(filename, file.getContentType(), file.getBytes());
        return fileRepository.save(fileData);
    }

    public FileData getFile(String id){
        return fileRepository.findById(id).get();
    }

    public Stream<FileData> getAllFiles(){
        return fileRepository.findAll().stream();
    }
}
