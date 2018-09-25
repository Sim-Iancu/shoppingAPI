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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void postCustomer() throws Exception {
        this.mockMvc.perform(
                post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Gen\", \"address\":\"123 Sunview Street\", \"phone\":\"905-542-9004\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Gen"));
    }

    @Test
    public void getOneCustomer() throws Exception {
        //don't have to do a get because the database is pre-populated with a customer

        MvcResult result = this.mockMvc.perform(get("/customers/{id}","1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String expectedCustomer = "{\"id\":1,\"name\":\"Michael\",\"address\":\"308 King St.\",\"phone\":\"416-970-5473\"}";

        assertEquals(expectedCustomer, result.getResponse().getContentAsString().trim());
    }

    @Test
    public void getAllCustomers() throws Exception {
        //database already has customer 'Michael' in it
        this.mockMvc.perform(
                post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Gen\",\"address\":\"123 Sunview Street\",\"phone\":\"905-542-9004\"}"))
                .andDo(print());

        MvcResult result = this.mockMvc.perform(get("/customers"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String expectedCustomer1 = "\"name\":\"Michael\",\"address\":\"308 King St.\",\"phone\":\"416-970-5473\"";
        String expectedCustomer2 = "\"name\":\"Gen\",\"address\":\"123 Sunview Street\",\"phone\":\"905-542-9004\"";

        //use .contains instead of .equals because you can't guarantee the order
        assertTrue(result.getResponse().getContentAsString().trim().contains(expectedCustomer1.trim()));
        assertTrue(result.getResponse().getContentAsString().trim().contains(expectedCustomer2.trim()));
    }

    @Test
    public void deleteCustomer() throws Exception {
        this.mockMvc.perform(delete("/customers/{id}", "1"))
                .andDo(print())
                .andExpect(status().isOk());

        MvcResult result = this.mockMvc.perform(get("/customers/{id}", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        assertEquals("", result.getResponse().getContentAsString().trim());
    }
}