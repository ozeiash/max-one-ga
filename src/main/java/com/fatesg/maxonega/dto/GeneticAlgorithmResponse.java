package com.fatesg.maxonega.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record GeneticAlgorithmResponse(
        @Schema(description = "Melhor cromossomo encontrado", example = "11111111111111111111")
        String bestGenes,

        @Schema(description = "Quantidade de 1s no melhor cromossomo", example = "20")
        int bestFitness,

        @Schema(description = "Número de gerações executadas", example = "42")
        int generationsExecuted,

        @Schema(description = "Tamanho da solução", example = "20")
        int solutionLength
) {
}