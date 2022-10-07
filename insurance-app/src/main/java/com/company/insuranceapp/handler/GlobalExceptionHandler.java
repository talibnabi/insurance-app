package com.company.insuranceapp.handler;

import com.company.insuranceapp.exception.ResourceNotFoundException;
import com.company.insuranceapp.exception.TokenExpiredException;
import com.company.insuranceapp.model.response.APIError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> fieldErrors = ex.getBindingResult().getFieldErrors().stream().map(field -> field.getField() + ", " + field.getDefaultMessage()).collect(Collectors.toList());
        List<String> globalErrors = ex.getBindingResult().getGlobalErrors().stream().map(field -> field.getObjectName() + ", " + field.getDefaultMessage()).collect(Collectors.toList());
        List<String> errors = new ArrayList<>();
        errors.addAll(globalErrors);
        errors.addAll(fieldErrors);
        APIError response = new APIError(ex.getLocalizedMessage(), BAD_REQUEST.getReasonPhrase(), getPath(request), errors);
        return new ResponseEntity<>(response, BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        APIError response = new APIError(ex.getLocalizedMessage(), BAD_REQUEST.getReasonPhrase(), getPath(request));
        return new ResponseEntity<>(response, BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        APIError response = new APIError(ex.getLocalizedMessage(), BAD_REQUEST.getReasonPhrase(), getPath(request), List.of("Something went wrong"));
        return new ResponseEntity<>(response, BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        APIError error = new APIError(ex.getLocalizedMessage(), BAD_REQUEST.getReasonPhrase(), getPath(request), details);
        return new ResponseEntity<>(error, BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        APIError err = new APIError(ex.getLocalizedMessage(), NOT_FOUND.getReasonPhrase(), getPath(request));
        return new ResponseEntity<>(err, NOT_FOUND);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException ex, WebRequest request) {
        APIError error = new APIError(ex.getLocalizedMessage(), NOT_FOUND.getReasonPhrase(), getPath(request));
        return new ResponseEntity<>(error, NOT_FOUND);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
        APIError error = new APIError(ex.getLocalizedMessage(), UNAUTHORIZED.getReasonPhrase(), getPath(request));
        return new ResponseEntity<>(error, UNAUTHORIZED);
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<Object> handleDisabledException(DisabledException ex, WebRequest request) {
        APIError error = new APIError(ex.getLocalizedMessage(), UNAUTHORIZED.getReasonPhrase(), getPath(request));
        return new ResponseEntity<>(error, UNAUTHORIZED);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<Object> handleTokenExpiredException(TokenExpiredException ex, WebRequest request) {
        APIError error = new APIError(ex.getLocalizedMessage(), NOT_ACCEPTABLE.getReasonPhrase(), getPath(request));
        return new ResponseEntity<>(error, NOT_ACCEPTABLE);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        APIError error = new APIError(ex.getLocalizedMessage(), FORBIDDEN.getReasonPhrase(), getPath(request));
        return new ResponseEntity<>(error, FORBIDDEN);
    }

    private String getPath(WebRequest request) {
        return request.getDescription(false).substring(4);
    }
}
