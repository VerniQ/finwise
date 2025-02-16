package me.verni.category;

import me.verni.config.TestSecurityConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@ContextConfiguration(classes = TestSecurityConfig.class)
public class CategoryControllerIT {

    @Autowired
    private MockMvc mockMvc;

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15.2")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @BeforeEach
    void setUp() {
        postgres.start();
    }

    @AfterEach
    void tearDown() {
        postgres.stop();
    }

    @Test
    public void shouldCreateCategory() throws Exception {
        String categoryJson = """
                {
                    "name": "Groceries"
                }
                """;

        mockMvc.perform(post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(categoryJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Groceries"));

    }

    @Test
    void shouldGetCategories() throws Exception {
        mockMvc.perform(get("/categories"))
                .andExpect(status().isOk());
    }
}
