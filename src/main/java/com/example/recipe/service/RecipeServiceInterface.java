package com.example.recipe.service;

import com.example.recipe.model.Recipe;

public interface RecipeServiceInterface {
    void addRecipe(Recipe recipe);

    Recipe getRecipe(int id);
}
