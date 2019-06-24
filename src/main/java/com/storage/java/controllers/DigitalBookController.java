package com.storage.java.controllers;

import com.storage.java.converters.DigitalBookDetailsConverter;
import com.storage.java.dto.responses.DigitalBookDetails;
import com.storage.java.services.StorageService;
import com.sun.istack.internal.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.util.List;

import static com.storage.java.constants.Constants.ROLE_ADMIN;

@RequiredArgsConstructor
@RestController
@RequestMapping("/storage")
public class DigitalBookController {

    private final StorageService storageService;

    private final DigitalBookDetailsConverter digitalBookDetailsConverter;

    @GetMapping
    public List<DigitalBookDetails> books() {
        return digitalBookDetailsConverter.convertList(storageService.getAllBooks());
    }

    @Secured(value = {ROLE_ADMIN})
    @PostMapping(path = "/add")
    public String newDigitalBook(
            @NotNull @RequestParam("file") MultipartFile data,
            @NotBlank @RequestParam("title") String title,
            @NotBlank @RequestParam("author") String author) {
        return storageService.add(data, title, author);
    }

    @GetMapping(path = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public Resource getFileByBookId(@NotBlank @RequestParam("fileId") String fileId) throws IOException {
        return storageService.getFile(fileId);
    }

}
