package graphe.grapheAdj;

import matrice.Matrice;

/**
 * Classe de test d'un graphe avec matrice d'adjacence
 */
public class MainGraphAdj {

    /**
     * Main test pour graphe avec matrice d'adjacence
     * @param args Arguments du main
     * @author Leo Boulard
     */
    public static void main(String[] args){
        int[][] mat =  {{1,1,1,0,1,1,0,0,0},
                        {1,1,0,1,0,0,0,0,0},
                        {1,0,1,0,0,0,0,0,0},
                        {0,1,0,1,0,1,1,0,0},
                        {1,0,0,0,1,0,0,0,0},
                        {1,0,0,1,0,1,1,0,0},
                        {0,0,0,1,0,1,1,1,0},
                        {0,0,0,0,0,0,1,1,1},
                        {0,0,0,0,0,0,0,1,1}};
        Matrice m = new Matrice(mat);
        GraphAdj graphe = new GraphAdj(m);
        //TODO: au choix pour les diagonales: convention = 0 car pour boucle = 1

        System.out.println(graphe.isVoisin(2,3));// Test de la fonction isVoisin (cas NOK)
        System.out.println(graphe.isVoisin(4,0));// Test de la fonction isVoisin (cas OK)
        System.out.println(graphe.distance(4,8));// Test de la fonction distance
    }
}
