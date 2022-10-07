package com.company.insuranceapp.security.filter;


import com.company.insuranceapp.model.response.APIError;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@Component
@RequiredArgsConstructor
public class RestAccessDeniedHandler implements AccessDeniedHandler {
    private final ObjectMapper mapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException ex) throws IOException, ServletException {
        log.info("Access denied error : {}", ex.getLocalizedMessage());
        response.sendError(SC_FORBIDDEN);
        response.setContentType(APPLICATION_JSON_VALUE);
        APIError apiError = new APIError(FORBIDDEN.getReasonPhrase(), ex.getLocalizedMessage(), request.getRequestURI());
        OutputStream out = response.getOutputStream();
        mapper.writeValue(out, apiError);
        out.flush();
    }

}
