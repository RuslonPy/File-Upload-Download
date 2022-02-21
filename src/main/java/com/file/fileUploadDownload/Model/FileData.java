package com.file.fileUploadDownload.Model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Table(name = "files")
public class FileData {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;

    private String type;

    @Lob
    private byte data;

    public FileData(String name, String type, byte[] data) {
    }

    public FileData(String id, String name, String type, byte data) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.data = data;
    }


    public FileData() {

    }
}
