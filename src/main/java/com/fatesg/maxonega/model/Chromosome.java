package com.fatesg.maxonega.model;

import java.util.Random;

public class Chromosome {
    private final String genes;

    public Chromosome(String genes) {
        if (genes == null || genes.isBlank()) {
            throw new IllegalArgumentException("Genes não podem ser nulos ou vazios.");
        }

        for (char gene : genes.toCharArray()) {
            if (gene != '0' && gene != '1') {
                throw new IllegalArgumentException("Cromossomo deve conter apenas 0 e 1.");
            }
        }

        this.genes = genes;
    }

    public String getGenes() {
        return genes;
    }

    public int length() {
        return genes.length();
    }

    public int fitness() {
        int count = 0;

        for (char gene : genes.toCharArray()) {
            if (gene == '1') {
                count++;
            }
        }

        return count;
    }

    public static Chromosome random(int length, Random random) {
        if (length <= 0) {
            throw new IllegalArgumentException("O tamanho do cromossomo deve ser maior que zero.");
        }

        StringBuilder builder = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            builder.append(random.nextBoolean() ? '1' : '0');
        }

        return new Chromosome(builder.toString());
    }

    public Chromosome crossover(Chromosome other, double crossoverRate, Random random) {
        if (random.nextDouble() > crossoverRate) {
            return new Chromosome(this.genes);
        }

        int cutPoint = random.nextInt(this.genes.length());

        String leftPart = this.genes.substring(0, cutPoint);
        String rightPart = other.genes.substring(cutPoint);

        return new Chromosome(leftPart + rightPart);
    }

    public Chromosome mutate(double mutationRate, Random random) {
        StringBuilder mutated = new StringBuilder(this.genes.length());

        for (int i = 0; i < this.genes.length(); i++) {
            char gene = this.genes.charAt(i);

            if (random.nextDouble() < mutationRate) {
                gene = (gene == '0') ? '1' : '0';
            }

            mutated.append(gene);
        }

        return new Chromosome(mutated.toString());
    }

    @Override
    public String toString() {
        return genes;
    }
}