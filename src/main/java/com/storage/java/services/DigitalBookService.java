package com.storage.java.services;

import com.storage.java.repositories.DigitalBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class DigitalBookService {
    private final String BOOKNOTFOUND = "Book not found";

    @Autowired
    DigitalBookRepository digitalBookRepository;


    @Autowired
    StorageService storageService;


    public String AddBook(MultipartFile file, String title, String author) {
        return storageService.store(file,title,author);
    }

    public String GetAllBooks(){
        return  storageService.GetAllBooks();
    }

    public InputStream GetFile(String id) throws IOException {
        return storageService.GetFile(id);
    }


}
