package com.example.document1.services;

import com.example.document1.database.DocumentAccessUserRepository;
import com.example.document1.database.Repository;
import com.example.document1.exception.NoAccessToDocument;
import com.example.document1.models.*;
import com.example.document1.services.factory.DatabaseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;


@Service
public class DocumentService {

    @Autowired
    private DatabaseFactory databaseFactory;

    @Autowired
    private DocumentAccessUserRepository documentAccessUserRepository;

    public void createDocument(Document document){
        Repository repository = databaseFactory.getDatabase(document.getDocumentTier());
        repository.createDocument(document);
        DocumentGrantAccess documentGrantAccess = DocumentGrantAccess.builder().id(UUID.randomUUID().toString()).documentId(document.getId()).user(document.getOwner())
                .documentAccessesTypeList(Arrays.asList(DocumentAccessesType.values())).build();
        grantAccess(documentGrantAccess);
    }

    public void grantAccess(DocumentGrantAccess documentGrantAccess){
        documentAccessUserRepository.grantAccess(documentGrantAccess);
    }

    public Document readDocument(User user, Document document) throws NoAccessToDocument {
        if(DocumentAccess.GLOBAL.equals(document.getDocumentAccess())) {
            return databaseFactory.getDatabase(document.getDocumentTier()).readDocument(document);
        }
        else{
            // check the access
            CheckAccess checkAccess = CheckAccess.builder().userId(user.getId()).documentId(document.getId()).build();
            DocumentGrantAccess documentGrantAccess = documentAccessUserRepository.hasAccess(checkAccess);
            if(!Objects.isNull(documentGrantAccess) && documentGrantAccess.getDocumentAccessesTypeList().contains(DocumentAccessesType.READ)){
                return databaseFactory.getDatabase(document.getDocumentTier()).readDocument(document);
            }
        }
        throw new NoAccessToDocument();

    }
}
