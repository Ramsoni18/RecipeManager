package com.ordina.recipemanager.service;

import com.ordina.recipemanager.model.Ingredient;

import java.util.List;

public interface IngredientService {

    public Ingredient saveIngredient(Ingredient ingredient);

    public List<Ingredient> saveIngredients(List<Ingredient> ingredients);

    public List<Ingredient> getIngredients();

    public Ingredient getIngredientById(int id);

    public void deleteIngredient(int id);

    public Ingredient updateIngredient(Ingredient ingredient);
}
