package com.example.recipe.service;

import com.example.recipe.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeServiceInterface {
    private static int id = 1;
    private  final Map<Integer, Recipe> RECIPE_MAP = new HashMap<>();

    @Override
    public int addRecipe(Recipe recipe) {
        RECIPE_MAP.put(id, recipe);
        return id++;
    }

    @Override
    public Recipe getRecipe(int id) {
        if (RECIPE_MAP.containsKey(id)) {
            return RECIPE_MAP.get(id);
        }
        return null;
    }

    @Override
    public Recipe editRecipe(int id, Recipe newRecipe) {
        if (RECIPE_MAP.containsKey(id)) {
            RECIPE_MAP.put(id, newRecipe);
            return RECIPE_MAP.get(id);
        }
        return null;
    }

    @Override
    public boolean deleteRecipe(int id) {
        for (Recipe recipe : RECIPE_MAP.values()) {
            if (RECIPE_MAP.containsKey(id)) {
                RECIPE_MAP.remove(id);
                return true;
            }

        }
        return false;
    }

    @Override
    public Collection<Recipe> getAllRecipe() {
        if (RECIPE_MAP.isEmpty()) {
            return null;
        }
        return RECIPE_MAP.values();
    }

    @Override
    public boolean isEmpty() {
        if (RECIPE_MAP.isEmpty()) {
            return true;
        }
        return false;
    }
}


