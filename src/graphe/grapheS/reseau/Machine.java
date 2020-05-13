package graphe.grapheS.reseau;

import graphe.grapheS.Sommet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Une classe machine qui possede un attribut ip
 */
public class Machine extends Sommet {

    /**
     * Enum des differents types de machine
     */
    enum Type {
        PC("PC"),
        ROUTER("ROUTEUR");

    private String type;

        /**
         * Constructeur de l'enum
         * @param type Le type de la machine
         */
    Type(String type){
        this.type = type;
    }

        /**
         * Methode toString du type de la machine
         * @return Le type de la machine
         */
    public String toString(){
        return this.type;
    }

        /**
         * Getteur du type de la machine
         * @return Le type de la machine
         */
    public String getType(){
        return this.type;
    }
    }

    private Ip myip;
    private Type type;
    private ArrayList<String> logs;

    /**
     * Constructeur d'un sommet similaire a celui d'un simple sommet
     * @param id Le numero du sommet
     * @param type True: type de la machine = routeur / False: type de la machine = PC (pas evolutif)
     */
    public Machine(int id, Type type) {
        super(id);
        this.type = type;
        this.logs = new ArrayList<>();
        this.logs.add(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date())+" : Creation "+this.type.getType()+" "+this.getId());
    }

    /**
     * Setter de l'ip de la machine
     * @param myip l'ip de la machine
     */
    public void setIp(Ip myip){
        this.myip = new Ip(myip);
        this.setLabel(myip.toString()); // ajout du label
        this.logs.add(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date())+" : Ajout IP : "+this.getIp());
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

    /**
     * Getteur de la liste des logs d'une machine
     * @return Liste des logs d'une machine
     */
    public ArrayList<String> getLogs(){
        return this.logs;
    }

    /**
     * Affichage des logs d'une machine
     */
    public void printLogs(){
        System.out.println("Liste des logs de la machine : "+this.type.getType()+" "+this.getId());
        for(String l: this.logs) System.out.println(l);
    }
}
