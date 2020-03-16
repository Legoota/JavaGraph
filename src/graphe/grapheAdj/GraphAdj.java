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
        // TODO: Ajouter exceptions, cad throws new + differents types dans un try catch
        // TODO: Changer signature fonction pour lancer les erreurs
        // TODO: Ajouter tests unitaires JUnit pour verifier: cas sommet negatif, cas sommet > ordre, cas cyclique (k >> ordre)
        if(i == j) return 0; // Cas meme sommet
        if(i >= this.ordre || j >= this.ordre || i < 0 || j < 0) return -2; // Cas sommet non existant
        if(this.matrice.get(i,j) != 0) return 1; // Cas sommets directement lies par arete

        Matrice temp = new Matrice(this.matrice.getArray()); //eviter de faire une reference a l'objet (=matrice originale) pour ne pas la modifier directement
        int k = 0;
        while((temp.get(i, j) == 0)){
            if(k > this.ordre) return -1; // Cas arbre
            temp = this.matrice.puissance(k+1);
            k++;
        }
        return k;//temp.get(i,j); // = nombre de parcours pour chemin de distance k
    }
}
