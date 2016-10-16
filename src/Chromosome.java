/**
 * Created by Etienne G on 28/09/2016.
 */
public class Chromosome {

    public float x;                        // phenotype (unsigned)
    public float fitness;                  // objective function value
    public int parent1, parent2, xsite;    // parents and cross pt
    public int[] genes = new int[Main.MAXGENELENGTH];     // array of int [0,1] w/ max gene length

    public Chromosome(){
        this.fitness = 0;
    }

    // Create a random individual -- Binary Encoded
    public void genChromosome() {
        for (int i = 0; i < genes.length; i++) {
            int gene = Algorithms.genRandomBit();
            genes[i] = gene;
        }
    }

    public String listGenes(){
        String genes = "";
        for(int i=0; i < Main.MAXGENELENGTH; i++){
            genes += this.genes[i];
//            System.out.print(genes[i]);
        }
        return genes;
    }

}
