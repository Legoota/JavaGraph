package graphe.grapheAdj;

import graphe.Graphe;
import matrice.Matrice;

public class GraphAdj implements Graphe {

    private Matrice matrice;
    private int ordre;

    /**
     * Constructeur d'un graphe avec matrice d'adjacence
     * @param matAdj La matrice d'adjacence
     */
    public GraphAdj(Matrice matAdj){
        this.matrice = matAdj;
        this.ordre = matAdj.ordre;
    }

    @Override
    public boolean isVoisin(int i, int j) {
        return this.matrice.get(i,j) == 1;
    }

    @Override
    public int distance(int i, int j) {

        return 0;
    }
}
