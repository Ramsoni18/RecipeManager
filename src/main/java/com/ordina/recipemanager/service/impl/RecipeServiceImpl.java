package com.ordina.recipemanager.service.impl;

import com.ordina.recipemanager.dao.RecipeRepository;
import com.ordina.recipemanager.exception.ApplicationException;
import com.ordina.recipemanager.exception.RecipeNotFoundException;
import com.ordina.recipemanager.model.Recipe;
import com.ordina.recipemanager.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedHashSet;
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
            throw new ApplicationException("recipe cannot be null");
        try {
            return recipeRepository.save(recipe);
        } catch (DataAccessException dae) {
            throw new ApplicationException("recipe cannot be saved because of " + dae.getMessage());
        }
    }

    @Override
    public List<Recipe> saveRecipes(List<Recipe> recipes) {
        if (recipes == null)
            throw new ApplicationException("recipe cannot be null");
        if (recipes != null && recipes.isEmpty())
            throw new ApplicationException("recipe cannot be null");
        try {
            return recipeRepository.saveAll(recipes);
        } catch (DataAccessException dae) {
            throw new ApplicationException("recipes cannot be saved because of " + dae.getMessage());
        }
    }

    @Override
    public List<Recipe> getRecipes() {
        try {
            return recipeRepository.findAll();
        } catch (DataAccessException dae) {
            throw new ApplicationException("cannot get recipes because of " + dae.getMessage());
        }
    }

    @Override
    public Recipe getRecipeById(int id) {
        if (id < 0)
            throw new ApplicationException("id cannot be less then 0");
        try {
            return recipeRepository.findById(id).orElseThrow(RecipeNotFoundException::new);
        } catch (DataAccessException dae) {
            throw new ApplicationException("cannot fetch recipe because of " + dae.getMessage());
        }
    }

    @Override
    public void deleteRecipe(int id) {
        if (id < 0)
            throw new IllegalArgumentException("id cannot be less then 0");
        if (recipeRepository.findById(id).isPresent()) {
            try {
                recipeRepository.deleteById(id);
            } catch (DataAccessException dae) {
                throw new ApplicationException("recipe cannot be deleted because of " + dae.getMessage());
            }
        } else {
            throw new RecipeNotFoundException();
        }
    }

    @Override
    public Recipe updateRecipe(Recipe recipe) {
        if (recipe == null)
            throw new ApplicationException("recipe cannot be null");
        Recipe existingRecipe = recipeRepository.findById(recipe.getId()).orElseThrow(RecipeNotFoundException::new);
        existingRecipe.setTitle(recipe.getTitle());
        existingRecipe.setVegetarian(recipe.isVegetarian());
        existingRecipe.setNoOfServing(recipe.getNoOfServing());
        existingRecipe.setIngredients(recipe.getIngredients());
        existingRecipe.setInstructions(recipe.getInstructions());
        try {
            return recipeRepository.save(existingRecipe);
        } catch (DataAccessException dae) {
            throw new ApplicationException("recipe cannot be updated because of " + dae.getMessage());
        }
    }

    @Override
    public List<Recipe> findByIsVegetarianEqualsOrderByTitleAsc(boolean isVegetarian) {
        try {
            return recipeRepository.findByIsVegetarianEqualsOrderByTitleAsc(isVegetarian);
        } catch (DataAccessException dae) {
            throw new ApplicationException("cannot find recipe if vegetarian because of " + dae.getMessage());
        }
    }

    @Override
    public List<Recipe> findByNoOfServingEqualsOrderById(int noOfServing) {
        if (noOfServing < 0)
            throw new ApplicationException("noOfServing cannot be less then 0");
        try {
            return recipeRepository.findByNoOfServingEqualsOrderById(noOfServing);
        } catch (DataAccessException dae) {
            throw new ApplicationException("cannot find recipe based on no of serving parameter because of " + dae.getMessage());
        }
    }

    @Override
    public List<Recipe> findByIngredientsNameIsInNameIsNotInOrderByTitle(Collection<String> includes, Collection<String> excludes) {
        if (includes == null)
            includes = new LinkedHashSet<>();
        if (excludes == null)
            excludes = new LinkedHashSet<>();
        try {
            return recipeRepository.findByIngredientsNameIsInNameIsNotInOrderByTitle(includes, excludes);
        } catch (DataAccessException dae) {
            throw new ApplicationException("cannot find recipe based on ingredients because of " + dae.getMessage());
        }
    }

    @Override
    public List<Recipe> findDistinctByInstructionsContainsAllIgnoreCaseOrderByTitleAsc(String text) {
        if (text != null && !text.equalsIgnoreCase("")) {
            try {
                return recipeRepository.findDistinctByInstructionsContainsAllIgnoreCaseOrderByTitleAsc(text);
            } catch (DataAccessException dae) {
                throw new ApplicationException("cannot find recipe based on text inside instruction because of " + dae.getMessage());
            }
        }
        throw new ApplicationException("cannot find Recipes with empty or null search text");
    }


}
