package graphe.grapheS.reseau;

/**
 * Classe de test de la classe Reseau
 */
public class MainReseau {

    /**
     * Main de test de la classe Reseau
     * @param args Arguments du main
     */
    public static void main(String[] args){

        Reseau r = new Reseau(10,5);

        System.out.println("Main classe Reseau");
        System.out.println("Affichage du reseau r:");
        System.out.println(r.toString()); // expected : "reseau de 10 routeurs" + Routeur 'id', @ext, @reseau, taille


        Ip ipexterne = new Ip(10,10,10,10);
        Ip ipinterne = new Ip(192,168,10,0);
        Reseau r2 = new Reseau(5,5,ipexterne,ipinterne,24);
        System.out.println("Affichage du reseau r2:");
        System.out.println(r2.toString()); // expected : "reseau de 5 routeurs" + Routeur 'id' + @ext (a partir de 10.10.10.11), @reseau (192.168.10.0), taille

        System.out.println("Ajout / suppression d'un routeur du reseau r:");
        Routeur ajouter = new Routeur(100,2,new Ip(1,1,1,100), new Ip(192,168,1,100),24);
        r.addRouteur(ajouter);
        System.out.println("Affichage du routeur ajoute precedemment:");
        System.out.println(r.getRouteurFromId(100).toString()); // expected : affichage de 'Routeur 100' et de ses 2 machines
        System.out.println("Suppression du routeur d'id 100 : " + r.deleteRouteur(new Ip(1,1,1,100))); // expected : true

        try{
            System.out.print("Essai d'obtention du routeur d'id 100 (@ext = 1.1.1.100) : ");
            r.getRouteurFromIp(new Ip(1,1,1,100)); // attendu : IllegalArgumentException
        } catch(IllegalArgumentException e){
            System.out.println("Exception levée = " + e.toString());
        }
    }
}
