package graphe.grapheS;

/**
 * Classe de test d'un graphe avec sommets
 */
public class MainGraphS {

    /**
     * Main test pour graphe avec sommets
     * @param arg Arguments du main
     */
    public static void main(String[] arg){

        GraphS graphe = new GraphS(10);
        graphe.addArete(0,1);
        graphe.addArete(0,2);
        graphe.addArete(0,4);
        graphe.addArete(0,6);
        graphe.addArete(0,9);
        graphe.addArete(1,2);
        graphe.addArete(1,7);
        graphe.addArete(2,5);
        graphe.addArete(4,7);
        graphe.addArete(7,8);
        System.out.println("Main classe GraphS");
        System.out.println("Affichage du graphe initial:");
        System.out.println(graphe.toString()+"\n");
        System.out.println("Affichage du BFS du graphe:");
        System.out.println(graphe.BFS().toString()+"\n");
        System.out.println("Affichage du DFS du graphe:");
        System.out.println(graphe.DFS().toString()+"\n");

        System.out.println("Distance entre deux sommets");
        System.out.println("Distance entre 0 et 5 = " + graphe.distance(0,5)); // attendu : 2
        System.out.println("Distance entre 0 et 3 = " + graphe.distance(0,3)); // attendu : -1

        System.out.println("\nVoisinage entre deux sommets");
        System.out.println("Fonction isVoisin entre 0 et 1 = " + graphe.isVoisin(0,1)); // attendu : true
        System.out.println("Fonction isVoisin entre 0 et 5 = " + graphe.isVoisin(0,5)); // attendu  : false

        System.out.println("\nChemin le plus court entre deux sommets");
        System.out.println("Chemin le plus court entre 0 et 8 = " + graphe.BFSPath(graphe.getSommetbyId(0), graphe.getSommetbyId(8))); // attendu : [8,7,1,0]
        try{
            System.out.print("Chemin le plus court entre 0 et 3 : ");
            graphe.BFSPath(graphe.getSommetbyId(0), graphe.getSommetbyId(3)); // attendu : IllegalArgumentException
        } catch(IllegalArgumentException e){
            System.out.println("Exception levée = " + e.toString());
        }

        System.out.println("\nAjout / suppression de sommets");
        Sommet sa = new Sommet(100);
        graphe.addSommet(sa); // ajout du sommet d'ID 100
        System.out.println("Sommet d'ID 100 ajouté: " + graphe.getSommetbyId(100).toString()); // attendu : 100 (l'id du sommet)
        System.out.println("Suppression du sommet d'ID 100 : " + graphe.deleteSommet(sa)); // attendu: true

        System.out.println("\nAjout / suppression d'aretes");
        System.out.println("Suppression de l'arete entre 7 et 8 : " + graphe.deleteArete(7,8)); // attendu: true
        System.out.println("Affichage du sommet 8 : " + graphe.getSommetbyId(8).toString()); // attendu : 8 (sans le voisin 7)

        System.out.println("Suppression d'un sommet contenant des aretes");
        System.out.println("Suppression du sommet 0 : " + graphe.deleteSommet(0)); // attendu : true
        System.out.println("Affichage du graphe apres suppression du sommet 0:");
        System.out.println(graphe.toString()); // attendu : le graphe sans le sommet 0, sans les aretes reliees a 0
    }
}
