package com.example.recipe.controllers;

import com.example.recipe.service.FileIngredientService;
import com.example.recipe.service.FileRecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/files")
@Tag(name = "Загрузка и выгрузка файлов  ", description = "сохраниние рецепта в виде файла " +
        ", загрузка файлов рецептов и ингридиентов")
public class FileController {

    private final FileRecipeService fileRecipeService;
    private final FileIngredientService fileIngredientService;

    public FileController(FileRecipeService fileRecipeService, FileIngredientService fileIngredientService) {
        this.fileRecipeService = fileRecipeService;
        this.fileIngredientService = fileIngredientService;
    }

    @Operation(summary = "Скачать файл с рецептами")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Файл можно скачать"
            ),

            @ApiResponse(
                    responseCode = "404",
                    description = "Файл нельзы скачать"
            )
    })
    @GetMapping("/export")
    public ResponseEntity<InputStreamResource> downloadDataFile() throws FileNotFoundException {
        File file = fileRecipeService.gtDataFile();

        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Recipe.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @Operation(summary = "Загрузить свой файл с рецептами")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Файл можно загрузить"
            ),

            @ApiResponse(
                    responseCode = "404",
                    description = "Файл нельзя загрузить"
            )
    })
    @PostMapping(value = "/importrecipe", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadRecipeFile(@RequestParam MultipartFile file) {
        fileRecipeService.cleanDataFile();
        File fileData = fileRecipeService.gtDataFile();
        try (BufferedInputStream bis = new BufferedInputStream(file.getInputStream());
             FileOutputStream fos = new FileOutputStream(fileData);
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            byte[] buffer = new byte[1024];
            while (bis.read(buffer) > 0) {
                bos.write(buffer);
            }
            return ResponseEntity.ok().build();


        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }

    @Operation(summary = "Загрузить свой файл с ингредиентами")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Файл можно загрузить"
            ),

            @ApiResponse(
                    responseCode = "404",
                    description = "Файл нельзы загрузить"
            )
    })
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadIngredientFile(@RequestParam MultipartFile file) {
        fileIngredientService.cleanDataFile();
        File fileData = fileIngredientService.gtDataFile();
        try (BufferedInputStream bis = new BufferedInputStream(file.getInputStream());
             FileOutputStream fos = new FileOutputStream(fileData);
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            byte[] buffer = new byte[1024];
            while (bis.read(buffer) > 0) {
                bos.write(buffer);
            }

            return ResponseEntity.ok().build();


        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }
}
