package com.example.recipe.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {

    @GetMapping("/info")
    public  String helloWorld(){
        return "Клименко Дмитрий.   "  +
                "Проект : <сайт Рцептов>,\n" +
                "дату создания проекта:02.02.2023,\n" +
                "описание проекта: <Это будет лучший в мире, самый популярный сайт с рецептами Блинов>.";
    }
    @GetMapping()
    public  String start(){
        return "Приложение запущено";
    }
}
