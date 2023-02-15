package com.example.recipe.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
@Service
public class FileRecipeServiceImpl implements FileRecipeService {
    @Value("${path.to.data.file}")
    private String dataFilePath;
    @Value("${name.of.recipe.data.file}")
    private String dataRecipeFileName;
    @Override
    public boolean saveToFile(String json) {


        try {
            cleanDataFile();
            Files.writeString(Path.of(dataFilePath,dataRecipeFileName), json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    @Override
    public String readFromFile() {
        try {
            return Files.readString(Path.of(dataFilePath, dataRecipeFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean cleanDataFile() {
        try {
            Files.deleteIfExists(Path.of(dataFilePath,dataRecipeFileName));
            Files.createFile(Path.of(dataFilePath,dataRecipeFileName));
            return true;
        } catch (IOException e) {
            return false;
        }

    }
@Override
    public void createFile(){

        try {
            if(!Files.exists(Path.of(dataFilePath,dataRecipeFileName))) {
                Files.createFile(Path.of(dataFilePath, dataRecipeFileName));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



