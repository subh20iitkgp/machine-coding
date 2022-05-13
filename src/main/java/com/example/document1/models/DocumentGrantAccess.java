package com.example.document1.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DocumentGrantAccess {

    private String id;
    private String documentId;
    private User user;
    private List<DocumentAccessesType> documentAccessesTypeList;
}
