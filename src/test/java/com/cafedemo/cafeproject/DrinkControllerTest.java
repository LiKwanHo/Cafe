package com.cafedemo.cafeproject;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cafedemo.cafeproject.controller.DrinkController;
import com.cafedemo.cafeproject.model.Drink;
import com.cafedemo.cafeproject.service.DrinkService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(DrinkController.class)
public class DrinkControllerTest {

    @MockBean
    private DrinkService drinkService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void mockMvCCreatedTest() {
        assertNotNull(mockMvc);
    }

    @InjectMocks
    private DrinkController drinkController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void testAddDrink() throws Exception {
        Drink tea = new Drink();
        tea.setType("tea");
        tea.setName("Early Grey");
        tea.setWeight(200);
        tea.setPrice(3.0);
        tea.setRoastingLevel(2);

        String jsonStr = objectMapper.writeValueAsString(tea);

        mockMvc.perform(MockMvcRequestBuilders.post("/tea")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonStr))
                .andExpect(status().isCreated())
                .andExpect(content().string("Tea added successfully"));
    }

    @Test
    void testGetAllTeas() throws Exception {
        Drink tea = new Drink();
        tea.setType("tea");
        tea.setName("Early Grey");
        tea.setWeight(200);
        tea.setPrice(3.0);
        tea.setRoastingLevel(2);
        List<Drink> teas = Arrays.asList(tea, tea);

        when(drinkService.getAllDrinksFromFile("tea.txt")).thenReturn(teas);

        mockMvc.perform(MockMvcRequestBuilders.get("/tea"))
                .andExpect(status().isOk())
                .andExpect(view().name("tea"))
                .andExpect(model().attributeExists("tea"))
                .andExpect(model().attribute("tea", teas));
    }
}

