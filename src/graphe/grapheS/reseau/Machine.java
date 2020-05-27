package graphe.grapheS.reseau;

import graphe.grapheS.Sommet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Une classe machine qui possede un attribut ip
 */
public class Machine extends Sommet {

    /**
     * Enum des differents types de machine
     */
    public enum Type {
        PC("PC"),
        TELEPHONE("TELEPHONE");

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
    private HashMap<Ip,ArrayList<String>> messages;

    /**
     * Constructeur d'un sommet similaire a celui d'un simple sommet
     * @param id Le numero du sommet
     * @param type True: type de la machine = routeur / False: type de la machine = PC (pas evolutif)
     */
    public Machine(int id, Type type) {
        super(id);
        this.type = type;
        this.logs = new ArrayList<>();
        this.messages = new HashMap<>();
        this.logs.add(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date())+" : Creation "+this.type.getType()+" "+this.getId());
    }

    /**
     * Setter de l'ip de la machine
     * @param ip l'ip de la machine
     */
    public void setIp(Ip ip){
        if(this.myip == null) this.logs.add(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date())+" : Ajout IP : "+ip);
        this.myip = new Ip(ip);
        this.setLabel(ip.toString()); // ajout du label
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
     * Getter de la HashMap de messages
     * @return La HashMap de messages
     */
    public HashMap<Ip,ArrayList<String>> getMessages(){
        return this.messages;
    }

    /**
     * Getter de l'ArrayList de messages de la machine d'adresse IP ip
     * @param ip L'adresse IP de la machine expediteur
     * @return L'ArrayList des messages de la machine IP
     */
    public ArrayList<String> getMessagesFromIp(Ip ip) {
        return this.messages.containsKey(ip) ? this.messages.get(ip) : null;
    }

    /**
     * Methode permettant l'envoi d'un message a un destinataire
     * @param message Le message
     * @param destinataire Le destinataire du message
     * @param r Le routeur cible
     * @return True si le message a ete transmis, false sinon
     */
    public boolean sendMessage(String message, Ip destinataire, Routeur r) {
        return r.message(message, this.myip, destinataire);
    }

    /**
     * Methode permettant de receptionner un message et de l'ajouter a la liste des messages
     * @param message Le message
     * @param expediteur L'expediteur du message
     */
    public void getMessage(String message, Ip expediteur) {
        if (!this.messages.containsKey(expediteur)) {
            this.messages.put(expediteur, new ArrayList<>());
            this.logs.add(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date())+" : Ajout d'une nouvelle conversation avec l'adresse IP "+expediteur.toString());
        }
        this.messages.get(expediteur).add(message);
    }

    /**
     * Methode de DHCM permettant d'attribuer une nouvelle adresse IP a partir de la liste des adresses IP du reseau
     * @param r Le reseau auquel appartient la machine
     */
    public void DHCPM(Routeur r){
        Ip newIp = r.DHCPM();
        this.logs.add(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date())+" : Appel DHCP : Attribution de l'adresse IP : " + newIp);
        this.setIp(newIp);
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

    /**
     * Affichage des messages d'une machine
     */
    public void printMessages(){
        StringBuilder sb = new StringBuilder();
        sb.append("Messages :\n");
        this.messages.forEach((k,v) -> {
            sb.append("Conversation avec "+k.toString()+" :\n");
            v.forEach(s -> sb.append(s+"\n"));
        });
        System.out.println(sb);
    }

    @Override
    public String toString(){
        return super.toString()+"\n@IP : "+this.myip + "\nType : " + this.type.toString();
    }
}
