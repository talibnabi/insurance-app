package com.company.insuranceapp.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class APIError {
    private final String code;
    private final String message;
    private final List<String> errors;
    private final String path;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private final LocalDateTime timestamp;

    public APIError(String message, String code, String path, List<String> errors) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.code = code;
        this.path = path;
        this.errors = errors;
    }

    public APIError(String message, String code, String path) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.code = code;
        this.path = path;
        this.errors = new ArrayList<>();
    }


}
