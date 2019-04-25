package com.storage.java.controllers;

import com.storage.java.Dto.BookDto;
import com.storage.java.models.Book;
import com.storage.java.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/books")
    public String books() {
        return bookService.GetAllBooks();
    }

    @PostMapping(path = "/addBook")
    public Book newBook(@RequestBody BookDto dto) {
        return bookService.AddBook(dto);
    }

    @PostMapping(path = "/editBook")
    public Book editBook(@RequestBody BookDto dto) {
        return bookService.editBook(dto);
    }

    @PostMapping(path = "/deleteBook")
    public boolean deleteBook(@RequestBody BookDto dto) {
        return bookService.deleteBook(dto);
    }
}
