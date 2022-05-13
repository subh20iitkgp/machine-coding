package com.example.document1;

import com.example.document1.models.*;
import com.example.document1.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.UUID;

@Component
public class CommandRunner implements CommandLineRunner {
    @Autowired
    private DocumentService documentService;

    @Override
    public void run(String... args) throws Exception {
        User onwer = User.builder().id(UUID.randomUUID().toString()).name("s1").build();
        Document document = Document.builder().documentAccess(DocumentAccess.PRIVATE).content("dsfshfhfi").id(UUID.randomUUID().toString()).name("d1").documentTier(DocumentTier.COLD).owner(onwer).build();
        documentService.createDocument(document);
        String user2Id =UUID.randomUUID().toString();
        User user2 = User.builder().id(user2Id).name("s2").build();
        documentService.grantAccess(DocumentGrantAccess.builder().documentId(document.getId()).id(UUID.randomUUID().toString()).user(user2).documentAccessesTypeList(Arrays.asList(DocumentAccessesType.READ)).build());
        documentService.readDocument(user2,document);

    }
}
