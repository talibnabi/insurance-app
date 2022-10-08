package com.company.insuranceapp.service.concretes;

import com.company.insuranceapp.model.entity.Storage;
import com.company.insuranceapp.repository.StorageRepository;
import com.company.insuranceapp.service.abstracts.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StorageServiceManager implements StorageService {
    private final StorageRepository storageRepository;

    @Override
    public Storage add(Storage storage) {
        return storageRepository.save(storage);
    }

    @Override
    public Storage getById(Long id) {
        return storageRepository.findById(id).orElse(null);
    }
}
