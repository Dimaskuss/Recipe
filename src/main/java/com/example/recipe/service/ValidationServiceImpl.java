package com.example.recipe.service;

import com.example.recipe.model.Ingredient;
import com.example.recipe.model.Recipe;

public class ValidationServiceImpl implements ValidationService {
    @Override
    public boolean validate(Recipe recipe) {
        return recipe != null
                && !recipe.getName().isEmpty()
                && recipe.getCookingTime() > 0
                && recipe.getSteps() != null
                && !recipe.getSteps().isEmpty()
                && recipe.getIngredients() != null
                && !recipe.getIngredients().isEmpty();
    }

    @Override
    public boolean validate(Ingredient ingredient) {
        return ingredient != null
                && !ingredient.getName().isEmpty()
                && !ingredient.getMeasureUnit().isEmpty();

    }
}
