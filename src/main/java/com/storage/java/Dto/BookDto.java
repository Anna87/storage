package com.storage.java.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    String id;
    String title;
    String autor;
    Boolean isAvalible;
}
