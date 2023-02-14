package com.example.recipe.service;

import com.example.recipe.model.Ingredient;
import com.example.recipe.model.Recipe;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeServiceInterface {
    private  int id = 1;
    private Map<Integer, Recipe> recipeMap = new HashMap<>();
    private final FileRecipeServiceImpl fileRecipeService;

    public RecipeServiceImpl(FileRecipeServiceImpl fileRecipeService) {
        this.fileRecipeService = fileRecipeService;
    }


    @Override
    public int addRecipe(Recipe recipe) {
        recipeMap.put(id, recipe);
        saveToFile();
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
            saveToFile();
            return recipeMap.get(id);

        }
        return null;
    }

    @Override
    public boolean deleteRecipe(int id) {

        if (recipeMap.containsKey(id)) {
            recipeMap.remove(id);
            saveToFile();
            return true;
        }


        return false;
    }

    @Override
    public Collection<Recipe> getAllRecipe() {

        return recipeMap.values();
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipeMap);
            fileRecipeService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {

        try {
            String json = fileRecipeService.readFromFile();
            recipeMap = new ObjectMapper().readValue(json, new TypeReference<HashMap<Integer, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    @PostConstruct
    private void init() {
        fileRecipeService.createFile();
        addRecipe(new Recipe("Defolt",0,new ArrayList<>(),new ArrayList<>()));
        readFromFile();

    }

}
