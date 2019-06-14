package com.storage.java.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Builder(toBuilder = true)
@Data
@AllArgsConstructor
public class DigitalBook {
    private final String id;
    private final String filename;
    private final String contentType;
    private final String title;
    private final String author;
}
