package com.storage.java.controllers;

import com.storage.java.services.DigitalBookService;
import com.storage.java.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
public class DigitalBookController {

    @Autowired
    DigitalBookService digitalBookService;

    @Autowired
    StorageService storageService;

    @GetMapping("/digitalBooks")
    public String books() {
        return digitalBookService.GetAllBooks();
    }


    @PostMapping(path = "/addDigitalBook")
    public String newDigitalBook(@RequestParam("file") MultipartFile data, @RequestParam("title") String title, @RequestParam("author") String author) { // working
        return digitalBookService.AddBook(data, title, author);
    }
    /*
    @GetMapping("/digitalBooks/{id}")
    public GridFsResource GetBookById(@PathVariable String id){
        GridFsResource res = digitalBookService.GetFile(id);
        return res;
    }*/

    @PostMapping("/downloadDigitalBook")
    public void GetFileByBookId(@RequestParam("fileId") String fileId) {
        try {
            InputStream res = digitalBookService.GetFile(fileId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //return res;
    }

}
