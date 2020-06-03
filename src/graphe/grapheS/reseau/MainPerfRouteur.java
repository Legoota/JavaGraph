package graphe.grapheS.reseau;

import exceptions.BadSizeGrapheException;
import graphe.grapheS.GraphS;

public class MainPerfRouteur {

    public static void main(String[] args){
        Routeur R = new Routeur(1,10,new Ip(1,1,1,1),new Ip(192,168,1,1),5);
        Machine m = new Machine(15,Machine.Type.PC);
        Machine m2 = new Machine(20,Machine.Type.PC);

        long startTime1 = System.nanoTime();
        R.DHCP();
        long finishTime1 = System.nanoTime();
        System.out.println("Execution de la methode DHCP : " + (finishTime1 - startTime1) + " ns");

        long startTime2 = System.nanoTime();
        R.addMachine(m);
        long finishTime2 = System.nanoTime();
        System.out.println("Temps d'ajout de la machine m au reseau : " + (finishTime2 - startTime2) + " ns");

        R.addMachine(m2);//ajout seconde machine

        long startTime3 = System.nanoTime();
        m.sendMessage("Test venant de M",R.getMachineById(20).getIp(),R);
        long finishTime3 = System.nanoTime();
        System.out.println("Temps d'envoi d'un message entre m et m2 : " + (finishTime3 - startTime3) + " ns");

        long startTime4 = System.nanoTime();
        m.DHCPM(R);
        long finishTime4 = System.nanoTime();
        System.out.println("Execution de la methode DHCPM sur la machine m : " + (finishTime4 - startTime4) + " ns");



    }
}
