package com.ordina.recipemanager;

import com.ordina.recipemanager.model.Ingredient;
import com.ordina.recipemanager.model.Recipe;

import java.util.Arrays;
import java.util.List;

public class Helper
{
    public static Ingredient createIngredient(){
        return Ingredient.builder().name("Mint").build();
    }

    public static List<Ingredient> createIngredients(){
        return List.of(Ingredient.builder().name("Olive").build()
                , Ingredient.builder().name("Strawberry").build()
                , Ingredient.builder().name("Jack fruit").build()
                , Ingredient.builder().name("Beef").build());
    }

    public static Recipe createRecipe(){
        List<Ingredient> ingredients = List.of(Ingredient.builder().name("Baking Powder").build()
                , Ingredient.builder().name("Kosher Salt").build()
                , Ingredient.builder().name("Sugar").build()
                , Ingredient.builder().name("Baking Soda").build());

        return Recipe.builder()
                .title("Sandpiper - Cheesy Biscuits")
                .isVegetarian(Boolean.TRUE)
                .noOfServing(3)
                .ingredients(ingredients)
                .instructions("Preheat oven to 425°F. Mix baking powder, salt, sugar, baking soda, and 3½ cups flour in a food processor to combine. Add chilled butter and pulse until the largest pieces of butter are the size of a pea.")
                .build();
    }

    public static List<Recipe> createRecipes(){
        List<Ingredient> ingredientsForRecipe1 = List.of(Ingredient.builder().name("Baking Powder").build()
                , Ingredient.builder().name("Kosher Salt").build()
                , Ingredient.builder().name("Sugar").build()
                , Ingredient.builder().name("Baking Soda").build());

        List<Ingredient> ingredientsForRecipe2 = List.of(Ingredient.builder().name("Lemon").build()
                , Ingredient.builder().name("Black pepper").build()
                , Ingredient.builder().name("Salt").build()
                , Ingredient.builder().name("Olive Oil").build());

        Recipe recipe1 = Recipe.builder()
                .title("Sandpiper - Cheesy Biscuits")
                .isVegetarian(Boolean.TRUE)
                .noOfServing(3)
                .ingredients(ingredientsForRecipe1)
                .instructions("Preheat oven to 425°F. Mix baking powder, salt, sugar, baking soda, and 3½ cups flour in a food processor to combine. Add chilled butter and pulse until the largest pieces of butter are the size of a pea.")
                .build();

        Recipe recipe2 = Recipe.builder()
                .title("Ranch 45 - Lemon Vinaigrette")
                .isVegetarian(Boolean.TRUE)
                .noOfServing(2)
                .ingredients(ingredientsForRecipe2)
                .instructions("Combine all ingredients in a mixing bowl, start with a few pinches of salt and pepper at first. Taste and adjust seasoning as necessary.")
                .build();

        return Arrays.asList(recipe1,recipe2);
    }
}
