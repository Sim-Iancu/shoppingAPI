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
import shopifyapi.model.*;
import shopifyapi.model.dao.CustomerRepository;
import shopifyapi.model.dao.ProductRepository;
import shopifyapi.model.dao.ShopRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void postOrder() throws Exception {
        this.mockMvc.perform(
                post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"customer\":{\"id\":1}," +
                                "\"shop\":{\"id\":2}," +
                                "\"date\":\"September 21, 2018\"," +
                                "\"lineItems\":[" +
                                "{\"product\":{\"productCode\":\"51419\",\"name\":\"Banana\",\"price\":0.45}," +
                                "\"quantity\":5}]}"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getOneOrder() throws Exception {
        //have to do a post because we can't pre-populate the database with orders
        this.mockMvc.perform(
                post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"customer\":{\"id\":1}," +
                                "\"shop\":{\"id\":2}," +
                                "\"date\":\"September 21, 2018\"," +
                                "\"lineItems\":[" +
                                "{\"product\":{\"productCode\":\"51419\",\"name\":\"Banana\",\"price\":0.45}," +
                                "\"quantity\":5}]}"))
                .andDo(print());

        String expectedOrder = "{\"orderNumber\":3,\"customer\":{\"id\":1,\"name\":\"Michael\",\"address\":\"308 King St.\",\"phone\":\"416-970-5473\"},\"shop\":{\"id\":2,\"name\":\"Simona's Grocery Store\",\"address\":\"273 Lester St.\",\"phone\":\"905-555-4907\"},\"date\":\"September 21, 2018\",\"lineItems\":[{\"id\":4,\"product\":{\"productCode\":51419,\"name\":\"Banana\",\"price\":0.45},\"quantity\":5,\"lineItemTotal\":2.25}],\"total\":2.25}";

        MvcResult result = this.mockMvc.perform(get("/orders/{id}", "3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(expectedOrder, result.getResponse().getContentAsString().trim());
    }

    @Test
    public void getAllOrders() throws Exception {
        //have to do a post because we can't pre-populate the database with orders
        this.mockMvc.perform(
                post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"customer\":{\"id\":1}," +
                                "\"shop\":{\"id\":2}," +
                                "\"date\":\"September 21, 2018\"," +
                                "\"lineItems\":[" +
                                "{\"product\":{\"productCode\":51419,\"name\":\"Banana\",\"price\":0.45}," +
                                "\"quantity\":5}]}"));

        String expectedOrder = "\"customer\":{\"id\":1,\"name\":\"Michael\",\"address\":\"308 King St.\",\"phone\":\"416-970-5473\"},\"shop\":{\"id\":2,\"name\":\"Simona's Grocery Store\",\"address\":\"273 Lester St.\",\"phone\":\"905-555-4907\"},\"date\":\"September 21, 2018\",\"lineItems\":[{\"id\":4,\"product\":{\"productCode\":51419,\"name\":\"Banana\",\"price\":0.45},\"quantity\":5,\"lineItemTotal\":2.25}],\"total\":2.25";

        MvcResult result = this.mockMvc.perform(get("/orders"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //use .contains instead of .equals because you can't guarantee the order
        assertTrue(result.getResponse().getContentAsString().trim().contains(expectedOrder.trim()));
    }

    @Test
    public void deleteOrder() throws Exception {
        MvcResult postResult = this.mockMvc.perform(
                post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"customer\":{\"id\":1}," +
                                "\"shop\":{\"id\":2}," +
                                "\"date\":\"September 21, 2018\"," +
                                "\"lineItems\":[" +
                                "{\"product\":{\"productCode\":\"51419\",\"name\":\"Banana\",\"price\":0.45}," +
                                "\"quantity\":5}]}"))
                .andDo(print())
                .andReturn();

        this.mockMvc.perform(delete("/orders/{id}", "3"))
                .andDo(print())
                .andExpect(status().isOk());

        MvcResult result = this.mockMvc.perform(get("/orders/{id}","3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        assertEquals("", result.getResponse().getContentAsString().trim());
    }
}