package com.fatesg.maxonega.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

public record GeneticAlgorithmRequest(
        @NotNull(message = "geneLength não pode ser nulo")
        @Min(value = 1, message = "geneLength deve ser maior que zero")
        @Schema(description = "Comprimento da string binária", example = "20")
        Integer geneLength,

        @NotNull(message = "populationSize não pode ser nulo")
        @Min(value = 2, message = "populationSize deve ser maior ou igual a 2")
        @Schema(description = "Tamanho da população", example = "100")
        Integer populationSize,

        @NotNull(message = "maxGenerations não pode ser nulo")
        @Min(value = 1, message = "maxGenerations deve ser maior que zero")
        @Schema(description = "Máximo de gerações", example = "500")
        Integer maxGenerations,

        @NotNull(message = "mutationRate não pode ser nulo")
        @DecimalMin(value = "0.0", inclusive = true, message = "mutationRate deve ser >= 0.0")
        @DecimalMax(value = "1.0", inclusive = true, message = "mutationRate deve ser <= 1.0")
        @Schema(description = "Taxa de mutação", example = "0.05")
        Double mutationRate,

        @NotNull(message = "crossoverRate não pode ser nulo")
        @DecimalMin(value = "0.0", inclusive = true, message = "crossoverRate deve ser >= 0.0")
        @DecimalMax(value = "1.0", inclusive = true, message = "crossoverRate deve ser <= 1.0")
        @Schema(description = "Taxa de crossover", example = "0.9")
        Double crossoverRate,

        @NotNull(message = "eliteCount não pode ser nulo")
        @Min(value = 1, message = "eliteCount deve ser maior ou igual a 1")
        @Schema(description = "Quantidade de melhores indivíduos preservados", example = "2")
        Integer eliteCount
) {
}