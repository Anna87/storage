package com.storage.java.controllers;

import com.storage.java.models.Book;
import com.storage.java.services.DigitalBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class DigitalBookController {

    @Autowired
    DigitalBookService digitalBookService;

    @GetMapping("/digitalBooks")
    public String books() {
        return digitalBookService.GetAllBooks();
    }


    @PostMapping(path = "/addDigitalBook")
    public Book newBook(@RequestParam("file") MultipartFile data, @RequestParam("title") String title, @RequestParam("autor") String author) {
        digitalBookService.AddBook(data, title, author);
        return Book.builder().autor("ff").id("1").isAvalible(true).title("g").build();
    }
    @PostMapping(path = "/addDigitalBook2")
    public String newDigitalBook(@RequestParam("title") String title) {
        String g = title;
        return g;
    }

    @GetMapping("/digitalBooks/{id}")
    public GridFsResource GetBookById(@PathVariable String id){
        GridFsResource res = digitalBookService.GetFile(id);
        return res;
    }

}
