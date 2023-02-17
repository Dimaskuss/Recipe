package com.example.recipe.service;


import com.example.recipe.exception.ValidationException;
import com.example.recipe.model.Ingredient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class IngredientServiceImpl implements IngredientServiceInterface {
    private Map<Integer, Ingredient> ingredientMap = new HashMap<>();
    private int id = 1;

    private final FileIngredientService filesServiceInterface;
    private final ValidationService validationService;


    @Override
    public int addIngredient(Ingredient ingredient) throws ValidationException {
        if (!validationService.validate(ingredient)) {
            throw new ValidationException(ingredient.toString());
        }
        ingredientMap.put(id, ingredient);
        saveToFile();
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
        if (ingredientMap.containsKey(id) && validationService.validate(newIngredient)) {
            ingredientMap.put(id, newIngredient);
            saveToFile();
            return ingredientMap.get(id);
        }
        return null;
    }

    @Override
    public boolean deleteIngredient(int id) {

        if (ingredientMap.containsKey(id)) {
            ingredientMap.remove(id);
            saveToFile();
            return true;
        }


        return false;
    }

    @Override
    public Collection<Ingredient> getAllIngredients() {

        return ingredientMap.values();
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredientMap);
            filesServiceInterface.saveToFile(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void readFromFile() {

        try {
            String json = filesServiceInterface.readFromFile();
            ingredientMap = new ObjectMapper().readValue(json, new TypeReference<HashMap<Integer, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    @PostConstruct
    private void init() {
//        filesServiceInterface.createFile();
//        addIngredient(new Ingredient("Defolt", 0, "Defolt"));
        readFromFile();

    }

}
