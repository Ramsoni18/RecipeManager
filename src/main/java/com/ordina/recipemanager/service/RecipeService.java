package com.ordina.recipemanager.service;

import com.ordina.recipemanager.model.Recipe;

import java.util.Collection;
import java.util.List;

public interface RecipeService  {
    public Recipe saveRecipe(Recipe recipe);

    public List<Recipe> saveRecipes(List<Recipe> recipes);

    public List<Recipe> getRecipes();

    public Recipe getRecipeById(int id);

    public void deleteRecipe(int id);

    public Recipe updateRecipe(Recipe recipe);

    public List<Recipe> findByIsVegetarianEqualsOrderByTitleAsc(boolean isVegetarian);

    public List<Recipe> findByNoOfServingEqualsOrderById(int noOfServing);

    public List<Recipe> findByIngredients_NameIsIn_NameIsNotInOrderByTitle(Collection<String> includes, Collection<String> excludes);

    public List<Recipe> findDistinctByInstructionsContainsAllIgnoreCaseOrderByTitleAsc(String text);
}
