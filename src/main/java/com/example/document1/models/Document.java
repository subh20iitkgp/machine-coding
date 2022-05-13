package com.example.document1.models;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Document {
    private String name;
    private String content;
    private String id;
    private DocumentAccess documentAccess;
    private DocumentTier documentTier;
    private User owner;

}
