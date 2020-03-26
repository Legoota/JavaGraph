package graphe.grapheS;

import exceptions.BadSizeGrapheException;
import graphe.Graphe;
import java.util.Vector;

/**
 * Classe d'un graphe contenant des sommets
 */
public class GraphS implements Graphe {

    private int taille;
    private Vector<Sommet> sommets = new Vector<>();

    /**
     * Constructeur d'un Graphe utilisant des sommets.
     */
    public GraphS(int taille) throws BadSizeGrapheException {
        if(taille < 0) throw new BadSizeGrapheException(taille);
        this.taille = taille;
        for(int i = 0; i < taille; i++) this.sommets.add(new Sommet(i));
    }

    /**
     * Ajoute une arete entre deux sommets
     * @param i Premier sommet
     * @param j Deuxieme sommet
     * @throws IllegalArgumentException Sommet non valide
     */
    public void addArete(int i, int j) throws IllegalArgumentException {
        if(i < 0 || j < 0) throw new IllegalArgumentException("Un des sommets est negatif");
        this.sommets.get(i).addVoisin(this.sommets.get(j));
    }

    @Override
    public boolean isVoisin(int i, int j) throws IllegalArgumentException {
        if (i < 0 || j < 0) throw new IllegalArgumentException("Un des sommets est negatif");
        return sommets.get(i).isVoisin(sommets.get(j));
    }

    @Override
    public int distance(int i, int j) {
        return 0;
    }

    @Override
    public String toString(){
        String str = "";
        for(int i = 0; i < this.taille; i++) str += sommets.get(i).toString() + "\n";
        return str;
    }
}
