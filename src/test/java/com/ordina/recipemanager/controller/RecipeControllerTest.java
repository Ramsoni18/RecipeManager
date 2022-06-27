package com.ordina.recipemanager.controller;

import com.ordina.recipemanager.Helper;
import com.ordina.recipemanager.model.Recipe;
import com.ordina.recipemanager.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

}
