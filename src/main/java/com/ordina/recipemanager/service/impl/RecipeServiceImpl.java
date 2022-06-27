package com.ordina.recipemanager.service.impl;

import com.ordina.recipemanager.dao.RecipeRepository;
import com.ordina.recipemanager.exception.RecipeNotFoundException;
import com.ordina.recipemanager.model.Recipe;
import com.ordina.recipemanager.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Recipe saveRecipe(Recipe recipe) {
        if (recipe == null)
            throw new IllegalArgumentException("recipe cannot be null");
        try {
            return recipeRepository.save(recipe);
        }catch (DataAccessException dae)
        {
            //TODO Exception throw
        }
    }

    @Override
    public List<Recipe> saveRecipes(List<Recipe> recipes) {
        if (recipes == null && recipes.size()<=0)
            throw new IllegalArgumentException("recipe cannot be null");
        return recipeRepository.saveAll(recipes);
    }

    @Override
    public List<Recipe> getRecipes() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe getRecipeById(int id) {
        if (id < 0)
            throw new IllegalArgumentException("id cannot be less then 0")
        return recipeRepository.findById(id).orElseThrow(RecipeNotFoundException::new);
    }

    @Override
    public void deleteRecipe(int id) {
        if (id < 0)
            throw new IllegalArgumentException("id cannot be less then 0")
        if (recipeRepository.findById(id).isPresent()) {
            recipeRepository.deleteById(id);
        } else {
            throw new RecipeNotFoundException();
        }
    }

    @Override
    public Recipe updateRecipe(Recipe recipe) {
        if (recipe == null)
            throw new IllegalArgumentException("recipe cannot be null");
        Recipe existingRecipe = recipeRepository.findById(recipe.getId()).orElseThrow(RecipeNotFoundException::new);
        existingRecipe.setTitle(recipe.getTitle());
        existingRecipe.setVegetarian(recipe.isVegetarian());
        existingRecipe.setNoOfServing(recipe.getNoOfServing());
        existingRecipe.setIngredients(recipe.getIngredients());
        existingRecipe.setInstructions(recipe.getInstructions());
        return recipeRepository.save(existingRecipe);
    }

    @Override
    public List<Recipe> findByIsVegetarianEqualsOrderByTitleAsc(boolean isVegetarian) {
        return recipeRepository.findByIsVegetarianEqualsOrderByTitleAsc(isVegetarian);
    }

    @Override
    public List<Recipe> findByNoOfServingEqualsOrderById(int noOfServing) {
        return recipeRepository.findByNoOfServingEqualsOrderById(noOfServing);
    }

    @Override
    public List<Recipe> findByIngredients_NameIsIn_NameIsNotInOrderByTitle(Collection<String> includes, Collection<String> excludes) {
        return recipeRepository.findByIngredients_NameIsIn_NameIsNotInOrderByTitle(includes, excludes);
    }

    @Override
    public List<Recipe> findDistinctByInstructionsContainsAllIgnoreCaseOrderByTitleAsc(String text) {
        return recipeRepository.findDistinctByInstructionsContainsAllIgnoreCaseOrderByTitleAsc(text);
    }


}
