package com.example.recipe.controllers;

import com.example.recipe.exception.ValidationException;
import com.example.recipe.model.Ingredient;
import com.example.recipe.service.IngredientServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.ObjectUtils;
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
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиент добавлен"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Ошибка введенных значений"
            )


    })
    @PostMapping
    public ResponseEntity<Integer> addIngredient(@RequestBody Ingredient ingredient) throws ValidationException {
        int id = ingredientServiceInterface.addIngredient(ingredient);
        return ResponseEntity.ok(id);
    }

    @Operation(summary = "Вывод игредиента по номеру")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиент найден"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Неправильный номер ингридиента"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredientForId(@PathVariable int id) {
        Ingredient ingredient = ingredientServiceInterface.getIngredient(id);
        if (ObjectUtils.isEmpty(ingredient)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }

    @Operation(summary = "Изменение игредиента по номеру")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиент изменен"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Ошибка введенных значений"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Неправильный номер ингридиента"
            )
    })

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> editIngredient(@PathVariable int id, @RequestBody Ingredient newIngredient) {
        Ingredient ingredient = ingredientServiceInterface.editIngredient(id, newIngredient);
        if (ObjectUtils.isEmpty(ingredient)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }

    @Operation(summary = "Удаление игредиента")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиент удален"
            ),

            @ApiResponse(
                    responseCode = "404",
                    description = "Введен неправильный номер"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable Integer id) {

        if (ingredientServiceInterface.deleteIngredient(id)) {
            return ResponseEntity.ok().build();
        } else {

            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Показать все игредиенты")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Все игредиенты нашлись"

            )
    })
    @GetMapping("/")
    public ResponseEntity getAllIngredients() {

        return ResponseEntity.ok(ingredientServiceInterface.getAllIngredients());
    }


}
