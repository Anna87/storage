package com.storage.java.dto.responses;

import lombok.Builder;
import lombok.Value;

@Builder(toBuilder = true)
@Value
public class DigitalBookDetails {
    private final String id;
    private final String filename;
    private final String title;
    private final String author;
}
