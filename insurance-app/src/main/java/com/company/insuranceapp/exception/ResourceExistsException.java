package com.company.insuranceapp.exception;

public class ResourceExistsException extends RuntimeException {
    public ResourceExistsException(String resource, String field, Object value) {
        super(String.format("%s already exists by %s: %s", resource, field, value));
    }
}