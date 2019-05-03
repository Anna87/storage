package com.storage.java.controllers;

import com.storage.java.services.DigitalBookService;
import com.storage.java.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.ws.ResponseWrapper;
import java.io.IOException;

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


    @PostMapping(path = "/downloadDigitalBook", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public Resource GetFileByBookId(@RequestParam("fileId") String fileId) throws IOException {
        Resource resource = digitalBookService.GetFile(fileId);
        return resource;
    }

}
