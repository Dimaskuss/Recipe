package com.example.recipe.service;

import java.io.File;
import java.nio.file.Path;

public interface FileRecipeService {
    boolean cleanDataFile();

    void createFile();


    boolean saveToFile(String json);

    String readFromFile();

    File gtDataFile();

    Path createTempFile(String suffix);
}

