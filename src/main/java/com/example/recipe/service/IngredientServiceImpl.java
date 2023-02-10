package com.example.recipe.service;


import com.example.recipe.model.Ingredient;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientServiceInterface {
    private static final Map<Integer, Ingredient> INGREDIENT_MAP = new HashMap<>();

    private int id = 1;

    @Override
    public int addIngredient(Ingredient ingredient) {
        INGREDIENT_MAP.put(id, ingredient);
        return id++;
    }

    @Override
    public Ingredient getIngredient(int id) {
        if (INGREDIENT_MAP.containsKey(id)) {
            return INGREDIENT_MAP.get(id);
        }
        return null;
    }

    @Override
    public Ingredient editIngredient(int id, Ingredient newIngredient) {
        if (INGREDIENT_MAP.containsKey(id)) {
            INGREDIENT_MAP.put(id, newIngredient);
            return INGREDIENT_MAP.get(id);
        }
        return null;
    }

    @Override
    public boolean deleteIngredient(int id) {
        for (Ingredient ingredient : INGREDIENT_MAP.values()) {
            if (INGREDIENT_MAP.containsKey(id)) {
                INGREDIENT_MAP.remove(id);
                return true;
            }

        }
        return false;
    }

    @Override
    public Collection<Ingredient> getAllIngredients() {
        if (INGREDIENT_MAP.isEmpty()) {
            return null;
        }
        return INGREDIENT_MAP.values();
    }

    @Override
    public boolean isEmpty() {
        if (INGREDIENT_MAP.isEmpty()) {
            return true;
        }
        return false;
    }
}
