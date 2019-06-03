package com.storage.java.services;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.storage.java.common.JsonParserHelper;
import com.storage.java.models.DigitalBook;
import org.bson.BsonObjectId;
import org.springframework.beans.factory.annotation.Autowired;
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
public class StorageService {

    @Autowired
    GridFsTemplate gridFsTemplate;

    @Autowired
    JsonParserHelper jsonParserHelper;


    public String store(MultipartFile file, String title, String author){
        InputStream stream = null;
        try {
            stream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        DBObject metaData = new BasicDBObject();
        metaData.put("title", title);
        metaData.put("author", author);
        return gridFsTemplate.store(stream, file.getOriginalFilename(), file.getContentType(), metaData).toString();
    }

    public String getAllBooks(){
        List<GridFSFile> fileList = new ArrayList<GridFSFile>();
        gridFsTemplate.find(new Query()).into(fileList);

        List<DigitalBook> digitalBooks = fileList.stream().map(x -> DigitalBook.builder()
                .title(x.getMetadata().getString("title"))
                .author(x.getMetadata().getString("author"))
                .filename(x.getFilename())
                .contentType(x.getMetadata().getString("_contentType"))
                .id(((BsonObjectId) x.getId()).getValue().toString()).build()).collect(Collectors.toList());

        return jsonParserHelper.writeToStrJson(digitalBooks);
    }


    public Resource getFile(String id) throws IOException {
        GridFSFile gridFsFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
        return gridFsTemplate.getResource(gridFsFile.getFilename());
    }

}
