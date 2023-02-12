package com.example.recipe.service;

import com.example.recipe.exception.NullException;
import com.example.recipe.model.Ingredient;
import com.example.recipe.model.Recipe;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

public interface RecipeServiceInterface {


    int addRecipe(Recipe recipe);

    Recipe getRecipe(int id);

    Recipe editRecipe(int id, Recipe newRecipe);

    boolean deleteRecipe(int id);

    Collection<Recipe> getAllRecipe();




}
