package graphe;

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

        return false;
    }
}
