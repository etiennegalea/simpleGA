import java.util.Random;

/**
 * Created by Etienne G on 01/10/2016.
 */
public class Algorithms {

    static Random rnd = new Random();




    // generates a pseudo-random integer (0 or 1)
    public static int genRandomBit(){
        return ((Math.random() < 0.5) ? 0 : 1);
        // return (Math.round((float)Math.random()));
    }

    // generates a pseudo-random real value between 0 and 1
    public static float genRandomFloat(){
        return ((float)Math.random());
    }

    // generates a random integer between specified upper and lower bounds
    public static int genRandomInt(Random rnd){
        return rnd.nextInt(125) + 1;
    }

    public static int genRndGeneLength() {
        return rnd.nextInt(Main.MAXGENELENGTH);
    }

    public static int genRndPop() {
        return rnd.nextInt(Main.MAXPOP);
    }


    // ----------------------------- DAVID E. GOLDBERG ----------------------------

    public static boolean flip(double probability){
        if(probability == 1)
            return true;
        else
            flip(Math.random());
        return false;
    }




    // ----------------------------------------------------------------------------


    public static float getFitness(Chromosome chrom){
        float fitness = 0;
        int[] goal = toIntArray(Main.GOAL);

        for(int i = 0; i < Main.MAXGENELENGTH; i++){
            System.out.println("~ chrome gene["+(i+1)+"]: " + chrom.genes[i]);
            if(goal[i] ==  chrom.genes[i]){
//                System.out.println("~ chrome gene["+(i+1)+"]: " + chrom.genes[i]);
                fitness ++;
            }
        }

        return fitness;
    }

    public static int[] toIntArray(String stringGoal){
        int[] intSol = new int[Main.MAXGENELENGTH];
        String[] stringSol = stringGoal.split("");

        for(int i=0; i < stringGoal.length(); i++){
            intSol[i] = Integer.parseInt(stringSol[i]);
        }

//        System.out.println();
//        for(int i=0; i < stringGoal.length(); i++) {
//            System.out.print(intSol[i]);
//        }

        return intSol;
    }


    public static Population rouletteSelection(Population pop, boolean printTestCases){

        float avg;
        float popTotal = 0;
        int max = Main.MAXPOP;

        // total fitness of population
        for(int i = 0; i < max; i++)
            popTotal += pop.population[i].fitness;
        // average fitness of population
        avg = popTotal / max;
        pop.avg = avg;

        if(printTestCases)  System.out.println("\ntotal: " + popTotal + "\nmax: " + max + "\navg: " + avg + "\n");

//      ~~ percentage testing ~~
        float totalP = 0;
        float[] percentages = new float[max];
        for(int i = 0; i < max; i++){
            percentages[i] = pop.population[i].fitness / popTotal;
            totalP += percentages[i];
            if(printTestCases)  System.out.println("Chromosome " + (i+1) + ": " + (percentages[i]*100) + "%");
        }

        if(printTestCases)  System.out.println("\ntotal percent: " + (totalP*100) + "%\n");

        // create new population
//        Population roulettePop = new Population(Main.MAXPOP/2, false);
        Population roulettePop = new Population(Main.MAXPOP, false);
        Population newPop = new Population(Main.MAXPOP, false);

        float newPopTotal = 0;
        double rnd;
        int selectedChrom = -1;
        int crossoverCounter = 0;
        int index = 0;

        // greedy search
        // ~~~ should fill half of the new population, then crossover parents
        // ~~~ s.t. 2 children are generated from the new parents
        if(printTestCases)  System.out.println("~ new population ~");
        int k = 0;

        crossover(newPop, pop.elite, pop.elite, 0);
        for(int i = 2; i < Main.MAXPOP; i++){
            rnd = Math.random();
//            if(printTestCases)  System.out.println("random float: " + rnd);
            for(int j = 0; j < max; j++) {
                rnd -= percentages[j];
                if (rnd <= 0) {
                    roulettePop.population[i] = pop.population[j];
                    break;
                }
            }
            k++;
            if(k == 2){
                crossover(newPop, roulettePop.population[i-1], roulettePop.population[i], i-1);
                k = 0;
            }
        }

        newPop.getFitness();

        // total fitness of population
        popTotal = 0;
        for(int i = 0; i < max; i++)
            popTotal += newPop.population[i].fitness;
        // average fitness of population
        double newAvg = popTotal / max;
        double avgDiff = (double)Math.round((newAvg - avg) * 100) /100 ;


//        System.out.print("\t- avg difference: " + avgDiff);


//        int index1, index2;
//        int k = 0;
//        // loops for a number of the population's length, crossing over chromosomes at random
//        for(int i=0; i < Main.MAXPOP/2; i++){
//            // checking for random pop index for no duplicates and indexes of 0 or max
//            do{
//                index1 = genRndPop();
//            }while(index1 != 0 && index1 != Main.MAXPOP);
//            do{
//                index2 = genRndPop();
//            }while(index2 != 0 && index2 != Main.MAXPOP && index1 != index2);
//
//            crossover(newPop, roulettePop.population[index1], roulettePop.population[index2], k);
//            k += 2;
//        }

        if(printTestCases)  newPop.listChromosomes();

//        if(newPop.maxFitness != 20){
//        if(totalIterations < 5){
//            totalIterations++;
//            rouletteSelection(newPop, false);
//        }
        return newPop;
    }


    // cross 2 parent strings, place 2 child strings
    public static void crossover(Population p, Chromosome father, Chromosome mother, int k) {

        double crossOverRate = ((Main.MAXPOP * 0.25) / Main.MAXPOP);
        double crossOverPt = Math.round(Math.random() * Main.MAXGENELENGTH);
//        System.out.println("crossover pt: " + crossOverPt);
        Chromosome child1 = new Chromosome();
        Chromosome child2 = new Chromosome();

//        System.out.println("Crossover rate: " + crossOverRate);

        if(Math.random() < crossOverRate) {
            for (int i = 0; i < Main.MAXGENELENGTH; i++) {
                if (i < crossOverPt) {
                    child1.genes[i] = father.genes[i];
                    child2.genes[i] = mother.genes[i];
                } else {
                    child1.genes[i] = mother.genes[i];
                    child2.genes[i] = father.genes[i];
                }
            }
        }

        p.storeChromosome(k, mutationByRate(child1));
        p.storeChromosome(k+1, mutationByRate(child2));

    }

    // bit inversion over a random few genes
    public static Chromosome mutationByRnd(Chromosome c){
        // invert n bits randomly
        for(int nMutation = 1; nMutation != 0; nMutation--){
            int rnd = genRndGeneLength(); // get random position
            // invert bit at random position
            c.genes[rnd] = (c.genes[rnd] == 0) ? 1 : 0;
        }
        return c;
    }

    public static Chromosome mutationByRate(Chromosome c){
        // invert bits at a fixed mutation rate
//        double mutationRate = Main.MAXGENELENGTH / (Main.MAXGENELENGTH * 100);
        double mutationRate = ((Main.MAXGENELENGTH * 0.2) / Main.MAXGENELENGTH);;

//        System.out.println("Mutation rate: " + mutationRate);

        for(int i=0; i < Main.MAXGENELENGTH; i++){
            if(Math.random() < mutationRate){
                // new allele at position i
                c.genes[i] = (int)Math.round(Math.random());
            }
        }

        return c;
    }

}
