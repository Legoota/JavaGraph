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
        Matrice temp = new Matrice(this.ordre); //eviter de faire une reference a l'objet pour ne pas modifier la matrice originale
        temp = this.matrice;
        if(this.matrice.get(i,j) != 0) return 1;

        int k = 1;
        while((temp.get(i, j) == 0)){
            if(k > this.ordre) return -1;
            temp = temp.puissance(k);
            k++;
            temp.toString();
        }
        return k-1;// temp.get(i, j);
    }
}
