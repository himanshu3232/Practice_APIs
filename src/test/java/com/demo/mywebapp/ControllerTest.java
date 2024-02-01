package com.demo.mywebapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
class ControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private Controller controller;

    @BeforeEach
    void setUp() {
        // Initialize MockMvc and set up initial data for pojoList
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        POJO pojo1 = new POJO("Book1", "Author1");
        POJO pojo2 = new POJO("Book2", "Author2");
        controller.addPojo(pojo1);
        controller.addPojo(pojo2);
    }

    @Test
    void testGetAllPojo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/test/getAll"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Book1\nBook2\n"));
    }

    @Test
    void testGetPojoByName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/test/{book}", "Book1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookName").value("Book1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].author").value("Author1"));
    }

    @Test
    void testAddPojo() throws Exception {
        POJO newPojo = new POJO("Book3", "Author3");
        mockMvc.perform(MockMvcRequestBuilders.post("/test")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(newPojo)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("true"));

        mockMvc.perform(MockMvcRequestBuilders.get("/test/{book}", "Book3"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookName").value("Book3"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].author").value("Author3"));
    }

    @Test
    void testDeletePojoByName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/test/{book}", "Book1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("true"));

        mockMvc.perform(MockMvcRequestBuilders.get("/test/getAll"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Book2\n"));
    }

    @Test
    void testUpdatePojoByName() throws Exception {
        POJO updatedPojo = new POJO("Book1", "NewAuthor");
        mockMvc.perform(MockMvcRequestBuilders.put("/test/{book}", "Book1")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(updatedPojo)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("true"));

        mockMvc.perform(MockMvcRequestBuilders.get("/test/{book}", "Book1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookName").value("Book1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].author").value("NewAuthor"));
    }
}
