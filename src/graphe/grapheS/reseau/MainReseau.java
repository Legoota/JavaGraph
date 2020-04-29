package graphe.grapheS.reseau;

import exceptions.BadSizeGrapheException;
import graphe.grapheS.GraphS;
import graphe.grapheS.Sommet;
import json.Export;

/**
 * Classe de test classe Reseau
 */
public class MainReseau {

    /**
     * Main de test de la classe Reseau
     * @param args Arguments du Main
     */
    public static void main(String[] args) {
        try {
            int size = 500;
            GraphS g = new GraphS(size);
            Reseau R = new Reseau(size, new Ip(110, 23, 12, 12), 20);
            for (int i = 0; i < size - 2; i++) {
                g.addArete(i, i + 1);
                R.addArete(i, i + 1);
                g.addArete(i, i + 2);
                R.addArete(i, i + 2);
                //G.addArete(i,i+10);GA.addArete(i,i+10);
            }
            g.addArete(0, 5);
            R.addArete(0, 5);
            //System.out.println(g);
            GraphS bfsG = g.BFS();
            //System.out.println(bfsG);
            GraphS dfsG = g.DFS();
            //System.out.println(dfsG);
            long tdebut = System.currentTimeMillis();
            int d = g.distance(0, 300);
            long tfin = System.currentTimeMillis();
            R.DHCP();
            //System.out.println(d + " en " + (tfin - tdebut) + " ms.");
            //System.out.println(R.getMachineByIp(new Ip(110, 23, 0, 10)).getId());
            //System.out.println(R.getMachineByIp(new Ip(110, 23, 1, 10)).getId());
            System.out.println("Reseau : "+R.getReseau());
            System.out.println("Broadcast : "+R.getBroadcast());
            System.out.println("@IP PC id 100 : "+R.getMachineById(100).getIp());
            System.out.println("@ID PC ip 110.23.1.10 : "+R.getMachineByIp(new Ip(110, 23, 1, 10)).getId());
            //Export.exportToJson(R,"reseau");

        } catch (BadSizeGrapheException e) {
            System.out.println(e);
        }


        Ip machine = new Ip(192, 168, 1, 1);
        Ip masque = new Ip(255, 255, 0, 0);
        System.out.print("Le reseau de la machine "+machine+" (masque : "+masque+") est : ");
        System.out.println(machine.getReseau(masque));

    }
}
