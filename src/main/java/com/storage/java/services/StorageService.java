package com.storage.java.services;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.storage.java.models.DigitalBook;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonObjectId;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class StorageService {

    private final GridFsTemplate gridFsTemplate;

    public String add(final MultipartFile file, final String title, final String author){
        try {
            final InputStream stream = file.getInputStream();
            final DBObject metaData = new BasicDBObject();
            metaData.put("title", title);
            metaData.put("author", author);
            return gridFsTemplate.store(stream, file.getOriginalFilename(), file.getContentType(), metaData).toString();
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage()); //TODO is that ok?
        }
    }

    public List<DigitalBook> getAllBooks(){
        final List<GridFSFile> fileList = new ArrayList<GridFSFile>();
        gridFsTemplate.find(new Query()).into(fileList);

        final List<DigitalBook> digitalBooks = fileList.stream().map(x -> DigitalBook.builder()
                .title(x.getMetadata().getString("title"))
                .author(x.getMetadata().getString("author"))
                .filename(x.getFilename())
                .contentType(x.getMetadata().getString("_contentType"))
                .id(((BsonObjectId) x.getId()).getValue().toString()).build()).collect(Collectors.toList());

        return digitalBooks;
    }


    public Resource getFile(final String id) throws IOException {
        final GridFSFile gridFsFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
        return gridFsTemplate.getResource(gridFsFile.getFilename());
    }

}
