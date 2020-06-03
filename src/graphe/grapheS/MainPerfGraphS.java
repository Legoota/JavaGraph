package graphe.grapheS;

import json.Import;

import java.io.FileNotFoundException;

public class MainPerfGraphS {

    public static void main(String[] args) {
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

        GraphS arctic = null;

        // Import d'un fichier JSON consequent
        try {
            arctic = Import.fetchGraphS("arctic.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Tests sur le graphS arctic
        long startTimeArctic1 = System.currentTimeMillis();
        arctic.BFS();
        long finishTimeArctic1 = System.currentTimeMillis();
        System.out.println("Fonction BFS pour Arctic = " + (finishTimeArctic1 - startTimeArctic1) + " ms");

        long startTimeArctic2 = System.currentTimeMillis();
        arctic.DFS();
        long finishTimeArctic2 = System.currentTimeMillis();
        System.out.println("Fonction DFS pour Arctic = " + (finishTimeArctic2 - startTimeArctic2) + " ms");

        // Tests sur les fonctions BFS et DFS
        long startTime3 = System.nanoTime();
        gs.BFS();
        long finishTime3 = System.nanoTime();
        System.out.println("Fonction BFS pour GraphS = " + (finishTime3 - startTime3) + " ns");

        long startTime4 = System.nanoTime();
        gs.DFS();
        long finishTime4 = System.nanoTime();
        System.out.println("Fonction DFS pour GraphS = " + (finishTime4 - startTime4) + " ns");
    }
}
