package graphe.grapheS;

import java.util.Vector;

/**
 * Classe Sommet
 * Possede en attribut une liste des sommets voisins
 */
public class Sommet {

    private int id;
    Vector<Sommet> voisins=new Vector<>();

    /**
     * Constructeur d'un sommet
     * @param id Le numero du sommet
     */
    public Sommet(int id) throws IllegalArgumentException {
        if(id < 0) throw new IllegalArgumentException("Valeur de sommet negative impossible");
        this.id = id;
    }

    /**
     * Ajoute un sommet dans la liste des sommets voisins
     * @param s Le sommet a ajouter
     */
    void addVoisin(Sommet s) {
        voisins.add(s);
    }

    /**
     * Vérifie si le sommet rentre en parametre est bien un voisin
     * @param s Le voisin potentiel
     * @return true si voisin, false sinon
     */
    boolean isVoisin(Sommet s) {
        return voisins.contains(s);
    }

    @Override
    public String toString() {
        String str = id + ": [";
        for (int i = 0; i < voisins.size() - 1; i++) str += voisins.get(i).id + ", ";
        return str + voisins.get(voisins.size()-1).id + "]";
    }
}
