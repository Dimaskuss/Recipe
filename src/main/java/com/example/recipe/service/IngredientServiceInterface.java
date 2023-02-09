package com.example.recipe.service;

import com.example.recipe.model.Ingredient;

public interface IngredientServiceInterface {
    void addIngredient(Ingredient ingredient);

    Ingredient getIngredient(int id);
}
