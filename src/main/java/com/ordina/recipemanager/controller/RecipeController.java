package com.ordina.recipemanager.controller;

import com.ordina.recipemanager.model.Recipe;
import com.ordina.recipemanager.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("recipe-manager/rest/api/v1/recipe")
public class RecipeController {
    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping(value = "/getRecipes")
    public List<Recipe> getRecipes() {
        return recipeService.getRecipes();
    }

    @GetMapping("/getRecipeById/{id}")
    public Recipe getRecipeById(@PathVariable int id) {
        return recipeService.getRecipeById(id);
    }

    @PostMapping(value = "/addRecipe", consumes = "application/json", produces = "application/json")
    public Recipe addRecipe(@RequestBody @Valid Recipe recipe) {
        return recipeService.saveRecipe(recipe);
    }

    @PostMapping("/addRecipes")
    public List<Recipe> addRecipes(@RequestBody @Valid List<Recipe> recipes) {
        return recipeService.saveRecipes(recipes);
    }

    @DeleteMapping("/deleteRecipe/{id}")
    public void deleteRecipe(@PathVariable int id) {
        recipeService.deleteRecipe(id);
    }

    @PutMapping("/updateRecipe")
    public Recipe updateRecipe(@RequestBody Recipe recipe) {
        return recipeService.updateRecipe(recipe);
    }

    @GetMapping("/search/findRecipeIsVegetarian/{isVegetarian}")
    public List<Recipe> findRecipeIsVegetarian(@PathVariable boolean isVegetarian) {
        return recipeService.findByIsVegetarianEqualsOrderByTitleAsc(isVegetarian);
    }

    @GetMapping("/search/findRecipeByNoOfServing/{noOfServing}")
    public List<Recipe> findRecipeByNoOfServing(@PathVariable int noOfServing) {
        return recipeService.findByNoOfServingEqualsOrderById(noOfServing);
    }

    @GetMapping("/search/findRecipeByIngredientsIncludesOrExcludes/")
    public List<Recipe> findRecipeByIngredientsIncludesOrExcludes(@RequestParam @Valid Collection<String> includes, @RequestParam @Valid Collection<String> excludes) {
        return recipeService.findByIngredientsNameIsInNameIsNotInOrderByTitle(includes, excludes);
    }

    @GetMapping("/search/findRecipeByInstructionContainsText/{text}")
    public List<Recipe> findRecipeByInstructionContainsText(@PathVariable String text) {
        return recipeService.findDistinctByInstructionsContainsAllIgnoreCaseOrderByTitleAsc(text);
    }
}
