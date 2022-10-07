package com.company.insuranceapp.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResponseModel<T> {
    private String message;
    private boolean error;
    private T object;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private final LocalDateTime timestamp;

    public ResponseModel ()
    {
        timestamp = LocalDateTime.now();
    }

    public static <T> ResponseModel<T> success (T object)
    {
        ResponseModel<T> responseModel = new ResponseModel<>();
        responseModel.setObject(object);
        responseModel.setMessage("Success");
        responseModel.setError(false);
        return responseModel;
    }

    public static <T> ResponseModel<T> error (T object, String message)
    {
        ResponseModel<T> responseModel = new ResponseModel<>();
        responseModel.setObject(object);
        responseModel.setMessage(message);
        responseModel.setError(true);
        return responseModel;
    }

    public static <T> ResponseModel<T> notFound ()
    {
        ResponseModel<T> responseModel = new ResponseModel<>();
        responseModel.setMessage("Not found");
        responseModel.setError(true);
        return responseModel;
    }
}
