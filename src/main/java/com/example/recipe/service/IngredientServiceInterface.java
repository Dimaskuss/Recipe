package com.example.recipe.service;

import com.example.recipe.exception.ValidationException;
import com.example.recipe.model.Ingredient;

import java.util.Collection;

public interface IngredientServiceInterface {
    int addIngredient(Ingredient ingredient) throws ValidationException;

    Ingredient getIngredient(int id);

    Ingredient editIngredient(int id, Ingredient newIngredient);

    boolean deleteIngredient(int id);

    Collection getAllIngredients();


}
