package com.storage.java.repositories;


import com.mongodb.DBObject;
import com.storage.java.models.DigitalBook;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.List;

@Repository
public interface DigitalBookRepository extends MongoRepository<DigitalBook, String> {
    List<DigitalBook> findByFilename(String filename);


}

