package com.mendel.challenge.config;

import com.mendel.challenge.exception.ApiError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class ControllerExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(ControllerExceptionHandler.class);
    private final String INTERNAL_ERROR = "INTERNAL_SERVER_ERROR";

    private void logMessage(HttpStatus httpStatus, Exception exception){
        String format = "Type: %s - Message: %s - StackTrace:  %s";

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        exception.printStackTrace(printWriter);
        stringWriter.toString();

        log.error(String.format(format,HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), stringWriter.toString()));
    }
    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<ApiError> handleUnknownException(Exception exception) {

        this.logMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception);

        ApiError apiError = ApiError.builder()
                .error(INTERNAL_ERROR)
                .message(exception.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();

        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }
}
