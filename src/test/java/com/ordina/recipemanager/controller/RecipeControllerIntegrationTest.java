package com.ordina.recipemanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ordina.recipemanager.Helper;
import com.ordina.recipemanager.model.Recipe;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
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
public class RecipeControllerIntegrationTest {

    static String RECIPE_MANAGER_SERVICE = "/recipe-manager/rest/api/v1/recipe/";

    @Autowired
    MockMvc mockMvc;

    @Test
    void givenRecipe_whenAddRecipe_thenReturnRecipe() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post(RECIPE_MANAGER_SERVICE + "/addRecipe")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(Helper.createRecipe()));
        mockMvc.perform(mockRequest).andExpect(status().isOk()).andExpect(jsonPath("$.id").value(3))
                .andReturn();
    }

    @Test
    void givenRecipeList_whenAddRecipes_thenReturnRecipes() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post(RECIPE_MANAGER_SERVICE + "/addRecipes")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(Helper.createRecipes()));
        mockMvc.perform(mockRequest).andExpect(status().isOk()).andExpect(jsonPath("$[*].id").value(Matchers.containsInAnyOrder(1, 2)))
                .andReturn();
    }

    @Test
    void given_whenGetRecipes_thenReturnRecipes() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .get(RECIPE_MANAGER_SERVICE + "/getRecipes")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(mockRequest).andExpect(status().isOk()).andExpect(jsonPath("$[*]").value(Matchers.hasSize(20)))
                .andReturn();
    }

    @Test
    void givenId_whenGetRecipeById_thenReturnRecipe() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .get(RECIPE_MANAGER_SERVICE + "/getRecipeById/1002")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(mockRequest).andExpect(status().isOk()).andExpect(jsonPath("$.id").value(1002))
                .andReturn();
    }

    @Test
    void givenId_whenDeleteRecipe_thenReturnVoid() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .delete(RECIPE_MANAGER_SERVICE + "/deleteRecipe/1009")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(mockRequest).andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void givenRecipe_whenUpdateRecipe_thenReturnRecipe() throws Exception {
        Recipe recipe = Recipe.builder()
                .id(2)
                .title("New Updated Recipe Title")
                .isVegetarian(Boolean.FALSE)
                .noOfServing(5)
                .build();

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .put(RECIPE_MANAGER_SERVICE + "/updateRecipe")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(recipe));
        mockMvc.perform(mockRequest).andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void givenIsVegetarian_whenFindRecipeIsVegetarian_thenTrue() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .get(RECIPE_MANAGER_SERVICE + "/search/findRecipeIsVegetarian/false")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(mockRequest).andExpect(status().isOk()).andExpect(jsonPath("$..id").isNotEmpty())
                .andReturn();
    }

    @Test
    void givenNoOfServing_whenFindRecipeByNoOfServing_thenReturnRecipes() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .get(RECIPE_MANAGER_SERVICE + "/search/findRecipeByNoOfServing/2")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(mockRequest).andExpect(status().isOk()).andExpect(jsonPath("$..id").isNotEmpty())
                .andReturn();
    }

    @Test
    void givenId_whenFindRecipeByIngredientsIncludesOrExcludes_thenReturnRecipes() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .get(RECIPE_MANAGER_SERVICE + "/search/findRecipeByIngredientsIncludesOrExcludes/")
                .param("includes", "Butter")
                .param("excludes", "Cooking Oil")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(mockRequest).andExpect(status().isOk()).andExpect(jsonPath("$..id").isNotEmpty())
                .andReturn();
    }

    @Test
    void givenId_whenFindRecipeByInstructionContainsText_thenReturnRecipes() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .get(RECIPE_MANAGER_SERVICE + "/search/findRecipeByInstructionContainsText/zest half a large lemon")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(mockRequest).andExpect(status().isOk())
                .andReturn();
    }

}
