package graphe.grapheS;

import java.util.ArrayList;

/**
 * Classe Sommet
 * Possede en attribut une liste des sommets voisins
 */
public class Sommet {

    //TODO: check type list
    public ArrayList<Sommet> sommetsVoisins;

    /**
     * Constructeur d'un sommet
     * @param sommets La liste de sommets voisins
     */
    public Sommet(ArrayList<Sommet> sommets){
        this.sommetsVoisins = sommets;
        //TODO: Exceptions sur l'arraylist
    }

    /**
     * Constructeur d'un sommet
     */
    public Sommet(){
        this.sommetsVoisins = new ArrayList<>();
    }
}
