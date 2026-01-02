package com.servicedesk.lite.config;

import com.servicedesk.lite.auth.EmailAlreadyRegisteredException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, Object> handleEmailExists(EmailAlreadyRegisteredException ex) {
        return Map.of(
                "timestamp", OffsetDateTime.now().toString(),
                "status", 409,
                "error", "CONFLICT",
                "message", ex.getMessage()
        );
    }
}

