package com.example.document1.services.factory;


import com.example.document1.database.Repository;
import com.example.document1.database.impl.DiskRepository;
import com.example.document1.database.impl.InmemoryRepository;
import com.example.document1.models.DocumentTier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseFactory {

    @Autowired
    InmemoryRepository inmemoryRepository;

    @Autowired
    DiskRepository diskRepository;

    public Repository getDatabase(DocumentTier documentTier){
        switch (documentTier){
            case HOT:
                return inmemoryRepository;
            case COLD:
                return diskRepository;
            default:
                break;
        }
        return null;
    };
}
