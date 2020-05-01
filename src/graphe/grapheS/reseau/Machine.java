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
        this.setLabel(myip.toString()); // ajout du label
    }

    /**
     * Getter de l'adresse ip de la machine
     * @return une l'ip de la machine
     */
    public Ip getIp(){
        return this.myip;
    }

    /**
     * Methode qui renvoie le reseau de la machine en fonction de son masque
     * @param masque Masque
     * @return Adresse du reseau de la machine
     */
    public Ip getReseau(Ip masque){
        return new Ip(this.myip.getA() & masque.getA(),this.myip.getB() & masque.getB(),this.myip.getC() & masque.getC(),this.myip.getD() & masque.getD());
    }
}
