package com.storage.java.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class DigitalBookService {
    @Autowired
    StorageService storageService;


    public String addBook(MultipartFile file, String title, String author) {
        return storageService.store(file,title,author);
    }

    public String getAllBooks(){
        return  storageService.getAllBooks();
    }

    public Resource getFile(String id) throws IOException {
        return storageService.getFile(id);
    }


}
