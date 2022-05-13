package com.example.document1.database;

import com.example.document1.models.*;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DocumentAccessUserRepository {

    private Map<String, DocumentGrantAccess> documentAccess = new HashMap<>();

    public void grantAccess(DocumentGrantAccess documentGrantAccess) {
        String key = documentGrantAccess.getDocumentId() + documentGrantAccess.getUser();
        if (!documentAccess.containsKey(key)) {
            documentAccess.put(documentGrantAccess.getId() + documentGrantAccess.getUser(), documentGrantAccess);
        }
        else{
            DocumentGrantAccess documentGrantAccess1 = documentAccess.get(key);
            documentGrantAccess1.setDocumentAccessesTypeList(documentGrantAccess.getDocumentAccessesTypeList());
            documentAccess.put(key, documentGrantAccess);
        }
    }

    public DocumentGrantAccess hasAccess(CheckAccess checkAccess){
        String key = checkAccess.getDocumentId()+checkAccess.getUserId();
        if(documentAccess.containsKey(checkAccess.getDocumentId()+checkAccess.getUserId())){
            return documentAccess.get(key);
        }
        return null;
    }
}
