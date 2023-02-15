package com.example.recipe.controllers;

import com.example.recipe.exception.NullException;
import com.example.recipe.model.Ingredient;
import com.example.recipe.model.Recipe;
import com.example.recipe.service.RecipeServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
@RestController
@RequestMapping("/recipe")
@Tag(name = "API по работе с рецептами ", description = "CRUD операции для рецептов")
public class RecipeController {
    private final RecipeServiceInterface recipeServiceInterface;

    public RecipeController(RecipeServiceInterface recipeServiceInterface) {
        this.recipeServiceInterface = recipeServiceInterface;
    }
    @Operation(summary = "Добавление рецепта")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт добавлен"
            ),

            @ApiResponse(
                    responseCode = "404",
                    description = "Введено неправильное значение"
            )
    })
    @PostMapping

    public ResponseEntity<Integer> addRecipe(@RequestBody Recipe recipe) {
        int id = recipeServiceInterface.addRecipe(recipe);
        return ResponseEntity.ok(id);
    }
    @Operation(summary = "Получение рецепта по номеру")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт найден"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Неправильный номер рецепта"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Введено неправильное значение"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeForId(@PathVariable int id) {
        Recipe recipe = recipeServiceInterface.getRecipe(id);
        if (ObjectUtils.isEmpty(recipe)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }
    @Operation(summary = "Исправление/замена рецепта")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт изменен"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Неправильный номер рецепта"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Введено неправильное значение"
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<Recipe> editRecipe(@PathVariable int id, @RequestBody Recipe newRecipe) {
        Recipe recipe = recipeServiceInterface.editRecipe(id, newRecipe);
        if (ObjectUtils.isEmpty(recipe) ) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }
    @Operation(summary = "Удаление рецепта")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт удален"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Неправильный номер рецепта"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Введено неправильное значение"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Integer id) {

        if (recipeServiceInterface.deleteRecipe(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(summary = "Получение всех рецептов")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепты найдены"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Введено неправильное значение"
            )
    })
    @GetMapping("/")
    public ResponseEntity<Collection<Recipe>> getAllRecipe() {

            return ResponseEntity.ok(recipeServiceInterface.getAllRecipe());
        }



}


