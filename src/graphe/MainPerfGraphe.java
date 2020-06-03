package graphe;

import graphe.grapheAdj.GraphAdj;
import graphe.grapheS.GraphS;
import json.Import;
import matrice.Matrice;

import java.io.FileNotFoundException;

/**
 * Classe pour la comparaison des performances entre GraphAdj et GraphS
 */
public class MainPerfGraphe {
    /**
     * Main de tests des performances
     * @param args Arguments du main
     */
    public static void main(String[] args){
        int[][] mat =  {{0,1,1,0,1,0,1,0,0,1},
                        {1,0,1,0,0,0,0,1,0,0},
                        {1,1,0,0,0,1,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0},
                        {1,0,0,0,0,0,0,1,0,0},
                        {0,0,1,0,0,0,0,0,0,0},
                        {1,0,0,0,0,0,0,0,0,0},
                        {0,1,0,0,1,0,0,0,1,0},
                        {0,0,0,0,0,0,0,1,0,0},
                        {1,0,0,0,0,0,0,0,0,0}};
        Matrice m = new Matrice(mat);
        GraphAdj ga = new GraphAdj(m);

        GraphS gs = new GraphS(10);
        gs.addArete(0,1);
        gs.addArete(0,2);
        gs.addArete(0,4);
        gs.addArete(0,6);
        gs.addArete(0,9);
        gs.addArete(1,2);
        gs.addArete(1,7);
        gs.addArete(2,5);
        gs.addArete(4,7);
        gs.addArete(7,8);

        // Tests sur la distance
        long startTime1 = System.nanoTime();
        ga.distance(5,8);
        long finishTime1 = System.nanoTime();
        System.out.println("Calcul de distance 5 -> 8 (GraphAdj) =  " + (finishTime1 - startTime1) + " ns");

        long startTime2 = System.nanoTime();
        gs.distance(5,8);
        long finishTime2 = System.nanoTime();
        System.out.println("Calcul de distance 5 -> 8 (GraphS) = " + (finishTime2 - startTime2) + " ns");

        // Tests sur la fonction isVoisin
        long startTime5 = System.nanoTime();
        ga.isVoisin(0,9);
        long finishTime5 = System.nanoTime();
        System.out.println("Fonction isVoisin (GraphAdj) 0 -> 9 (cas OK) = " + (finishTime5 - startTime5) + " ns");

        long startTime6 = System.nanoTime();
        ga.isVoisin(3,6);
        long finishTime6 = System.nanoTime();
        System.out.println("Fonction isVoisin (GraphAdj) 3 -> 6 (cas NOT OK) = " + (finishTime6 - startTime6) + " ns");

        long startTime7 = System.nanoTime();
        gs.isVoisin(0,9);
        long finishTime7 = System.nanoTime();
        System.out.println("Fonction isVoisin (GraphS) 0 -> 9 (cas OK) = " + (finishTime7 - startTime7) + " ns");

        long startTime8 = System.nanoTime();
        gs.isVoisin(3,6);
        long finishTime8 = System.nanoTime();
        System.out.println("Fonction isVoisin (GraphS) 3 -> 6 (cas NOT OK) = " + (finishTime8 - startTime8) + " ns");
    }
}
