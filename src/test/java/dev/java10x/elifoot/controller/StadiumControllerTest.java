package dev.java10x.elifoot.controller;

import dev.java10x.elifoot.BaseIntegrationTest;
import dev.java10x.elifoot.controller.request.StadiumRequest;
import dev.java10x.elifoot.entity.Stadium;
import dev.java10x.elifoot.repository.StadiumRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

class StadiumControllerTest extends BaseIntegrationTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    StadiumRepository stadiumRepository;

    @BeforeEach
    void setUp() {
        stadiumRepository.deleteAll();
        stadiumRepository.save(Stadium.builder()
                .name("Novo estadio")
                .city("Juiz de Fora")
                .capacity(60000)
                .urlImg("http://img.com/jf.jpg")
                .build());
    }

    @WithMockUser(authorities = {"SCOPE_stadium:write"})
    @Test
    @DisplayName("Should create a stadium")
    void shouldCreateStadium() throws Exception {

        StadiumRequest request = StadiumRequest.builder()
                .name("Novo estadio")
                .city("Pelotas")
                .capacity(60000)
                .urlImg("http://img.com/pelotas.jpg")
                .build();

        mockMvc.perform(post("/stadiums")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(request.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city").value(request.getCity()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.capacity").value(request.getCapacity()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.urlImg").value(request.getUrlImg()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty());
    }

    @WithMockUser(authorities = {"SCOPE_stadium:read"})
    @Test
    @DisplayName("Should list stadiums")
    void shouldListStadium() throws Exception {

        mockMvc.perform(get("/stadiums"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.length()").value(org.hamcrest.Matchers.greaterThan(0)));
    }

    @WithMockUser(authorities = {"SCOPE_stadium:read"})
    @Test
    @DisplayName("Should return 403 when user lacks write scope")
    void shouldReturnForbiddenWhenUserLacksWriteScope() throws Exception {

        StadiumRequest request = StadiumRequest.builder()
                .name("Novo estadio")
                .city("Pelotas")
                .capacity(60000)
                .urlImg("http://img.com/pelotas.jpg")
                .build();

        mockMvc.perform(post("/stadiums")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @WithMockUser(authorities = {"SCOPE_stadium:write"})
    @Test
    @DisplayName("Should return 400 when request is invalid")
    void shouldReturnBadRequestWhenRequestIsInvalid() throws Exception {

        StadiumRequest request = StadiumRequest.builder()
                .capacity(60000)
                .urlImg("http://img.com/pelotas.jpg")
                .build();

        mockMvc.perform(post("/stadiums")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors.name").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors.city").exists());
    }
}