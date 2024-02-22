package com.cafedemo.cafeproject.controller;

import com.cafedemo.cafeproject.model.Drink;
import com.cafedemo.cafeproject.service.DrinkService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class DrinkController {

    private final DrinkService drinkService;

    public DrinkController(DrinkService drinkService) {
        this.drinkService = drinkService;
    }
    @RequestMapping
    public ModelAndView drinkPage() {
        return new ModelAndView("index");
    }


    @GetMapping("/tea")
    public ModelAndView getAllTeas() {
        List<Drink> teas = drinkService.getAllDrinksFromFile("tea.txt");
        ModelAndView modelAndView = new ModelAndView("tea");
        modelAndView.addObject("tea", teas);
        return modelAndView;
    }

    @PostMapping("/tea")
    public void addTea(@RequestBody Drink tea) {
        drinkService.saveDrink(tea);
    }

    @GetMapping("/coffee")
    public ModelAndView getAllCoffee() {
        List<Drink> coffees = drinkService.getAllDrinksFromFile("coffee.txt");
        ModelAndView modelAndView = new ModelAndView("coffee");
        modelAndView.addObject("coffee", coffees);
        return modelAndView;
    }

    @PostMapping("/coffee")
    public ResponseEntity<String> addCoffee(@RequestBody Drink coffee) {
        drinkService.saveDrink(coffee);
        return ResponseEntity.status(HttpStatus.CREATED).body("Coffee added successfully");
    }
}