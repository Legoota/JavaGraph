package graphe.grapheS.reseau;

import exceptions.BadSizeGrapheException;

/**
 * Classe de test classe Routeur
 */
public class MainRouteur {

    /**
     * Main de test de la classe Routeur
     * @param args Arguments du Main
     */
    public static void main(String[] args) {
        try {
            /*int size = 500;
            GraphS g = new GraphS(size);
            Routeur R = new Routeur(size, new Ip(110, 23, 12, 12), 20);
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
            System.out.println("Routeur : "+R.getReseau());
            System.out.println("Broadcast : "+R.getBroadcast());
            System.out.println("@IP PC id 100 : "+R.getMachineById(100).getIp());
            System.out.println("@ID PC ip 110.23.1.10 : "+R.getMachineByIp(new Ip(110, 23, 1, 10)).getId());
            //Export.exportToJson(R,"reseau");*/

        } catch (BadSizeGrapheException e) {
            System.out.println(e);
        }


        /*Ip machine = new Ip(192, 168, 1, 1);
        Ip masque = new Ip(255, 255, 0, 0);
        System.out.print("Le reseau de la machine "+machine+" (masque : "+masque+") est : ");*/

        Routeur R = new Routeur(1,10,new Ip(1,1,1,1),new Ip(192,168,1,1),5);
        System.out.println("@Routeur: "+R.getReseau());
        System.out.println("@Broad: "+R.getBroadcast());
        System.out.println("@Masque: "+R.getMasque());
        R.DHCP();

        Machine m = new Machine(15,Machine.Type.PC);
        Machine m2 = new Machine(20,Machine.Type.PC);
        R.addMachine(m);
        R.addMachine(m2);
        System.out.println("NOUVEAU RESEAU: "+R.toString());
        m.DHCPM(R);
        m2.DHCPM(R);
        System.out.println(m2.getIp().toString());

        m.sendMessage("Test venant de M",R.getMachineById(20).getIp(),R);
        System.out.println(m2.getMessagesFromIp(new Ip(192,0,0,13)));
        m2.printLogs();
        m.sendMessage("Deuxieme test venant de M",R.getMachineById(20).getIp(),R);
        m2.printMessages();;
    }
}
