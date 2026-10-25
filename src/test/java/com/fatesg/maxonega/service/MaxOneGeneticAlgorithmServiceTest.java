package com.fatesg.maxonega.service;

import com.fatesg.maxonega.dto.GeneticAlgorithmRequest;
import com.fatesg.maxonega.dto.GeneticAlgorithmResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaxOneGeneticAlgorithmServiceTest {

    private final MaxOneGeneticAlgorithmService service = new MaxOneGeneticAlgorithmService();

    @Test
    void shouldFindSolutionWithAllOnes() {
        GeneticAlgorithmRequest request = new GeneticAlgorithmRequest(
                20,
                100,
                500,
                0.05,
                0.9,
                2
        );

        GeneticAlgorithmResponse response = service.solve(request);

        assertNotNull(response);
        assertEquals(20, response.solutionLength());
        assertEquals(20, response.bestFitness());
        assertTrue(response.bestGenes().chars().allMatch(ch -> ch == '1'));
    }

    @Test
    void shouldRejectInvalidEliteCount() {
        GeneticAlgorithmRequest request = new GeneticAlgorithmRequest(
                10,
                10,
                200,
                0.05,
                0.9,
                10
        );

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> service.solve(request)
        );

        assertTrue(ex.getMessage().contains("eliteCount"));
    }
}