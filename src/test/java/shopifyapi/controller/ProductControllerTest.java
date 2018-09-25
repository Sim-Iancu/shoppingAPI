package shopifyapi.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createProduct() throws Exception {
        this.mockMvc.perform(
                post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"productCode\":19305,\"name\":\"Coffee\",\"price\":6.95}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Coffee"));
    }

    @Test
    public void getOneProduct() throws Exception {
        //don't have to do a get because the database is pre-populated with a customer

        MvcResult result = this.mockMvc.perform(get("/products/{id}","51419"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String expectedProduct = "{\"productCode\":51419,\"name\":\"Banana\",\"price\":0.45}";

        assertEquals(expectedProduct.trim(), result.getResponse().getContentAsString().trim());
    }

    @Test
    public void getAllProducts() throws Exception {
        //database already contains product 'Banana' in it

        this.mockMvc.perform(
                post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"productCode\":19305,\"name\":\"Coffee\",\"price\":6.95}"))
                .andDo(print());

        MvcResult result = this.mockMvc.perform(get("/products"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String expectedProduct = "[{\"productCode\":19305,\"name\":\"Coffee\",\"price\":6.95},{\"productCode\":51419,\"name\":\"Banana\",\"price\":0.45}]";

        assertEquals(expectedProduct.trim(), result.getResponse().getContentAsString().trim());
    }

    @Test
    public void deleteProduct() throws Exception {
        this.mockMvc.perform(delete("/products/{id}", "51419"))
                .andDo(print())
                .andExpect(status().isOk());

        MvcResult result = this.mockMvc.perform(get("/products/{id}", "51419"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        assertEquals("", result.getResponse().getContentAsString().trim());
    }

}