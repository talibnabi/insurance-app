package com.company.insuranceapp.mapper;

import com.company.insuranceapp.model.entity.Storage;
import com.company.insuranceapp.model.response.StorageResponse;

public class StorageMapper {
    public static StorageResponse toStorageResponse (Storage storage)
    {
        return StorageResponse.builder().id(storage.getId())
                .name(storage.getName()).size(storage.getSize()).type(storage.getType())
                .build();
    }
}
