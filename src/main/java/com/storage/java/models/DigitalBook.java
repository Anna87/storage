package com.storage.java.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DigitalBook {
    private String id;
    private String filename;
    private String contentType;
    private String title;
    private String author;
}
