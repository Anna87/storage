package com.storage.java.converters;

import com.storage.java.dto.responses.DigitalBookDetails;
import com.storage.java.models.DigitalBook;
import org.springframework.stereotype.Component;

@Component
public class DigitalBookDetailsConverter implements GenericConverter<DigitalBookDetails, DigitalBook> {

    @Override
    public DigitalBookDetails convert(final DigitalBook book) {
        return DigitalBookDetails.builder()
                .id(book.getId())
                .filename(book.getFilename())
                .author(book.getAuthor())
                .title(book.getTitle())
                .build();
    }
}
