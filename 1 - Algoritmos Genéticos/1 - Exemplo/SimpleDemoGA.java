import java.util.Random;

public class SimpleDemoGA {
    Population population = new Population();
    Individual fittest;
    Individual secondFittest;
    int generationCount = 0;
    
    public static void main(String[] args) {
        Random rn = new Random();
        SimpleDemoGA demo = new SimpleDemoGA();

        // Inicializar a população.
        demo.population.initializePopulation(10);

        // Calcular a aptidão de cada indivíduo.
        demo.population.calculateFitness();

        System.out.println("Geração: " + demo.generationCount + " Mais apto: " + demo.population.fittest);
        // Vai se repetir até a população gerar um indivíduo com
        // a maior aptidão.
        while(demo.population.fittest < 5) {
            ++demo.generationCount;

            // Faz a seleção dos dois indivíduos com maior aptidão.
            demo.selection();

            // Faz o cruzamento entre os indívudos mais aptos.
            demo.crossover();

            // Faz uma mutação caso as condições sejam atendidas.
            if (rn.nextInt() % 7 < 5) {
                demo.mutation();
            }

            // Adiciona os filhos dos mais bem aptos na população.
            demo.addFittestOffspring();

            demo.population.calculateFitness();
            
            System.out.println("Geração: " + demo.generationCount + " Mais apto: " + demo.population.fittest);
        }

        System.out.println("\nA solução foi encontrada na geração " + demo.generationCount);
        System.out.println("Aptidão: " + demo.population.getFittest().fitness);
        System.out.print("Genes: ");

        for (int i = 0; i < 5; i++) {
            System.out.print(demo.population.getFittest().genes[i]);
        }
        System.out.println("");
    }

    void selection() {
        // Seleciona o indivíduo mais apto.
        fittest = population.getFittest();
        // Seleciona o segundo indivíduo mais apto.
        secondFittest = population.getSecondFittest();
    }

    void crossover() {
        Random rn = new Random();

        // Seleciona um ponto de cruzamento aleatório.
        int crossOverPoint = rn.nextInt(population.individuals[0].geneLength);
        
        // Trocar valores entre pais.
        
        for(int i = 0; i < crossOverPoint; i++) {
            int temp = fittest.genes[i];
            fittest.genes[i] = secondFittest.genes[i];
            secondFittest.genes[i] = temp;
        }
    }

    // Mutação
    void mutation() {
        Random rn = new Random();

        // Seleciona um ponto de mutação aleatório.
        int mutationPoint = rn.nextInt(population.individuals[0].geneLength);
        
        // Inverte os valures no ponto de mutação.

        if(fittest.genes[mutationPoint] == 0) {
            fittest.genes[mutationPoint] = 1;
        } else {
            fittest.genes[mutationPoint] = 0;
        }
    }

     // Pega a prole mais apta.
     Individual getFittestOffspring() {
        if (fittest.fitness > secondFittest.fitness) {
            return fittest;
        }
        return secondFittest;
    }

    // Substitui o indivíduo menos apto da prole mais apta.
    void addFittestOffspring() {

        // Calculando o valor da aptidão dos filhos.
        fittest.calcFitness();
        secondFittest.calcFitness();

        // Pega o index do indivíduo menos apto.
        int leastFittestIndex = population.getLeastFittestIndividual();

        // Substitui o indivíduo menos apto pela prole mais apta.
        population.individuals[leastFittestIndex] = getFittestOffspring();
    }
}   