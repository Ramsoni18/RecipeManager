package com.ordina.recipemanager.exception;

public class IngredientNotFoundException extends NotFoundException{

    public IngredientNotFoundException(){
    }

    public IngredientNotFoundException(String message){
        super(message);
    }
}
