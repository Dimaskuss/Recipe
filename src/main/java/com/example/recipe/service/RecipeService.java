package com.example.recipe.service;

import com.example.recipe.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeService implements RecipeServiceInterface {
    private static int id = 0;
    private static Map<Integer, Recipe> recipeMap = new HashMap<>();

    @Override
    public void addRecipe(Recipe recipe) {
        recipeMap.put(id++, recipe);

    }

    @Override
    public Recipe getRecipe(int id) {
//        if( recipeMap.containsKey(id) ){
//            System.out.println("такого рецепта нет");
//        }
        return recipeMap.get(id);

    }
}


