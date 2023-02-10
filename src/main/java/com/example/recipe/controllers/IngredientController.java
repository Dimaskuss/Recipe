package com.example.recipe.controllers;

import com.example.recipe.exception.NullException;
import com.example.recipe.model.Ingredient;
import com.example.recipe.service.IngredientServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    private IngredientServiceInterface ingredientServiceInterface;

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
    public ResponseEntity<Void> deleteIngredient(@PathVariable Integer id) throws NullException {

        if (ingredientServiceInterface.deleteIngredient(id)) {
            return ResponseEntity.ok().build();
        } else {
            throw new NullException("нет такого ингридеиента");

        }
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<Ingredient>> getAllIngredients() {
        if (!ingredientServiceInterface.isEmpty()) {
            return ResponseEntity.ok(ingredientServiceInterface.getAllIngredients());
        }
        return ResponseEntity.notFound().build();

    }
}
