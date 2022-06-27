package com.ordina.recipemanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ordina.recipemanager.Helper;
import com.ordina.recipemanager.model.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class IngredientControllerTest {
    static String INGREDIENT_MANAGER_SERVICE = "/recipe-manager/rest/api/v1/ingredient/";

    @Autowired
    MockMvc mockMvc;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(mockMvc);
    }

    @Test
    public void given_whenGetIngredients_thanReturnIngredient() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .get(INGREDIENT_MANAGER_SERVICE + "/getIngredients")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(mockRequest).andExpect(status().isOk())
                .andExpect(jsonPath("$[*]").value(Matchers.hasSize(24)))
                .andReturn();
    }

    @Test
    public void given_whenGetIngredientById_thanReturnIngredient() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .get(INGREDIENT_MANAGER_SERVICE+"/getIngredientById/102")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(mockRequest).andExpect(status().isOk()).andExpect(jsonPath("$.id").value(102))
                .andReturn();
    }

    @Test
    public void givenIngredient_whenAddIngredient_thanReturnIngredient() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post(INGREDIENT_MANAGER_SERVICE + "/addIngredient")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(Helper.createIngredient()));
        mockMvc.perform(mockRequest).andExpect(status().isOk()).andExpect(jsonPath("$.id").isNotEmpty())
                .andReturn();
    }

    @Test
    public void givenIngredientList_whenAddIngredients_thanReturnIngredientList() throws Exception{
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post(INGREDIENT_MANAGER_SERVICE + "/addIngredients")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(Helper.createIngredients()));
        mockMvc.perform(mockRequest).andExpect(status().isOk()).andExpect(jsonPath("$[*].id").value(Matchers.containsInAnyOrder(1, 2, 3, 4)))
                .andReturn();
    }

    @Test
    public void givenId_whenDeleteIngredient_thanReturnVoid() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .delete(INGREDIENT_MANAGER_SERVICE+"/deleteIngredient/103")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(mockRequest).andExpect(status().isExpectationFailed())
                .andReturn();
    }

    @Test
    public void givenIngredient_whenUpdateIngredient_thanReturnIngredient() throws Exception {
       Ingredient updatedIngredient = Ingredient.builder().id(106).name("Black Pepper").build();

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .put(INGREDIENT_MANAGER_SERVICE + "/updateIngredient")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(updatedIngredient));
        mockMvc.perform(mockRequest).andExpect(status().isOk()).andExpect(jsonPath("$.id").value(106))
                .andReturn();
    }
}
