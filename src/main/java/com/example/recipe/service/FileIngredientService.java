package com.example.recipe.service;

import java.io.File;
import java.nio.file.Path;

public interface FileIngredientService {

    boolean saveToFile(String json);

    String readFromFile();

    boolean cleanDataFile();

    void createFile();

    File gtDataFile();


}
