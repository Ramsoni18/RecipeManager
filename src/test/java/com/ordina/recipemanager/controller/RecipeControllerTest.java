package com.ordina.recipemanager.controller;

import com.ordina.recipemanager.Helper;
import com.ordina.recipemanager.model.Recipe;
import com.ordina.recipemanager.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RecipeControllerTest {

    @Mock
    private RecipeService recipeService;

    @InjectMocks
    private RecipeController recipeController;

    @Test
    void givenNoFilterCriteria_whenGetRecipes_thenReturnAllRecipes() {
        List<Recipe> expectedResponse = Helper.createRecipes();
        when(recipeService.getRecipes()).thenReturn(expectedResponse);
        List<Recipe> actualResponse = recipeController.getRecipes();
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void givenIdOfRecipe_whenGetRecipeById_thenReturnRecipeForProvidedId() {
        Recipe expectedResponse = Helper.createRecipe();
        when(recipeService.getRecipeById(0)).thenReturn(expectedResponse);
        Recipe actualResponse = recipeController.getRecipeById(0);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void givenRecipe_whenAddRecipe_ThenReturnNewlyCreatedRecipe() {
        Recipe expectedResponse = Helper.createRecipe();
        when(recipeService.saveRecipe(expectedResponse)).thenReturn(expectedResponse);
        Recipe actualResponse = recipeController.addRecipe(expectedResponse);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void givenListOfRecipe_whenAddRecipes_ThenReturnNewlyCreatedRecipeList() {
        List<Recipe> expectedResponse = Helper.createRecipes();
        when(recipeService.saveRecipes(expectedResponse)).thenReturn(expectedResponse);
        List<Recipe> actualResponse = recipeController.addRecipes(expectedResponse);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void givenIdOfRecipe_whenDeleteRecipe_ThenReturnNothing() {
        Recipe expectedResponse = Helper.createRecipe();
        recipeService.deleteRecipe(0);
        recipeController.deleteRecipe(0);
        assertTrue(true);
    }

    @Test
    void givenUpdatedRecipe_whenUpdateRecipe_ThenUpdateRecipeParameters() {
        Recipe expectedResponse = Helper.createRecipe();
        when(recipeService.updateRecipe(expectedResponse)).thenReturn(expectedResponse);
        Recipe actualResponse = recipeController.updateRecipe(expectedResponse);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void givenIsVegetarianTrue_whenFindRecipeIsVegetarian_ThenReturnListOfVegetarianRecipes() {
        List<Recipe> expectedResponse = Helper.createRecipes();
        when(recipeService.findByIsVegetarianEqualsOrderByTitleAsc(true)).thenReturn(expectedResponse);
        List<Recipe> actualResponse = recipeController.findRecipeIsVegetarian(true);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void givenNoOfServing_whenFindRecipeByNoOfServing_ThenReturnListOfFilteredRecipes() {
        List<Recipe> expectedResponse = Helper.createRecipes();
        when(recipeService.findByNoOfServingEqualsOrderById(2)).thenReturn(expectedResponse);
        List<Recipe> actualResponse = recipeController.findRecipeByNoOfServing(2);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void givenIncludeAndExcludeIngredients_whenFindRecipeByIngredientsIncludesOrExcludes_ThenReturnListOfFilteredRecipes() {
        List<Recipe> expectedResponse = Helper.createRecipes();
        List<String> includes = Arrays.asList("Butter");
        List<String> excludes = Arrays.asList("Cooking Oil");
        when(recipeService.findByIngredients_NameIsIn_NameIsNotInOrderByTitle(includes, excludes)).thenReturn(expectedResponse);
        List<Recipe> actualResponse = recipeController.findRecipeByIngredientsIncludesOrExcludes(includes, excludes);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void givenTextAsfilterCriteria_whenFindRecipeByInstructionContainsText_ThenReturnListOfFilteredRecipes() {
        List<Recipe> expectedResponse = Helper.createRecipes();
        String textToSearch = "Preheat oven";
        when(recipeService.findDistinctByInstructionsContainsAllIgnoreCaseOrderByTitleAsc(textToSearch)).thenReturn(expectedResponse);
        List<Recipe> actualResponse = recipeController.findRecipeByInstructionContainsText(textToSearch);
        assertEquals(expectedResponse, actualResponse);
    }
}