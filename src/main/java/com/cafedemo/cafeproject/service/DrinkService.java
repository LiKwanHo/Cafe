package com.cafedemo.cafeproject.service;

import com.cafedemo.cafeproject.model.Drink;
import com.cafedemo.cafeproject.utils.TextFileWriter;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DrinkService {

    public List<Drink> getAllDrinksFromFile(String filename) {
        List<Drink> drinkList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                Drink drink = new Drink();
                drink.setType(parts[0].split(": ")[1]);
                drink.setName(parts[1].split(": ")[1]);
                drink.setWeight(Double.parseDouble(parts[2].split(": ")[1]));
                drink.setPrice(Double.parseDouble(parts[3].split(": ")[1]));
                drink.setRoastingLevel(Integer.parseInt(parts[4].split(": ")[1]));
                drinkList.add(drink);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return drinkList;
    }

    public void saveDrink(Drink drink) {
        String drinkInfo = "Type: " + drink.getType() + ", Name: " + drink.getName() + ", Weight: " + drink.getWeight()
                             + ", Price: " + drink.getPrice() + ", Roasting Level: " + drink.getRoastingLevel();
        if ((drink.getType()).equals("coffee")) {
            TextFileWriter.writeToFile(drinkInfo, "coffee.txt");
        } else {
            TextFileWriter.writeToFile(drinkInfo, "tea.txt");
        }
    }

}
