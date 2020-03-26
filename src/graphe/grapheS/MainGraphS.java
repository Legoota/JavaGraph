package graphe.grapheS;

/**
 * Classe de test d'un graphe avec sommets
 */
public class MainGraphS {

    /**
     * Main test pour graphe avec sommets
     * @param args Arguments du main
     */
    public static void main(String[] args){

        GraphS graphe = new GraphS(6);
        graphe.addArete(0,1);
        graphe.addArete(2,3);
        graphe.addArete(2,1);
        graphe.addArete(1,3);
        graphe.addArete(4,0);
        System.out.println(graphe.toString());

        System.out.println("Distance entre 4 et 3: " + graphe.distance(4,3));
        System.out.println("Distance entre 1 et 0: " + graphe.distance(1,0));
        System.out.println("Distance entre 0 et 2: " + graphe.distance(0,2));
        System.out.println("Distance (non valide) entre 0 et 5: " + graphe.distance(0,5));
    }
}
