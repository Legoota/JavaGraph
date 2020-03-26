package graphe;

/**
 * Interface representative d'un graphe.
 */
public interface Graphe {

    /**
     * Fonction permettant de voir si deux sommets sont voisins.
     * @param i Le premier sommet a tester.
     * @param j Le deuxieme sommet a tester.
     * @throws IllegalArgumentException Sommet non valide
     * @return <b>True</b> si les deux sommets sont voisins, <b>False</b> sinon.
     */
    boolean isVoisin(int i, int j);

    /**
     * Renvoie la distance entre deux sommets
     * @param i Le sommet de depart
     * @param j Le sommet d'arrivee
     * @return La distance entre le premier sommet et le deuxieme sommet
     */
    int distance(int i, int j);

}
