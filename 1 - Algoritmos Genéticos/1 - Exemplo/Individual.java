import java.util.Random;

public class Individual {
    int fitness = 0;
    int[] genes = new int[5];
    int geneLength = 5;

    public Individual() {
        Random rn = new Random();

        // Define os genes randômicamente para todos os indivíduos.
        for(int i = 0; i < genes.length; i++) {
            genes[i] = Math.abs(rn.nextInt() % 2);
        }
        fitness = 0;

    }

    public void calcFitness() {
        fitness = 0;
        for(int i = 0; i < 5; i++) {
            // Se o gene tiver o valor de 1, ele se torna mais apto.
            if(genes[i] == 1) {
                ++fitness;
            }
        }
    }
}
