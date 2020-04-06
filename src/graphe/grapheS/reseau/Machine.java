package graphe.grapheS.reseau;

import graphe.grapheS.Sommet;

/**
 * Une classe machine qui possede un attribut ip
 */
public class Machine extends Sommet {

    private Ip myip;

    /**
     * Constructeur d'un sommet similaire a celui d'un simple sommet
     * @param id Le numero du sommet
     */
    public Machine(int id) {
        super(id);
    }

    /**
     * Setter de l'ip de la machine
     * @param myip l'ip de la machine
     */
    public void setIp(Ip myip){
        this.myip = new Ip(myip);
    }

    /**
     * Getter de l'adresse ip de la machine
     * @return une l'ip de la machine
     */
    public Ip getIp(){
        return this.myip;
    }
}
