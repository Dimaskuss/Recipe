package com.example.recipe.controllers;

import com.example.recipe.exception.NullException;
import com.example.recipe.model.Ingredient;
import com.example.recipe.model.Recipe;
import com.example.recipe.service.RecipeServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
@RestController
@RequestMapping("/pecipe")
public class RecipeController {
    private RecipeServiceInterface recipeServiceInterface;

    public RecipeController(RecipeServiceInterface recipeServiceInterface) {
        this.recipeServiceInterface = recipeServiceInterface;
    }

    @PostMapping
    public ResponseEntity<Integer> addIngredient(@RequestBody Recipe recipe) {
        int id = recipeServiceInterface.addRecipe(recipe);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getIngredientForId(@PathVariable int id) {
        Recipe recipe = recipeServiceInterface.getRecipe(id);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> editIngredient(@PathVariable int id, @RequestBody Recipe newRecipe) {
        Recipe recipe = recipeServiceInterface.editRecipe(id, newRecipe);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Integer id) throws NullException {

        if (recipeServiceInterface.deleteRecipe(id)) {
            return ResponseEntity.ok().build();
        } else {
            throw new NullException("нет такого номера ингридеиента");

        }
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<Recipe>> getAllRecipe() {
        if (!recipeServiceInterface.isEmpty()) {
            return ResponseEntity.ok(recipeServiceInterface.getAllRecipe());
        }
        return ResponseEntity.notFound().build();

    }
}


