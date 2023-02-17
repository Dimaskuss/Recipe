package com.example.recipe.service;

import com.example.recipe.model.Ingredient;
import com.example.recipe.model.Recipe;

public interface ValidationService {
    boolean validate(Recipe recipe);

    boolean validate(Ingredient ingredient);
}
