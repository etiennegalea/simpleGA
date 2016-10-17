import javax.sound.midi.SysexMessage;

/**
 * Created by Etienne G on 28/09/2016.
 */

public class Population {

    Chromosome[] population;
    Chromosome elite;
    float maxFitness;
    float avg;

    // Create a population : Constructor
    public Population(int populationSize, boolean initialise) {
        population = new Chromosome[Main.MAXPOP];
        maxFitness = 0;
        // Initialise population
        if (initialise) {
            // Loop and create Chromosomes
            for (int i = 0; i < population.length; i++) {
                Chromosome newChromosome = new Chromosome();
                newChromosome.genChromosome();
                storeChromosome(i, newChromosome);
            }
        }
    }

    // store genes in a population
    public void storeChromosome(int index, Chromosome x){
        population[index] = x;
    }


    // list all chromosomes in a population
    public void listChromosomes(){
        for(int i=0; i < Main.MAXPOP; i++){
            System.out.print("Chromosome " + (i+1) + ": ");
            for(int j=0; j < Main.MAXGENELENGTH; j++){
                System.out.print(population[i].genes[j]);
            }
            System.out.println(", fitness: " + population[i].fitness);
        }
    }

    // get fitness for all chromosomes in a population
    public void getFitness(){
        int[] solution = Algorithms.toIntArray(Main.GOAL);
        float fitness;

        for(int i=0; i < Main.MAXPOP; i++){
            fitness = 0;
//            System.out.println("-- Population["+i+"] -- ");
            for(int j=0; j < Main.MAXGENELENGTH; j++){
//                System.out.println("Gene["+j+"]: " + population[i].genes[j]);
                if(solution[j] == population[i].genes[j]){
                    fitness++;
                }
            }
            population[i].fitness = fitness;
            if(maxFitness < fitness){
                maxFitness = fitness;
            }
        }
    }

    public Chromosome getFittest() {
        Chromosome fittest = population[0];
        // Loop through Chromosomes to find fittest
        for (int i = 0; i < Main.MAXPOP; i++) {
//            System.out.println("fittest ["+fittest.fitness+"]: " + fittest.listGenes());
            if (fittest.fitness < population[i].fitness) {
                fittest = population[i];
//                System.out.println("-- new fittest ["+fittest.fitness+"]: " + fittest.listGenes() + " --");
            }
        }
        // fittest is set as elite
        elite = fittest;

        return fittest;
    }
}
