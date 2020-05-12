package graphe.grapheS.reseau;

import graphe.grapheS.Sommet;

/**
 * Une classe machine qui possede un attribut ip
 */
public class Machine extends Sommet {

    enum Type {
        PC("PC"),
        ROUTER("ROUTEUR");

    private String type;

    Type(String type){
        this.type = type;
    }

    public String toString(){
        return this.type;
    }

    public String getType(){
        return this.type;
    }
    }

    private Ip myip;
    private Type type;

    /**
     * Constructeur d'un sommet similaire a celui d'un simple sommet
     * @param id Le numero du sommet
     * @param type True: type de la machine = routeur / False: type de la machine = PC => pas evolutif
     */
    public Machine(int id, Type type) {
        super(id);
        this.type = type;
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
     * Getter du type de la machine
     * @return le type de la machine
     */
    public Type getType(){
        return this.type;
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
