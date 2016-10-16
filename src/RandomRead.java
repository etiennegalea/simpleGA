/**
 * Created by Etienne G on 07/10/2016.
 */
public class RandomRead {

    public static void getSolution(){
        int counter = 0;
        int[] temp = new int[Main.MAXGENELENGTH];
        int[] solution = Algorithms.toIntArray(Main.GOAL);

        System.out.println(" --- Finding Solution by Random Search ---\n");

        do{
            counter++;
            temp = genRandomSol();
            System.out.println("Counter: " + counter + " -- " + printSol(temp));
        }while(compareSols(solution, temp) == false);

        System.out.println("Solution found at: " + counter + " -- " + printSol(temp));
    }

    private static int[] genRandomSol(){
        int[] rSol = new int[Main.MAXGENELENGTH];
        for(int i = 0; i < Main.MAXGENELENGTH; i++){
            rSol[i] = (int)Math.round(Math.random());
        }

        return rSol;
    }

    private static boolean compareSols(int[] solution, int[] rSol){
        boolean verdict = true;
        for(int i = 0; i < Main.MAXGENELENGTH; i++){
            if(rSol[i] != solution[i])
                verdict = false;
        }
        return verdict;
    }

    private static String printSol(int[] sol){
        String solution = "";
        for(int i = 0; i < Main.MAXGENELENGTH; i++) {
            solution += sol[i];
        }
        return solution;
    }

}
