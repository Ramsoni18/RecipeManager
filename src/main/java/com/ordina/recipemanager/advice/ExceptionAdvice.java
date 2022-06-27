package com.ordina.recipemanager.advice;

import java.util.Date;

import com.ordina.recipemanager.exception.ExceptionMessage;
import com.ordina.recipemanager.exception.IngredientNotFoundException;
import com.ordina.recipemanager.exception.NotFoundException;
import com.ordina.recipemanager.exception.RecipeNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    @Value(value = "${data.exception.notFoundMessage}")
    private String notFoundMessage;

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = { NotFoundException.class})
    public ResponseEntity<ExceptionMessage> handleNotFoundException(NotFoundException nfe) {
        logger.error("Not found Exception: ", nfe.getMessage());

        ExceptionMessage message = new ExceptionMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                nfe.getClass().getSimpleName().concat(" : ").concat(notFoundMessage),
                nfe.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    /**
     * This is a handler method to handle invalid input Exceptions thrown by rest service
     * @param ex This parameter holds the type Exception
     * @return ResponseEntity<ErrorMessage> This is the ResponseEntity to be returned if any type Exception is thrown
     */
    @ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<ExceptionMessage> handleGlobalException(Exception ex) {
        logger.error("Unknown Exception: ", ex.getMessage());
        ExceptionMessage message = new ExceptionMessage(
                HttpStatus.EXPECTATION_FAILED.value(),
                new Date(),
                "Unknown Exception",
                ex.getMessage());
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
    }
}
