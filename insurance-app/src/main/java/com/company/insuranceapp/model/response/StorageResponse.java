package com.company.insuranceapp.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StorageResponse {
    private Long id;
    private String name;
    private String downloadUrl;
    private String type;
    private double size;
}
