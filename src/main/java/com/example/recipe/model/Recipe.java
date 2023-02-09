package com.example.recipe.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


@Data
@AllArgsConstructor
public class Recipe {
    private String name ;
    private int cookingTime;
    private List<Ingredient> ingredientList  ;
    private Set<String> steps  ;
}
