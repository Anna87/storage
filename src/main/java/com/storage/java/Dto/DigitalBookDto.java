package com.storage.java.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DigitalBookDto {
    MultipartFile file;
}
