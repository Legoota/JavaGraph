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
        System.out.println("Main classe Routeur");
        System.out.println("Ajout d'un routeur d'ip externe 1.1.1.1, contenant 10 machines d'ip 192.168.1.X, masque 24");
        Routeur routeur = new Routeur(1,10,new Ip(1,1,1,1),new Ip(192,168,1,1),24);
        System.out.println("Affichage de "+routeur.toString());

        System.out.println("L'adresse du routeur est : "+routeur.getReseau());
        System.out.println("L'adresse de broadcast est : "+routeur.getBroadcast());
        System.out.println("Le masque est :"+routeur.getMasque()+"\n");

        System.out.println("Appel de la methode DHCP");
        routeur.DHCP();//renseigne le adresses ip des machines connectees au routeur
        System.out.println(routeur.toString());

        Machine machine = new Machine(100,Machine.Type.PC);
        routeur.addMachine(machine);
        System.out.println("Ajout de la machine d'id "+machine.getId());
        System.out.println(routeur.toString());

        System.out.println("Demmande de reattribution d'adresse ip pour la machine d'id "+machine.getId());
        machine.DHCPM(routeur);//donne la premiere adresse ip disponible
        System.out.println("Nouvelle adresse ip de la machine d'id "+machine.getId()+" : "+machine.getIp());

        System.out.println("\nMesssagerie");
        System.out.println("Envoi d'un message de la machine d'adresse "+machine.getIp().toString()+" a la machine d'ip "+routeur.getMachineById(1).getIp().toString());
        machine.sendMessage("Bonjour, voici mon message ! ;) ",routeur.getMachineById(1).getIp(),routeur);//envoi d'un message de la machine "machine" a la machine d'id 1
        System.out.println("Recuperation du message sur la machine d'ip "+ routeur.getMachineById(1).getIp()+" : "+routeur.getMachineById(1).getMessages()+"\n");

        System.out.println("Affichage des logs de la machine \n"+machine.getIp().toString());
        machine.printLogs();

        System.out.println("\nSuppression de la machine d'id "+machine.getId());
        routeur.deleteMachine(machine);//suppression d'une machine
        System.out.println("Suppression de la machine d'ip "+routeur.getMachineById(5).getIp());
        routeur.deleteMachine(routeur.getMachineByIp(new Ip(192,168,1,6)).getId());//suppression d'une machine par son id
        System.out.println("\nNouvel affichage des machine du routeur");
        System.out.println(routeur.toString());
    }
}
