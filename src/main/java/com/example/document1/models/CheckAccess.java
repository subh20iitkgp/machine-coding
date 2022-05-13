package com.example.document1.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CheckAccess {
    private String userId;
    private String documentId;
}
