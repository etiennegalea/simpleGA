import java.io.IOException;
import java.util.Random;

public class Main {

    // Candidate solution -- goal
    static String GOAL = "001011101110110010010010111101110000101000100001011001001000";     // length of 10 (genes)

    static int MAXPOP = 100;
    static int MAXGENELENGTH = 60;



    public static void main(String[] args) throws IOException {

        // Data type declarations

        boolean allele;             // bit position
        boolean[] chromosome;       // array of alleles
        Chromosome record;
        Chromosome[] population;
        Population oldPop, newPop;  // old and new populations
        int gen = 0;                // generation counter
        int maxGen;                 // maximum generation (upper limit)
        float pCross;               // probability of crossover
        float pMutation;            // probability of mutationByRnd
        float sumFitness;           // sum of pop. fitness values (important during roulette wheel selection)
        int nMutation;              // mutationByRnd counter
        int nCross;                 // crossover counter
        float avg, max, min;

        Random rnd = new Random();

        // testing random search

//        RandomRead.getSolution();
//        System.in.read();

//        ~ Testing random number generators ~
//        for (int i = 0; i < 20; i++)
//            System.out.print(Algorithms.genRandomBit());
//
//        System.out.println("\n");
//
//        for (int i = 0; i < 10; i++)
//            System.out.println(Algorithms.genRandomFloat());
//
//        System.out.println("\n");
//
//        for (int i = 0; i < 10; i++)
//            System.out.print(Algorithms.genRandomInt(rnd) + " ");




//        System.out.println("\n");
//
//        float[] group = new float[10];
//        for (int i = 0; i < group.length; i++) {
//            group[i] = (float) Math.random() * 100;
//            System.out.println(group[i]);
//        }
//        System.out.println(" ~ " + group.length + " ~");



        // ~~~ roulette wheel selection testing ~~~

//        float average = 0;
//        float totalChroms = 0;
//        int iterations = 100;
//        for (int i = 0; i < iterations; i++) {
//            totalChroms += Algorithms.RouletteSelection(group, false);
//        }
//        average = totalChroms / iterations;
//
//        System.out.println("\naverage of total chromosomes \nfrom roulette selection: " + average);

//        Algorithms.RouletteSelection(group, true);
        System.out.println("\n");
        System.out.println(" --- Finding Solution by Genetic Algorithm ---\n");

        // generate a new population consisting of MAXPOP chromosomes and MAXGENELENGTH genes
        Population pop = new Population(MAXPOP, true);

        // fitness test for each genes in the population
        pop.getFitness();
        System.out.println("max fitness of population: " + pop.maxFitness);


        // list population and their fitness
//        pop.listChromosomes();

        // roulette selection
//        Algorithms.rouletteSelection(pop, true);

        while(pop.maxFitness != MAXGENELENGTH){
            gen++;
            System.out.print("Generation: " + gen);
            pop = Algorithms.rouletteSelection(pop, false);
            System.out.println(" - fittest["+(int)(pop.maxFitness)+"]: " + pop.getFittest().listGenes());
        }

        System.out.println();
        System.out.print("Fittest chromosome in population: " + pop.getFittest().listGenes());


//        int randolph = Algorithms.genRndGeneLength();
//        Chromosome test = new Chromosome();
//        test.genChromosome();
//        System.out.println("generate random chromosome: " + test.listGenes());
//
//        System.out.println("mutationByRnd test:" + test.genes[randolph]);
//        test.genes[randolph] = (test.genes[randolph] == 0) ? 1 : 0;
//        System.out.println("mutationByRnd test:" + test.genes[randolph]);
//
//        Algorithms.mutationByRnd(test);
//        System.out.println("mutationByRnd result: " + test.listGenes());


    }

}
