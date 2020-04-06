package graphe.grapheS.reseau;

import graphe.grapheS.Sommet;

/**
 * Une classe de sommets qui correspondent a des machines dans un reseau
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

    public void setIp(Ip myip){
        this.myip = new Ip(myip);
    }
}
