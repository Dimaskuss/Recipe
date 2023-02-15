package com.example.recipe.service;

public interface FileRecipeService {
    void createFile();


    boolean saveToFile(String json);

    String readFromFile();
}

