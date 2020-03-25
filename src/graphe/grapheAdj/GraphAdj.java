package graphe.grapheAdj;

import graphe.Graphe;
import matrice.Matrice;

/**
 * Classe Graphe utilisant matrice d'adjacence
 */
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
    public boolean isVoisin(int i, int j) throws IllegalArgumentException {
        if(0 <= i && 0 <= j && i < this.ordre && j < this.ordre) return this.matrice.get(i,j) == 1;
        else throw new IllegalArgumentException("Les sommets n'existent pas !");
    }

    @Override
    public int distance(int i, int j) throws IllegalArgumentException, IndexOutOfBoundsException {
        if(i >= this.ordre || j >= this.ordre || i < 0 || j < 0) throw new IllegalArgumentException("Les sommets n'existent pas !");
        if(i == j) return 0; // Cas meme sommet
        if(this.matrice.get(i,j) != 0) return 1; // Cas sommets directement lies par arete

        Matrice temp = new Matrice(this.matrice); //eviter de faire une reference a l'objet (=matrice originale) pour ne pas la modifier directement
        int k = 0;
        while((temp.get(i, j) == 0)){
            if(k > this.ordre) throw new IndexOutOfBoundsException("Ces sommets ne sont pas relies");
            temp = this.matrice.puissance(k+1);
            k++;
        }
        return k; //temp.get(i,j); // = nombre de parcours pour chemin de distance k
    }
}
