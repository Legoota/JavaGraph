package graphe.grapheS.reseau;

public class MainReseau {

    public static void main(String[] args){

        Reseau r = new Reseau(10,5);
        System.out.println("Reseau:\n"+r.toString());
        System.out.println("Get routeur from id 0: "+r.getRouteurFromId(0).toString());
        r.deleteRouteur(r.getRouteurFromId(0).getExt());
        System.out.println("Nouveau reseau:\n"+r.toString());
        System.out.println("Get routeur from ip: "+r.getRouteurFromIp(new Ip(1,1,1,2)).toString());
        System.out.println("Suppression routeur echec: "+r.deleteRouteur(new Ip(1,1,2,1)));
        Routeur r1 = new Routeur(1,1,new Ip(1,1,1,1),new Ip(192,168,1,0),24);
        Routeur r2 = new Routeur(2,1,new Ip(1,1,1,2),new Ip(192,168,1,0),24);
        Routeur[] routeurs = {r1,r2};
        Reseau reseau2 = new Reseau(routeurs);
        System.out.println(reseau2.toString());
    }
}
