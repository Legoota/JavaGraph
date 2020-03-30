package graphe.grapheS;

import exceptions.BadSizeGrapheException;
import graphe.Graphe;

import java.util.LinkedList;
import java.util.Vector;

/**
 * Classe d'un graphe contenant des sommets
 */
public class GraphS implements Graphe {

    private int taille;
    private Vector<Sommet> sommets = new Vector<>();

    /**
     * Constructeur d'un Graphe utilisant des sommets.
     * @param taille taille du graphe
     * @throws IllegalArgumentException taille non valide (negative)
     */
    public GraphS(int taille) throws BadSizeGrapheException {
        if(taille < 0) throw new BadSizeGrapheException(taille);
        this.taille = taille;
        for(int i = 0; i < taille; i++) this.sommets.add(new Sommet(i));
    }

    /**
     * Getter permettant de recuperer la taille du graphe
     * @return La taille du graphe
     */
    public int getTaille() {
        return this.taille;
    }

    /**
     * Getter permettant de recuperer le vecteur de sommets du graphe
     * @return Le vecteur de sommets du graphe
     */
    public Vector<Sommet> getSommets() {
        return this.sommets;
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
    public int distance(int i, int j) throws IllegalArgumentException {
        if (i < 0 || j < 0) throw new IllegalArgumentException("Au moins un des sommets est negatif");
        LinkedList<Integer> l = new LinkedList<>();
        l.add(i);
        sommets.get(i).setFlag(true);
        LinkedList<Integer> ldistance = new LinkedList<>();
        ldistance.add(0);

        while(l.size() != 0) {
            Sommet sommetCourant = sommets.get(l.getFirst());
            for(int x = 0; x<sommetCourant.getVoisins().size(); x++) {
                Sommet voisinCourant = sommetCourant.getVoisins().get(x);
                if (voisinCourant.getId() == j){
                    for(int y = 0; y < this.taille; y++) { this.sommets.get(y).setFlag(false); } // Reinitialisation du flag
                    return ldistance.getFirst()+1;
                }
                if(!voisinCourant.getFlag()){
                    voisinCourant.setFlag(true);
                    l.add(voisinCourant.getId());
                    ldistance.add(ldistance.getFirst()+1);
                }
            }
            l.remove();
            ldistance.remove();
        }

        return -1; // Cas ou pas de chemin entre les sommets
    }

    /**
     * Methode realisant un graphe BFS a partir d'un graphS
     * @return Un graphS BFS
     */
    public GraphS BFS() {
        GraphS bfs = new GraphS(this.taille);
        LinkedList<Integer> l = new LinkedList<>();
        l.add(0);
        this.sommets.get(0).setFlag(true);
        while(l.size() != 0) {
            Sommet sommetCourant = this.sommets.get(l.getFirst());
            for(int i = 0; i < sommetCourant.getVoisins().size(); i++){
                Sommet voisinCourant = sommetCourant.getVoisins().get(i);
                if(!voisinCourant.getFlag()){
                    bfs.addArete(sommetCourant.getId(),voisinCourant.getId());
                    voisinCourant.setFlag(true);
                    l.add(voisinCourant.getId());
                }
            }
            l.remove();
        }
        for(int j = 0; j < this.taille; j++) this.sommets.get(j).setFlag(false);

        return bfs;
    }

    /**
     * Methode realisant un graph DFS a partie d'un graphS
     * @return Un graphe DFS
     */
    public GraphS DFS() {
        GraphS dfs = new GraphS(this.taille);
        LinkedList<Integer> l = new LinkedList<>();
        l.add(0);
        this.sommets.get(0).setFlag(true);
        LinkedList<Integer> lposition = new LinkedList<>();
        lposition.add(0);
        while(l.size()!=0){
            Sommet sommetCourant = sommets.get(l.getLast());
            if(sommetCourant.getVoisins().size()==lposition.getLast()){
                l.removeLast();
                lposition.removeLast();
            }
            else{
                Sommet voisinCourant = sommetCourant.getVoisins().get(lposition.getLast());
                lposition.set(lposition.size()-1,lposition.getLast()+1);
                if(!voisinCourant.getFlag()){
                    dfs.addArete(sommetCourant.getId(),voisinCourant.getId());
                    voisinCourant.setFlag(true);
                    l.add(voisinCourant.getId());
                    lposition.add(0);
                }
            }
        }
        for (int i=0; i<taille;i++)sommets.get(i).setFlag(false);
        return dfs;
    }

    @Override
    public String toString(){
        String str = "";
        for(int i = 0; i < this.taille; i++) str += sommets.get(i).toString() + "\n";
        return str;
    }
}
