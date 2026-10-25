package com.fatesg.maxonega.controller;

import com.fatesg.maxonega.dto.GeneticAlgorithmRequest;
import com.fatesg.maxonega.dto.GeneticAlgorithmResponse;
import com.fatesg.maxonega.service.MaxOneGeneticAlgorithmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Validated
@Tag(name = "Algoritmo Genético", description = "Endpoints para resolver o problema Max One")
public class GeneticAlgorithmController {

    private final MaxOneGeneticAlgorithmService geneticAlgorithmService;

    public GeneticAlgorithmController(MaxOneGeneticAlgorithmService geneticAlgorithmService) {
        this.geneticAlgorithmService = geneticAlgorithmService;
    }

    @PostMapping("/ga/max-one")
    @Operation(summary = "Resolve o problema Max One com Algoritmo Genético")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cálculo realizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    public ResponseEntity<GeneticAlgorithmResponse> solve(@Valid @RequestBody GeneticAlgorithmRequest request) {
        GeneticAlgorithmResponse response = geneticAlgorithmService.solve(request);
        return ResponseEntity.ok(response);
    }
}