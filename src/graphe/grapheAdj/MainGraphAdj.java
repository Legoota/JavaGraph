package graphe.grapheAdj;

import matrice.Matrice;

/**
 * Classe de test d'un graphe avec matrice d'adjacence
 */
public class MainGraphAdj {

    /**
     * Main test pour graphe avec matrice d'adjacence
     * @param args Arguments du main
     */
    public static void main(String[] args){
        int[][] mat =  {{0,1,1,0,1,1,0},
                        {1,0,0,0,1,1,0},
                        {1,0,0,0,1,0,0},
                        {0,0,0,0,1,0,0},
                        {1,1,1,1,0,0,0},
                        {1,1,0,0,0,0,1},
                        {0,0,0,0,0,1,0}};
        Matrice m = new Matrice(mat);
        GraphAdj graphe = new GraphAdj(m);
    }
}
