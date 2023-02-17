package com.example.recipe.service;

public interface FileService {
    boolean saveToFile(String json);

    String readFromFile();
}
