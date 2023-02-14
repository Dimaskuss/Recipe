package com.example.recipe.controllers;

import com.example.recipe.exception.NullException;
import com.example.recipe.model.Ingredient;
import com.example.recipe.model.Recipe;
import com.example.recipe.service.RecipeServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    @PostMapping
    public ResponseEntity<Integer> addRecipe(@RequestBody Recipe recipe) {
        int id = recipeServiceInterface.addRecipe(recipe);
        return ResponseEntity.ok(id);
    }
    @Operation(summary = "Получение рецепта по номеру")
    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeForId(@PathVariable int id) {
        Recipe recipe = recipeServiceInterface.getRecipe(id);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }
    @Operation(summary = "Исправление/замена рецепта")
    @PutMapping("/{id}")
    public ResponseEntity<Recipe> editRecipe(@PathVariable int id, @RequestBody Recipe newRecipe) {
        Recipe recipe = recipeServiceInterface.editRecipe(id, newRecipe);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }
    @Operation(summary = "Удаление рецепта")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Integer id) {

        if (recipeServiceInterface.deleteRecipe(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(summary = "Получение всех рецептов")
    @GetMapping("/")
    public ResponseEntity<Collection<Recipe>> getAllRecipe() {

            return ResponseEntity.ok(recipeServiceInterface.getAllRecipe());
        }



}


