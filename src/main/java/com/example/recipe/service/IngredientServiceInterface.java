package com.example.recipe.service;

import com.example.recipe.model.Ingredient;

import java.util.Collection;

public interface IngredientServiceInterface {
    int addIngredient(Ingredient ingredient);

    Ingredient getIngredient(int id);

    Ingredient editIngredient(int id, Ingredient newIngredient);

    boolean deleteIngredient(int id);

    Collection getAllIngredients();

    boolean isEmpty();
}
