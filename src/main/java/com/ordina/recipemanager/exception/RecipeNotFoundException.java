package com.ordina.recipemanager.exception;

public class RecipeNotFoundException extends NotFoundException{

    public RecipeNotFoundException(){
    }

    public RecipeNotFoundException(String message){
        super(message);
    }

}
