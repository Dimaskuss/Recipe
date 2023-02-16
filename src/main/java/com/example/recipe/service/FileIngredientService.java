package com.example.recipe.service;

import java.io.File;

public interface FileIngredientService {

    boolean saveToFile(String json);

    String readFromFile();

    boolean cleanDataFile();

    void createFile();

    File gtDataFile();
}
