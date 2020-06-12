package graphe.grapheS;

import json.Import;

import java.io.FileNotFoundException;

/**
 * Classe de tests de performances de la classe GraphS
 */
public class MainPerfGraphS {

    /**
     * Main de tests de performances de la classe GraphS
     * @param args Arguments du main
     */
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

        long startTimeArctic3 = System.currentTimeMillis();
        arctic.deleteSommet(1); // Sommet 1 possede beaucoup de voisins
        long finishTimeAcrtic3 = System.currentTimeMillis();
        System.out.println("Suppression du sommet 1 pour Arctic = " + (finishTimeAcrtic3 - startTimeArctic3) + " ms");

        // Tests sur les fonctions BFS et DFS
        long startTime1 = System.nanoTime();
        gs.BFS();
        long finishTime1 = System.nanoTime();
        System.out.println("Fonction BFS pour GraphS = " + (finishTime1 - startTime1) + " ns");

        long startTime2 = System.nanoTime();
        gs.DFS();
        long finishTime2 = System.nanoTime();
        System.out.println("Fonction DFS pour GraphS = " + (finishTime2 - startTime2) + " ns");

        long startTime3 = System.nanoTime();
        gs.BFSPath(gs.getSommetbyId(0),gs.getSommetbyId(8));
        long finishTime3 = System.nanoTime();
        System.out.println("Fonction BFSPath entre sommet 0 et sommet 8 pour GraphS = " + (finishTime3 - startTime3) + " ns");
    }
}
