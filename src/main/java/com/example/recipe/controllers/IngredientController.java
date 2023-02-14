package com.example.recipe.controllers;

import com.example.recipe.model.Ingredient;
import com.example.recipe.service.IngredientServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredient")
@Tag(name = "API по работе с ингредиентами ", description = "CRUD операции для ингредиентов")
public class IngredientController {

    private final IngredientServiceInterface ingredientServiceInterface;

    public IngredientController(IngredientServiceInterface ingredientServiceInterface) {
        this.ingredientServiceInterface = ingredientServiceInterface;
    }

    @Operation(summary = "Добавление игредиента")
    @PostMapping
    public ResponseEntity<Integer> addIngredient(@RequestBody Ingredient ingredient) {
        int id = ingredientServiceInterface.addIngredient(ingredient);
        return ResponseEntity.ok(id);
    }

    @Operation(summary = "Вывод игредиента по номеру")
    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredientForId(@PathVariable int id) {
        Ingredient ingredient = ingredientServiceInterface.getIngredient(id);
        if (ingredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }

    @Operation(summary = "Изменение игредиента по номеру")
    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> editIngredient(@PathVariable int id, @RequestBody Ingredient newIngredient) {
        Ingredient ingredient = ingredientServiceInterface.editIngredient(id, newIngredient);
        if (ingredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }

    @Operation(summary = "Удаление игредиента")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable Integer id) {

        if (ingredientServiceInterface.deleteIngredient(id)) {
            return ResponseEntity.ok().build();
        } else {

            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Показать все игредиенты")
    @GetMapping("/")
    public ResponseEntity getAllIngredients() {

        return ResponseEntity.ok(ingredientServiceInterface.getAllIngredients());
    }


}
