package graphe.grapheS;

import java.util.ArrayList;

/**
 * Classe Sommet
 * Possede en attribut une liste des sommets voisins
 */
public class Sommet {

    //TODO: changer l'arraylist en vecteur
    public ArrayList<Sommet> sommetsVoisins;

    /**
     * Constructeur d'un sommet
     * @param sommets La liste de sommets voisins
     */
    public Sommet(ArrayList<Sommet> sommets){
        this.sommetsVoisins = sommets;
        //TODO: Exceptions sur le vecteur
    }

    /**
     * Constructeur d'un sommet
     */
    public Sommet(){
        this.sommetsVoisins = new ArrayList<>();
    }
}
