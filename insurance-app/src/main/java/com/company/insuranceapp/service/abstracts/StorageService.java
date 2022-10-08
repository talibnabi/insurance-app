package com.company.insuranceapp.service.abstracts;

import com.company.insuranceapp.model.entity.Storage;

public interface StorageService {
    Storage add (Storage storage);

    Storage getById (Long id);
}
