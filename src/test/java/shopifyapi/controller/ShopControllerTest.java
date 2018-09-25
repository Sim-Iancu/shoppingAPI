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
public class ShopControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void postShop() throws Exception {
        this.mockMvc.perform(
                post("/shops")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"The Tea Shop\", \"address\":\"55 Regina Street\", \"phone\":\"519-807-3099\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("The Tea Shop"));
    }

    @Test
    public void getOneShop() throws Exception {
        //don't have to do a get because the database is pre-populated with a shop

        MvcResult result = this.mockMvc.perform(get("/shops/{id}", "2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String expectedShop = "{\"id\":2,\"name\":\"Simona's Grocery Store\",\"address\":\"273 Lester St.\",\"phone\":\"905-555-4907\"}";

        assertEquals(expectedShop, result.getResponse().getContentAsString().trim());

    }

    @Test
    public void getAllShops() throws Exception {
        //database already has shop 'Simona's Grocery Store' in it
        this.mockMvc.perform(
                post("/shops")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"The Tea Shop\",\"address\":\"55 Regina Street\",\"phone\":\"519-807-3099\"}"))
                .andDo(print());

        MvcResult result = this.mockMvc.perform(get("/shops"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String expectedShop1 = "\"name\":\"Simona's Grocery Store\",\"address\":\"273 Lester St.\",\"phone\":\"905-555-4907\"";
        String expectedShop2 = "\"name\":\"The Tea Shop\",\"address\":\"55 Regina Street\",\"phone\":\"519-807-3099\"";

        //use .contains instead of .equals because you can't guarantee the order
        assertTrue(result.getResponse().getContentAsString().trim().contains(expectedShop1.trim()));
        assertTrue(result.getResponse().getContentAsString().trim().contains(expectedShop2.trim()));
    }

    @Test
    public void deleteShop() throws Exception {
        this.mockMvc.perform(delete("/shops/{id}", "2"))
                .andDo(print())
                .andExpect(status().isOk());

        MvcResult result = this.mockMvc.perform(get("/shops/{id}", "2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        assertEquals("", result.getResponse().getContentAsString().trim());
    }

}