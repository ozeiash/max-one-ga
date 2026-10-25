# Algoritmo Genético para o Problema Max One

## 1. Visão geral

Este projeto implementa um Algoritmo Genético (AG) para resolver o problema Max One em Java com Spring Boot.

O problema Max One consiste em encontrar uma string binária de tamanho L com o maior número possível de bits iguais a 1. A função de avaliação (fitness) é simplesmente a quantidade de 1s presentes na cadeia.

Exemplo:
- Cromossomo: 11001101
- Fitness: 5

O objetivo é maximizar o valor da função:
- f(x) = número de bits com valor 1

---

## 2. Objetivo da atividade

Implementar, em linguagem Java, um algoritmo genético capaz de evoluir uma população de soluções até encontrar uma string composta majoritariamente por 1s.

A solução deve demonstrar:
- representação do cromossomo
- inicialização da população
- cálculo do fitness
- seleção
- crossover
- mutação
- elitismo
- critério de parada

---

## 3. Tecnologias utilizadas

- Java 17
- Spring Boot 3
- Maven
- Swagger/OpenAPI
- HTML + JavaScript para interface web

---

## 4. Estrutura do projeto

```text
max-one-ga/
├── pom.xml
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com/fatesg/maxonega
│   │   │       ├── MaxOneGaApplication.java
│   │   │       ├── controller
│   │   │       │   └── GeneticAlgorithmController.java
│   │   │       ├── dto
│   │   │       │   ├── GeneticAlgorithmRequest.java
│   │   │       │   └── GeneticAlgorithmResponse.java
│   │   │       ├── exception
│   │   │       │   └── GlobalExceptionHandler.java
│   │   │       ├── model
│   │   │       │   └── Chromosome.java
│   │   │       └── service
│   │   │           └── MaxOneGeneticAlgorithmService.java
│   │   └── resources
│   │       ├── application.properties
│   │       └── static
│   │           └── index.html
│   └── test
│       └── java
│           └── com/fatesg/maxonega
│               ├── controller
│               │   └── GeneticAlgorithmControllerTest.java
│               └── service
│                   └── MaxOneGeneticAlgorithmServiceTest.java
```

---

## 5. Requisitos

Antes de executar o projeto, certifique-se de ter instalado:

- JDK 17+
- Maven
- IDE (IntelliJ IDEA, VS Code, Eclipse)

---
## 6. Como executar

### Passo 1: Clonar ou criar o projeto

Clone o projeto Spring Boot com Maven ou baixe o arquivo zip.

### Passo 2: Instalar dependências

No diretório do projeto, execute:
mvn clean install

### Passo 3: Subir a aplicação
mvn spring-boot:run

### Passo 4: Acessar a aplicação

- Swagger: [http://localhost:8085/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- Interface web: [http://localhost:8085/](http://localhost:8085/)

## 7. Endpoint da API

### POST /api/ga/max-one

#### Exemplo de request

```
{
  "geneLength": 20,
  "populationSize": 100,
  "maxGenerations": 500,
  "mutationRate": 0.05,
  "crossoverRate": 0.9,
  "eliteCount": 2
}
```

#### Exemplo de response
```
{
  "bestGenes": "11111111111111111111",
  "bestFitness": 20,
  "generationsExecuted": 42,
  "solutionLength": 20
}
```

