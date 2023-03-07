public class Population {
    int popSize = 10;
    Individual[] individuals = new Individual[10];
    int fittest = 0;

    public void initializePopulation(int size) {
        for (int i = 0; i < individuals.length; i++) {
            individuals[i] = new Individual();
        }
    }

    // Pega o indivíduo mais apto.
    public Individual getFittest() {
        int maxFit = Integer.MIN_VALUE;
        int maxFitIndex = 0;
        for(int i = 0; i < individuals.length; i++) {
            if(maxFit <= individuals[i].fitness) {
                maxFit = individuals[i].fitness;
                maxFitIndex = i;
            }
        }
        fittest = individuals[maxFitIndex].fitness;
        return individuals[maxFitIndex];
    }

    // Pega o segundo indivíduo mais apto.
    public Individual getSecondFittest() {
        int maxFit1 = 0;
        int maxFit2 = 0;
        for(int i = 0; i < individuals.length; i++) {

            if(individuals[i].fitness > individuals[maxFit1].fitness) {
                maxFit2 = maxFit1; 
                maxFit1 = i; 
            } else if(individuals[i].fitness > individuals[maxFit2].fitness) {
                maxFit2 = i;
            }
        }
        return individuals[maxFit2];
    }

    // Pega o indivíduo menos apto.
    public int getLeastFittestIndividual() {
        int minFitVal = Integer.MAX_VALUE;
        int minFitIndex = 0;

        for(int i = 0; i < individuals.length; i++) {
            if(minFitVal >= individuals[i].fitness) {
                minFitVal = individuals[i].fitness;
                minFitIndex = i;
            }
        }
        return minFitIndex;
    }
    
    // Calcular o nível de aptidão dos indivíduos.
    public void calculateFitness() {
        for(int i = 0; i < individuals.length; i++) {
            individuals[i].calcFitness();
        }
    }
}
