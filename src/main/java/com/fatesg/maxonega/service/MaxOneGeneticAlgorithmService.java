package com.fatesg.maxonega.service;

import org.springframework.stereotype.Service;
import com.fatesg.maxonega.dto.GeneticAlgorithmRequest;
import com.fatesg.maxonega.dto.GeneticAlgorithmResponse;
import com.fatesg.maxonega.model.Chromosome;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

@Service
public class MaxOneGeneticAlgorithmService {

    private final Random random = new Random();

    public GeneticAlgorithmResponse solve(GeneticAlgorithmRequest request) {
        validateRequest(request);

        List<Chromosome> population = initializePopulation(request.geneLength(), request.populationSize());

        Chromosome best = getBestChromosome(population);

        int generation = 0;

        while (generation < request.maxGenerations() && best.fitness() < request.geneLength()) {
            List<Chromosome> nextPopulation = new ArrayList<>(request.populationSize());

            List<Chromosome> sortedPopulation = new ArrayList<>(population);
            sortedPopulation.sort(Comparator.comparingInt(Chromosome::fitness).reversed());

            for (int i = 0; i < request.eliteCount() && i < sortedPopulation.size(); i++) {
                nextPopulation.add(sortedPopulation.get(i));
            }

            while (nextPopulation.size() < request.populationSize()) {
                Chromosome parentA = tournamentSelection(population);
                Chromosome parentB = tournamentSelection(population);

                Chromosome childA = parentA.crossover(parentB, request.crossoverRate(), random);
                Chromosome childB = parentB.crossover(parentA, request.crossoverRate(), random);

                childA = childA.mutate(request.mutationRate(), random);
                childB = childB.mutate(request.mutationRate(), random);

                nextPopulation.add(childA);

                if (nextPopulation.size() < request.populationSize()) {
                    nextPopulation.add(childB);
                }
            }

            population = nextPopulation;

            Chromosome currentBest = getBestChromosome(population);

            if (currentBest.fitness() > best.fitness()) {
                best = currentBest;
            }

            generation++;
        }

        return new GeneticAlgorithmResponse(
                best.getGenes(),
                best.fitness(),
                generation,
                request.geneLength()
        );
    }

    private void validateRequest(GeneticAlgorithmRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("O payload da requisição não pode ser nulo.");
        }

        if (request.eliteCount() >= request.populationSize()) {
            throw new IllegalArgumentException("eliteCount deve ser menor que populationSize.");
        }
    }

    private List<Chromosome> initializePopulation(int geneLength, int populationSize) {
        List<Chromosome> population = new ArrayList<>(populationSize);

        for (int i = 0; i < populationSize; i++) {
            population.add(Chromosome.random(geneLength, random));
        }

        return population;
    }

    private Chromosome tournamentSelection(List<Chromosome> population) {
        int tournamentSize = 3;
        List<Chromosome> candidates = new ArrayList<>(tournamentSize);

        for (int i = 0; i < tournamentSize; i++) {
            int randomIndex = random.nextInt(population.size());
            candidates.add(population.get(randomIndex));
        }

        return candidates.stream()
                .max(Comparator.comparingInt(Chromosome::fitness))
                .orElseThrow(() -> new IllegalStateException("Erro ao selecionar o melhor candidato do torneio."));
    }

    private Chromosome getBestChromosome(List<Chromosome> population) {
        return population.stream()
                .max(Comparator.comparingInt(Chromosome::fitness))
                .orElseThrow(() -> new IllegalStateException("A população não pode estar vazia."));
    }
}