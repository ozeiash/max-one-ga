package com.fatesg.maxonega.controller;

import com.fatesg.maxonega.dto.GeneticAlgorithmRequest;
import com.fatesg.maxonega.service.MaxOneGeneticAlgorithmService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(GeneticAlgorithmController.class)
class GeneticAlgorithmControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MaxOneGeneticAlgorithmService geneticAlgorithmService;

    @Test
    void shouldReturnOkWhenRequestIsValid() throws Exception {
        var response = new com.fatesg.maxonega.dto.GeneticAlgorithmResponse(
                "11111111111111111111",
                20,
                35,
                20
        );

        when(geneticAlgorithmService.solve(any(GeneticAlgorithmRequest.class))).thenReturn(response);

        var request = new GeneticAlgorithmRequest(
                20,
                100,
                500,
                0.05,
                0.9,
                2
        );

        mockMvc.perform(post("/api/ga/max-one")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bestGenes").value("11111111111111111111"))
                .andExpect(jsonPath("$.bestFitness").value(20))
                .andExpect(jsonPath("$.solutionLength").value(20));
    }

    @Test
    void shouldReturnBadRequestWhenRequestIsInvalid() throws Exception {
        String invalidJson = """
                {
                  "geneLength": 0,
                  "populationSize": 1,
                  "maxGenerations": 0,
                  "mutationRate": -0.1,
                  "crossoverRate": 1.2,
                  "eliteCount": 0
                }
                """;

        mockMvc.perform(post("/api/ga/max-one")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());
    }
}