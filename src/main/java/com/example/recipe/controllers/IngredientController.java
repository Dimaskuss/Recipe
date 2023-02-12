package com.example.recipe.controllers;

import com.example.recipe.model.Ingredient;
import com.example.recipe.service.IngredientServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    private final IngredientServiceInterface ingredientServiceInterface;

    public IngredientController(IngredientServiceInterface ingredientServiceInterface) {
        this.ingredientServiceInterface = ingredientServiceInterface;
    }

    @PostMapping
    public ResponseEntity<Integer> addIngredient(@RequestBody Ingredient ingredient) {
        int id = ingredientServiceInterface.addIngredient(ingredient);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredientForId(@PathVariable int id) {
        Ingredient ingredient = ingredientServiceInterface.getIngredient(id);
        if (ingredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> editIngredient(@PathVariable int id, @RequestBody Ingredient newIngredient) {
        Ingredient ingredient = ingredientServiceInterface.editIngredient(id, newIngredient);
        if (ingredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable Integer id) {

        if (ingredientServiceInterface.deleteIngredient(id)) {
            return ResponseEntity.ok().build();
        } else {

            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity getAllIngredients() {

            return ResponseEntity.ok(ingredientServiceInterface.getAllIngredients());
        }



}
