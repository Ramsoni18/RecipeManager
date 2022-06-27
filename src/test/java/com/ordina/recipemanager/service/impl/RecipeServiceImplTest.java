package com.ordina.recipemanager.service.impl;

import com.ordina.recipemanager.Helper;
import com.ordina.recipemanager.dao.RecipeRepository;
import com.ordina.recipemanager.model.Recipe;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecipeServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;

    @InjectMocks
    RecipeServiceImpl recipeServiceImpl;

    @Test
    void givenRecipe_whenSaveRecipe_thenReturnSavedRecipe() {
        Recipe expectedRecipe = Helper.createRecipe();
        when(recipeRepository.save(expectedRecipe)).thenReturn(expectedRecipe);
        Recipe actualRecipe = recipeServiceImpl.saveRecipe(expectedRecipe);
        assertEquals(expectedRecipe, actualRecipe);
    }

    @Test
    void saveRecipes() {
    }

    @Test
    void getRecipes() {
    }

    @Test
    void getRecipeById() {
    }

    @Test
    void deleteRecipe() {
    }

    @Test
    void updateRecipe() {
    }

    @Test
    void findByIsVegetarianEqualsOrderByTitleAsc() {
    }

    @Test
    void findByNoOfServingEqualsOrderById() {
    }

    @Test
    void findByIngredients_NameIsIn_NameIsNotInOrderByTitle() {
    }

    @Test
    void findDistinctByInstructionsContainsAllIgnoreCaseOrderByTitleAsc() {
    }
}