package com.ordina.recipemanager.dao;

import com.ordina.recipemanager.Helper;
import com.ordina.recipemanager.model.Recipe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class RecipeRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private RecipeRepository recipeRepository;

    @Test
    public void contextLoads() {
        Assertions.assertNotNull(testEntityManager);
    }

    @Test
    void givenRecipe_whenSaveRecipe_thenSavedRecipe() {
        Recipe recipe = Helper.createRecipe();
        Assertions.assertTrue(recipe.getId() == 0, "Recipe Id is 0 before save");
        testEntityManager.persist(recipe);
        Assertions.assertTrue(recipe.getId() != 0, "Recipe Id " + recipe.getId() + " after save");
    }

    @Test
    void givenRecipeAsNull_whenSaveRecipe_thenThrowException() {
        Recipe recipe = null;
        Assertions.assertThrows(java.lang.IllegalArgumentException.class,
                () -> testEntityManager.persist(recipe),
                "Entity Recipe Not found to persist");
    }
}