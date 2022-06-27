package com.ordina.recipemanager.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionMessage {
    private int statusCode;
    private Date timestamp;
    private String message;
    private String description;
}
