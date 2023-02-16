package com.example.recipe.service;

import java.io.File;

public interface FileRecipeService {
    boolean cleanDataFile();

    void createFile();


    boolean saveToFile(String json);

    String readFromFile();

    File gtDataFile();
}

