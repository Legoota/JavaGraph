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
        //TODO: Ajouter des tests sur les graphes

        GraphS graphe = new GraphS(5);
        graphe.addArete(0,1);
        graphe.addArete(2,3);
        graphe.addArete(2,1);
        graphe.addArete(1,3);
        graphe.addArete(4,0);
        System.out.println(graphe.toString());
    }
}
