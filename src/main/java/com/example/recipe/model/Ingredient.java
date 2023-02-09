package com.example.recipe.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ingredient {
    private String name;
    private int ingredientCount;
    private String measureUnit;

}
