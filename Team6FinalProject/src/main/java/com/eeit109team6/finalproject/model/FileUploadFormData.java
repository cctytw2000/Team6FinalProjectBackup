package com.eeit109team6.finalproject.model;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadFormData {

    /* MultipartFile is used to save the uploaded file content. 
     * */
    private MultipartFile file = null;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
