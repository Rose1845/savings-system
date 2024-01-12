package com.rose.savings.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rose.savings.model.entity.SavingsProduct;
import com.rose.savings.repository.SavingsProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class SavingsProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private SavingsProductRepository savingsProductRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @BeforeEach
    void setUpMethod(){
        savingsProductRepository.deleteAll();
    }

    @Test
    void createSavingsProduct() throws Exception {
            SavingsProduct savingsProduct =new SavingsProduct();
        savingsProduct.setName("rose");
        savingsProduct.setDescription("test");
        ResultActions resultActions = mockMvc.perform(post("/api/savings-products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(savingsProduct)));
        resultActions.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(savingsProduct.getName())))
                .andExpect(jsonPath("$.description",is(savingsProduct.getDescription())));

    }
    @Test
    void getAllSavingsProduct() throws Exception {
        List<SavingsProduct> savingsProducts = new ArrayList<>();
        savingsProducts.add(SavingsProduct.builder().name("ros").description("test234").build());
        savingsProducts.add(SavingsProduct.builder().name("ros23").description("test234").build());
        savingsProductRepository.saveAll(savingsProducts);
        ResultActions response = mockMvc.perform(get("/api/savings-products"));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(savingsProducts.size())));
    }

    @Test
    void getSavingsProducts() throws Exception {
        long id = 1L;
        SavingsProduct savingsProduct = SavingsProduct.builder()
                .name("rose")
                .description("rose test")
                .build();
        savingsProductRepository.save(savingsProduct);
        ResultActions response = mockMvc.perform(get("/api/savings-products/{id}/savings-product", id));
        response.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void deleteSavingsProduct() throws Exception {
//        long id = 1L;
//        SavingsProduct savingsProduct = SavingsProduct.builder()
//                .name("rose")
//                .description("rose test")
//                .build();
//        savingsProductRepository.save(savingsProduct);
//        ResultActions response = mockMvc.perform(delete("/api/savings-products/{id}", id));
//        response.andExpect(status().isOk())
//                .andDo(print());
        SavingsProduct savingsProduct = SavingsProduct.builder()
                .name("rose")
                .description("rose test")
                .build();
        savingsProductRepository.save(savingsProduct);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(delete("/api/savings-products/{id}", savingsProduct.getId()));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print());
    }



}