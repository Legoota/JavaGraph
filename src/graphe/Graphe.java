package graphe;

/**
 * Interface representative d'un graphe.
 */
public interface Graphe {

    /**
     * Fonction permettant de voir si deux sommets sont voisins.
     * @param i Le premier sommet a tester.
     * @param j Le deuxieme sommet a tester.
     * @return <b>True</b> si les deux sommets sont voisins, <b>False</b> sinon.
     */
    boolean isVoisin(int i, int j);

}
