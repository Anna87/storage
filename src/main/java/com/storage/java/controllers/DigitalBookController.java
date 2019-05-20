package com.storage.java.controllers;

import com.storage.java.services.DigitalBookService;
import com.storage.java.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class DigitalBookController {

    @Autowired
    DigitalBookService digitalBookService;

    @Autowired
    StorageService storageService;

    @GetMapping("/storage/digitalBooks")
    public String books() {
        return digitalBookService.GetAllBooks();
    }


    @PostMapping(path = "/storage/addDigitalBook")
    public String newDigitalBook(@RequestParam("file") MultipartFile data, @RequestParam("title") String title, @RequestParam("author") String author) { // working
        return digitalBookService.AddBook(data, title, author);
    }
    /*
    @GetMapping("/digitalBooks/{id}")
    public GridFsResource GetBookById(@PathVariable String id){
        GridFsResource res = digitalBookService.GetFile(id);
        return res;
    }*/


    @PostMapping(path = "/storage/downloadDigitalBook", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public Resource GetFileByBookId(@RequestParam("fileId") String fileId) throws IOException {
        Resource resource = digitalBookService.GetFile(fileId);
        return resource;
    }

}
