package com.example.document1.database;

import com.example.document1.models.Document;
import com.example.document1.models.User;

import javax.print.Doc;
import java.util.HashMap;
import java.util.Map;

public interface Repository {

    public void createDocument(Document document);

    public Document readDocument(Document document);

}
