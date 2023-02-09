package com.example.recipe.service;


import com.example.recipe.model.Ingredient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientService implements IngredientServiceInterface {
private static Map<Integer,Ingredient> ingredientMap = new HashMap<>();

private int id=0;

@Override
public void addIngredient(Ingredient ingredient){
    ingredientMap.put(id++,ingredient);
}
@Override
public Ingredient getIngredient(int id){
    return ingredientMap.get(id);
}

}

