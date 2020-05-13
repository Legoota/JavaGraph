package graphe.grapheS.reseau;

import exceptions.BadIpException;
import exceptions.BadSizeGrapheException;
import graphe.grapheS.GraphS;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

/**
 * Classe correspondant a un reseau
 */
public class Reseau extends GraphS<Machine> {

    private Ip reseau;
    private Ip broadcast;
    private Ip masque;
    private ArrayList<String> logs;

    /**
     * Constructeur d'un GraphS utilisant des Machines.
     * @param taille La taille  du GraphS a creer
     * @throws BadSizeGrapheException taille non valide (negative)
     */
    public Reseau(int taille) throws BadSizeGrapheException {
        super();
        if(taille < 0) throw new BadSizeGrapheException(taille);
        this.setTaille(taille);
        Vector<Machine> machines = new Vector<>();
        for(int i = 0; i < taille; i++){ machines.add(new Machine(i, Machine.Type.PC)); }
        this.setSommets(machines);
        this.logs = new ArrayList<>();
        this.logs.add(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date())+" : Creation d'un reseau de taille "+this.getTaille());
    }

    /**
     * Constructeur qui cree un GraphS avec des sommets. Met a jour les attributs relatifs aux adresses dans le reseau
     * @param taille Taille du GraphS a generer
     * @param ip Une IP contenue dans la plage disponible pour ce reseau
     * @param masque Le masque correspondant
     * @throws BadSizeGrapheException taille non valide (negative)
     */
    public Reseau(int taille,Ip ip, int masque) throws BadSizeGrapheException {
        super();
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
        this.logs.add(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date())+" : Creation d'un reseau de taille "+this.getTaille()+", d'adresse "+ip.toString()+"/"+masque);
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
     * Getter de l'ip du reseau
     * @return IP du reseau
     */
    public Ip getReseau() {
        return this.reseau;
    }

    /**
     * Getter de l'ip de broadcast du reseau
     * @return IP de broadcast du reseau
     */
    public Ip getBroadcast() {
        return this.broadcast;
    }

    /**
     * Getter de l'ip du masque du reseau
     * @return IP du masque du réseau
     */
    public Ip getMasque() {
        return this.masque;
    }

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
     * Getteur de la liste des logs du reseau
     * @return Liste des logs du reseau
     */
    public ArrayList<String> getLogs(){
        return this.logs;
    }

    /**
     * Affichage de la liste des logs du reseau
     */
    public void printLogs(){
        System.out.println("Liste des logs du reseau : "+"@Reseau : "+this.reseau.toString()+", @Broad : "+this.broadcast.toString()+", @Masque : "+this.masque.toString());
        for(String l: this.logs) System.out.println(l);
    }
}
