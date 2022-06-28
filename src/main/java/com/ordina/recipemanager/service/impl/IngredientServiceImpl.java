package com.ordina.recipemanager.service.impl;

import com.ordina.recipemanager.dao.IngredientRepository;
import com.ordina.recipemanager.exception.IngredientNotFoundException;
import com.ordina.recipemanager.exception.RecipeNotFoundException;
import com.ordina.recipemanager.model.Ingredient;
import com.ordina.recipemanager.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {

    final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient saveIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    @Override
    public List<Ingredient> saveIngredients(List<Ingredient> ingredients) {
        return ingredientRepository.saveAll(ingredients);
    }

    @Override
    public List<Ingredient> getIngredients() {
        return ingredientRepository.findAll();
    }

    @Override
    public Ingredient getIngredientById(int id) {
        if (ingredientRepository.findById(id).isEmpty())
            throw new IngredientNotFoundException();
        return ingredientRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteIngredient(int id) {
        if (ingredientRepository.findById(id).isPresent())
            ingredientRepository.deleteById(id);
        throw new RecipeNotFoundException();
    }

    @Override
    public Ingredient updateIngredient(Ingredient ingredient) {
        Ingredient existingIngredient = ingredientRepository.findById(ingredient.getId()).orElseThrow(IngredientNotFoundException::new);
        existingIngredient.setName(ingredient.getName());
        return ingredientRepository.save(existingIngredient);
    }
}
