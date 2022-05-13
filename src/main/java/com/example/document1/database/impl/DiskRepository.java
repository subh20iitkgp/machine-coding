package com.example.document1.database.impl;

import com.example.document1.database.Repository;
import com.example.document1.models.Document;
import com.example.document1.models.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class DiskRepository implements Repository {

    public Map<String, Document> documentMap = new HashMap<>();
    public void createDocument(Document document){
       if(!documentMap.containsKey(document.getId())){
           documentMap.put(document.getId(),document);
        }
    }

    @Override
    public Document readDocument(Document document) {
        return documentMap.get(document.getId());
    }
}
