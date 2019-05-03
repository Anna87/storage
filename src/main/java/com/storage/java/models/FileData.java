package com.storage.java.models;

import lombok.*;

@Builder(toBuilder = true)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FileData {
    private String contentType;
    private String fileName;
    private byte[] bytes;
}
