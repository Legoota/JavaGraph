package graphe.grapheS.reseau;

import exceptions.BadIpException;
import exceptions.BadSizeGrapheException;
import graphe.grapheS.GraphS;
import java.util.Vector;

/**
 * Classe correspondant a un reseau
 */
public class Reseau extends GraphS<Machine> {

    private Ip reseau;
    private Ip broadcast;
    private Ip masque;

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
        for(int i = 0; i < taille; i++){ machines.add(new Machine(i)); }
        this.setSommets(machines);
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
        for(int i = 0; i < taille; i++){ machines.add(new Machine(i)); }
        this.setSommets(machines);

        int partieHote = 32 - masque;
        if(partieHote < 8){
            this.reseau = new Ip(ip.getA(),ip.getB(),ip.getC(),(int)(ip.getD()-ip.getD()%(Math.pow(2,partieHote))));
            this.broadcast = new Ip(ip.getA(),ip.getB(),ip.getC(),(int)(ip.getD()-ip.getD()%(Math.pow(2,partieHote))+Math.pow(2,partieHote)-1));
        }
        else if (partieHote<16) {
            this.reseau = new Ip(ip.getA(),ip.getB(),(int)(ip.getC()-ip.getC()%(Math.pow(2, partieHote-8))),0);
            this.broadcast = new Ip(ip.getA(),ip.getB(),(int)(ip.getC()-ip.getC()%(Math.pow(2, partieHote-8))+Math.pow(2, partieHote-8)-1),255);
        }
        else if (partieHote<24) {
            this.reseau = new Ip(ip.getA(),(int)(ip.getB()-ip.getB()%(Math.pow(2, partieHote-16))),0,0);
            this.broadcast = new Ip(ip.getA(),(int)(ip.getB()-ip.getB()%(Math.pow(2, partieHote-16))+Math.pow(2, partieHote-16)-1),255,255);
        }
        else {
            this.reseau = new Ip((int)(ip.getA()-ip.getA()%(Math.pow(2, partieHote-24))),0,0,0);
            this.broadcast = new Ip((int)(ip.getA()-ip.getA()%(Math.pow(2, partieHote-24))+Math.pow(2, partieHote-24)-1),255,255,255);
        }
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
                    if(ipCourant.equals(this.broadcast)) throw new BadIpException(this.broadcast);
                }
                m.setIp(ipCourant);
            }
        }
    }
}
