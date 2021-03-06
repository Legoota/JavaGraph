package graphe.grapheS.reseau;

import exceptions.BadIpException;
import exceptions.BadSizeGrapheException;
import graphe.grapheS.GraphS;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

/**
 * Classe correspondant a un routeur
 */
public class Routeur extends GraphS<Machine> {

    private int id;
    private Ip ext;
    private Ip reseau;
    private Ip broadcast;
    private Ip masque;
    private ArrayList<String> logs;

    /**
     * Constructeur qui cree un GraphS avec des sommets. Met a jour les attributs relatifs aux adresses dans le routeur
     * @param id Id du routeur
     * @param taille Taille du GraphS a generer
     * @param ext L'IP externe du routeur
     * @param ip Une IP contenue dans la plage disponible pour ce routeur
     * @param masque Le masque correspondant
     * @throws BadSizeGrapheException taille non valide (negative)
     */
    public Routeur(int id, int taille, Ip ext, Ip ip, int masque) throws BadSizeGrapheException {
        super();
        this.id = id;
        this.ext = ext;
        if(taille < 0) throw new BadSizeGrapheException(taille);
        this.setTaille(taille);
        Vector<Machine> machines = new Vector<>();
        for(int i = 0; i < taille; i++){ machines.add(new Machine(i,Machine.Type.PC)); }
        this.setSommets(machines);

        int partieHote = 32 - masque;
        if(partieHote < 8){
            this.reseau = new Ip(ip.getA(),ip.getB(),ip.getC(),(int)(ip.getD()-ip.getD()%(Math.pow(2,partieHote))));
            this.broadcast = new Ip(ip.getA(),ip.getB(),ip.getC(),(int)(ip.getD()-ip.getD()%(Math.pow(2,partieHote))+Math.pow(2,partieHote)-1));
            this.masque = new Ip(255,255,255,getValDecimaleOctet(masque-24));
        }
        else if (partieHote<16) {
            this.reseau = new Ip(ip.getA(),ip.getB(),(int)(ip.getC()-ip.getC()%(Math.pow(2, partieHote-8))),0);
            this.broadcast = new Ip(ip.getA(),ip.getB(),(int)(ip.getC()-ip.getC()%(Math.pow(2, partieHote-8))+Math.pow(2, partieHote-8)-1),255);
            this.masque = new Ip(255,255,getValDecimaleOctet(masque-16),0);
        }
        else if (partieHote<24) {
            this.reseau = new Ip(ip.getA(),(int)(ip.getB()-ip.getB()%(Math.pow(2, partieHote-16))),0,0);
            this.broadcast = new Ip(ip.getA(),(int)(ip.getB()-ip.getB()%(Math.pow(2, partieHote-16))+Math.pow(2, partieHote-16)-1),255,255);
            this.masque = new Ip(255,getValDecimaleOctet(masque-8),0,0);
        }
        else {
            this.reseau = new Ip((int)(ip.getA()-ip.getA()%(Math.pow(2, partieHote-24))),0,0,0);
            this.broadcast = new Ip((int)(ip.getA()-ip.getA()%(Math.pow(2, partieHote-24))+Math.pow(2, partieHote-24)-1),255,255,255);
            this.masque = new Ip(getValDecimaleOctet(masque),0,0,0);
        }
        this.logs = new ArrayList<>();
        this.logs.add(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date())+" : Creation d'un routeur de taille "+this.getTaille()+", d'adresse "+ip.toString()+"/"+masque);
    }

    /**
     * Methode permettant d'obtenir une valeur entre 0-255 a partir du nombre de bits a 1 (en partant du MSB)
     * @param masq Nombre de bits a 1
     * @return Valeur entre 0 et 255
     */
    private int getValDecimaleOctet(int masq){
        int res = 0;
        for(int i = masq; i > 0;i--) res += Math.pow(2,8-i);
        return res;
    }

    /**
     * Getter d'une Machine avec ID
     * @param id L'ID de la Machine
     * @return La Machine d'ID id
     * @throws IllegalArgumentException Id invalide (n'est pas dans la liste de sommets)
     */
    public Machine getMachineById(int id) throws IllegalArgumentException {
        for(Machine m: this.getSommets()) {
            if(m.getId() == id) return m;
        }
        throw new IllegalArgumentException("ID pas valide");
    }

    /**
     * Getter d'une Machine avec IP
     * @param ip L'IP de la Machine
     * @return La Machine d'IP ip
     * @throws BadIpException Ip invalide (n'est pas dans la liste de sommets)
     */
    public Machine getMachineByIp(Ip ip) throws BadIpException {
        for(Machine m:this.getSommets()){
            if(m.getIp().equals(ip)) return m;
        }
        throw new BadIpException(ip);
    }

    /**
     * Getter de l'ID du routeur
     * @return ID du routeur
     */
    public int getId() { return this.id; }

    /**
     * Getter de l'IP du reseau
     * @return IP du reseau
     */
    public Ip getReseau() { return this.reseau; }

    /**
     * Getter de l'IP externe du routeur
     * @return IP externe du routeur
     */
    public Ip getExt() { return this.ext; }

    /**
     * Getter de l'IP de broadcast du reseau du routeur
     * @return IP de broadcast du reseau du routeur
     */
    public Ip getBroadcast() { return this.broadcast; }

    /**
     * Getter de l'IP du masque du reseau du routeur
     * @return IP du masque du reseau du routeur
     */
    public Ip getMasque() { return this.masque; }

    /**
     * Methode simulant un serveur DHCP qui attribue des adresses en fonction de l'ID des machines
     */
    public void DHCP() throws BadIpException {
        Ip ipCourant = new Ip(this.reseau);
        ipCourant.increment();
        Vector<Machine> machines = this.getSommets();

        for (Machine m : machines)
        {
            if(m.getIp() == null){
                while(machines.stream().anyMatch(a -> ipCourant.equals(a.getIp()))){
                    ipCourant.increment();
                    if(ipCourant.equals(this.broadcast)){
                        this.logs.add(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date())+" : Erreur DHCP : l'adresse IP traitee est identique a l'adresse de broadcast");
                        throw new BadIpException(this.broadcast);
                    }
                }
                m.setIp(ipCourant);
            }
        }
        this.logs.add(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date())+" : Realisation du protocole DHCP");
    }

    /**
     * Methode simulant un serveur DHCP qui renvoie une adresse IP a la machine qui l'appelle
     * @return Premiere adresse IP disponible
     * @throws BadIpException Plus d'adresse IP libre dans le reseau du routeur
     */
    public Ip DHCPM() throws BadIpException {
        Ip ipCourant = new Ip(this.reseau);
        ipCourant.increment();
        Vector<Machine> machines = this.getSommets();

        while(machines.stream().anyMatch(a -> ipCourant.equals(a.getIp()))){
            ipCourant.increment();
            if(ipCourant.equals(this.broadcast)){
                this.logs.add(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date())+" : Erreur DHCP : plus d'adresses disponibles dans ce reseau");
                throw new BadIpException(this.broadcast);
            }
        }
        return ipCourant;
    }

    /**
     * Getteur de la liste des logs du routeur
     * @return Liste des logs du routeur
     */
    public ArrayList<String> getLogs(){
        return this.logs;
    }

    /**
     * Affichage de la liste des logs du routeur
     */
    public void printLogs(){
        System.out.println("Liste des logs du routeur : "+"@Ext : "+this.ext.toString()+" , @Routeur : "+this.reseau.toString()+", @Broad : "+this.broadcast.toString()+", @Masque : "+this.masque.toString());
        for(String l: this.logs) System.out.println(l);
    }

    /**
     * Methode ajoutant une machine au reseau, puis appelle la fonction DHCP pour lui attribuer une adresse IP
     * @param m La machine a ajouter
     */
    public void addMachine(Machine m){
        this.addSommet(m);
        this.logs.add(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date())+" : Ajout machine "+m.getId());
        this.DHCP();
    }

    /**
     * Methode supprimant une machine du reseau
     * @param m La machine a supprimer
     * @return True si la machine a ete supprimee, false sinon
     */
    public boolean deleteMachine(Machine m){
        boolean res = this.deleteSommet(m);
        this.logs.add(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date())+" : Suppression machine "+m.getId());
        return res;
    }

    /**
     * Methode supprimant une machine du reseau par son id
     * @param id Id de la machine a supprimer
     * @return True si la machine a ete supprimee, false sinon
     */
    public boolean deleteMachine(int id){
        boolean res = this.deleteSommet(id);
        this.logs.add(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()) + " : Suppression machine " + id);
        return res;
    }

    /**
     * Fonction de ping: renvoie true si les deux machines se pingent, faux sinon.
     * @param source Machine emettrice du ping
     * @param dest Machine destination du ping
     * @return True si ping OK, false sinon
     */
    public boolean ping(Machine source, Machine dest){
        /*
        Il faudrait ici utiliser la fonction BFSPath pour les pings d'un reseau a un autre
        Cependant nous ne l'avons pas implemente car elle ne fonctionne pas a tous les coup.
         */
        return source.getReseau(this.masque).equals(dest.getReseau(this.masque));
    }

    /**
     * Methode permettant l'envoi de messages entre deux machines
     * @param message Le message a transmettre
     * @param expediteur L'expediteur du message
     * @param destinataire Le destinataire du message
     * @return True si le message a correctement ete envoye, false sinon
     */
    public boolean message(String message, Ip expediteur, Ip destinataire){
        if(ping(getMachineByIp(expediteur),getMachineByIp(destinataire))){
            Machine target = getMachineByIp(destinataire);
            target.getMessage(message,expediteur);
            return true;
        }
        else return false;
    }

    @Override
    public String toString(){
        String res = "Routeur "+this.id+": \n";
        res += super.toString();
        return res;
    }
}
