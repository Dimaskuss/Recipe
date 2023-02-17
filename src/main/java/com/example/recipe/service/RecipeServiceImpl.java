package com.example.recipe.service;

import com.example.recipe.exception.ValidationException;
import com.example.recipe.model.Ingredient;
import com.example.recipe.model.Recipe;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeServiceInterface {
    private int id = 1;
    private Map<Integer, Recipe> recipeMap = new HashMap<>();
    private final FileRecipeService fileRecipeService;
    private ValidationService validationService;


    public RecipeServiceImpl(FileRecipeService fileRecipeService) {
        this.fileRecipeService = fileRecipeService;
    }


    @Override
    public Integer addRecipe(Recipe recipe) throws ValidationException {
        if (!validationService.validate(recipe)) {
            throw new ValidationException(recipe.toString());
        }
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
        if (recipeMap.containsKey(id) && validationService.validate(newRecipe)) {
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
            e.printStackTrace();
        }
    }

    private void readFromFile() {

        try {
            String json = fileRecipeService.readFromFile();
            recipeMap = new ObjectMapper().readValue(json, new TypeReference<HashMap<Integer, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    @PostConstruct
    private void init() {

//        fileRecipeService.createFile();
//        addRecipe(new Recipe("Defolt", 0, new ArrayList<>(), new HashMap<>()));
        readFromFile();

    }

    @Override
    public Path createRecipeBook() throws IOException {
        Path path = fileRecipeService.createTempFile("recipeBook");
        for (Recipe recipe : recipeMap.values()) {
            try (Writer writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
                writer.append(recipe.getName() + "\n\n Время приготовления : " + recipe.getCookingTime() +
                        " мин. \n\n Список ингредиентов : \n\n");
                for (Ingredient ing : recipe.getIngredients()) {
                    writer.append(ing.toString());
                    writer.append("\n");
                }
                writer.append("\n");
                writer.append("Инструкция приготовления : \n");
                for (int i = 1; i <= recipe.getSteps().keySet().size(); i++) {
                    writer.append(i + " " + recipe.getSteps().get(i));
                    writer.append("\n");
                }
                writer.append("\n");


            }
        }


        return path;
    }
}