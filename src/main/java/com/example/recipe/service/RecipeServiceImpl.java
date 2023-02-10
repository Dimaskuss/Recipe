package com.example.recipe.service;

import com.example.recipe.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeServiceInterface {
    private static int id = 1;
    private static Map<Integer, Recipe> recipeMap = new HashMap<>();


    @Override
    public int addRecipe(Recipe recipe) {
        recipeMap.put(id, recipe);
        return id++;
    }

    @Override
    public Recipe getRecipe(int id) {
        if (recipeMap.containsKey(id)) {
            return recipeMap.get(id);
        }
        return null;
    }

    @Override
    public Recipe editRecipe(int id, Recipe newRecipe) {
        if (recipeMap.containsKey(id)) {
            recipeMap.put(id, newRecipe);
            return recipeMap.get(id);
        }
        return null;
    }

    @Override
    public boolean deleteRecipe(int id) {
        for (Recipe recipe : recipeMap.values()) {
            if (recipeMap.containsKey(id)) {
                recipeMap.remove(id);
                return true;
            }

        }
        return false;
    }

    @Override
    public Collection<Recipe> getAllRecipe() {
        if (recipeMap.isEmpty()) {
            return null;
        }
        return recipeMap.values();
    }

    @Override
    public boolean isEmpty() {
        if (recipeMap.isEmpty()) {
            return true;
        }
        return false;
    }
}


