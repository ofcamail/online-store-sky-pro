package com.example.onlinestoreproject;

import com.example.onlinestoreproject.dto.CategoryDTO;
import com.example.onlinestoreproject.model.Category;
import com.example.onlinestoreproject.repository.CategoryRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OnlineStoreProjectApplicationTests {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    CategoryRepository categoryRepository;
    private List<CategoryDTO> categoryList = new ArrayList<>();

    @BeforeEach
    void setUp(){
        Category category = new Category();
        category.setName("Clovers");
        Category category1 = new Category();
        category1.setName("car");
        categoryRepository.save(category);
        categoryRepository.save(category1);
        categoryList.add(CategoryDTO.of(category));
        categoryList.add(CategoryDTO.of(category1));

    }

    @Test
    void contextLoads() {
    }
    @Test
    public void whenCreateProduct_thenItExistInList() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","111");
        jsonObject.put("description", "1234567");
        jsonObject.put("creationDate", Instant.now());
        jsonObject.put("modificationDate", Instant.now());
        JSONArray jsonArray = new JSONArray();
        categoryList.stream().map(categoryDTO -> {
            try {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("id", categoryDTO.getId());
                jsonObject1.put("name", categoryDTO.getName());
                return jsonObject1;
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

        }).forEach(jsonArray::put);
        jsonObject.put("categories",jsonArray);
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonObject.toString()))
                .andExpect(status().isOk());

    }

}
