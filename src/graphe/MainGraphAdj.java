package graphe;

import matrice.Matrice;

public class MainGraphAdj {

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
