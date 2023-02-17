package com.example.recipe.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileIngredientServiceImpl implements FileIngredientService {

    @Value("${path.to.data.file}")
    private String dataFilePath;
    @Value("${name.of.ingredient.data.file}")
    private String dataIngredientFileName;

    @Override
    public boolean saveToFile(String json) {
        try {
            cleanDataFile();
            Files.writeString(Path.of(dataFilePath, dataIngredientFileName), json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public String readFromFile() {
        try {
            return Files.readString(Path.of(dataFilePath, dataIngredientFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean cleanDataFile() {
        try {
            Files.deleteIfExists(Path.of(dataFilePath, dataIngredientFileName));
            Files.createFile(Path.of(dataFilePath, dataIngredientFileName));
            return true;
        } catch (IOException e) {
            return false;
        }

    }

    @Override
    public void createFile() {

        try {
            if (!Files.exists(Path.of(dataFilePath, dataIngredientFileName))) {
                Files.createFile(Path.of(dataFilePath, dataIngredientFileName));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public File gtDataFile() {
        return new File(dataFilePath + "/" + dataIngredientFileName);

    }


}

