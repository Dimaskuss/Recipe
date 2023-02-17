package com.example.recipe.service;

import com.example.recipe.exception.ValidationException;
import com.example.recipe.model.Recipe;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;

public interface RecipeServiceInterface {


    Integer addRecipe(Recipe recipe) throws ValidationException;

    Recipe getRecipe(int id);

    Recipe editRecipe(int id, Recipe newRecipe);

    boolean deleteRecipe(int id);

    Collection<Recipe> getAllRecipe();


    Path createRecipeBook() throws IOException;
}
