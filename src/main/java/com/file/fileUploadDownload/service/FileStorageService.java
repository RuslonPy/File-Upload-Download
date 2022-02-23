package com.file.fileUploadDownload.service;

import com.file.fileUploadDownload.Model.FileData;
import com.file.fileUploadDownload.exception.MyFileNotFoundException;
import com.file.fileUploadDownload.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;


import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.stream.Stream;

@Service
public class FileStorageService {

    @Autowired
    private FileRepository fileRepository;

    private Path fileStorageLocation;

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

    public Resource loadFileAsResource(String fileName){
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }
}
