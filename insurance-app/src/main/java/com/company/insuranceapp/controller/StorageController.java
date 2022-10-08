package com.company.insuranceapp.controller;

import com.company.insuranceapp.mapper.StorageMapper;
import com.company.insuranceapp.model.entity.Storage;
import com.company.insuranceapp.model.response.ResponseModel;
import com.company.insuranceapp.model.response.StorageResponse;
import com.company.insuranceapp.service.abstracts.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController("/storage")
@RequiredArgsConstructor
public class StorageController {
    private final StorageService storageService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseModel<StorageResponse>> add (@RequestParam("file") MultipartFile multipartFile)
    {
        if (multipartFile != null && multipartFile.getOriginalFilename() != null)
        {
            String fileName = multipartFile.getOriginalFilename();
            Storage storage = new Storage(fileName, multipartFile.getContentType(), multipartFile.getSize());
            storage = storageService.add(storage);

            StorageResponse storageResponse = StorageMapper.toStorageResponse(storage);
            storageResponse.setDownloadUrl("http://localhost:8099/storage/download/" + storage.getId());
            return ResponseEntity.ok(ResponseModel.success(storageResponse));
        }

        return ResponseEntity.ok(ResponseModel.error(null, "File is not valid."));
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> download (@PathVariable Long id)
    {
        Storage storage = storageService.getById(id);
        if (storage == null)
            return null;

        try {
            File file = new File("directory"  + storage.getName());
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] arr = new byte[(int) file.length()];
            fileInputStream.read(arr);
            fileInputStream.close();

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(storage.getType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + storage.getName() + "\"")
                    .body(new ByteArrayResource(arr));
        } catch (IOException e) {
            return ResponseEntity.ok(null);
        }
    }
}
