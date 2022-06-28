package com.ordina.recipemanager.advice;

import com.ordina.recipemanager.exception.ApplicationException;
import com.ordina.recipemanager.exception.ExceptionMessage;
import com.ordina.recipemanager.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class ExceptionAdvice {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    @Value(value = "${data.exception.notFoundMessage}")
    private String notFoundMessage;

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<ExceptionMessage> handleNotFoundException(NotFoundException nfe) {
        logger.error("Not found Exception: ", nfe.getMessage());

        ExceptionMessage message = new ExceptionMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                nfe.getClass().getSimpleName().concat(" : ").concat(notFoundMessage),
                nfe.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
    
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {Exception.class, ApplicationException.class})
    public ResponseEntity<ExceptionMessage> handleGlobalException(Exception ex) {
        logger.error("Unknown Exception: ", ex.getMessage());
        ExceptionMessage message = new ExceptionMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                "Unknown Exception",
                ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }
}
