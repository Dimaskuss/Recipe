package com.example.recipe.service;

public interface FileIngredientService {
    boolean saveToFile(String json);

    String readFromFile();

    void createFile();
}
