package com.example.recipe.service;


import com.example.recipe.model.Ingredient;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientServiceInterface {
    private  final Map<Integer, Ingredient> ingredientMap = new HashMap<>();
    private int id = 1;

    @Override
    public int addIngredient(Ingredient ingredient) {
        ingredientMap.put(id, ingredient);
        return id++;
    }

    @Override
    public Ingredient getIngredient(int id) {
        if (ingredientMap.containsKey(id)) {
            return ingredientMap.get(id);
        }
        return null;
    }

    @Override
    public Ingredient editIngredient(int id, Ingredient newIngredient) {
        if (ingredientMap.containsKey(id)) {
            ingredientMap.put(id, newIngredient);
            return ingredientMap.get(id);
        }
        return null;
    }

    @Override
    public boolean deleteIngredient(int id) {

            if (ingredientMap.containsKey(id)) {
                ingredientMap.remove(id);
                return true;
            }


        return false;
    }

    @Override
    public Collection<Ingredient> getAllIngredients() {

        return ingredientMap.values();
    }



}
