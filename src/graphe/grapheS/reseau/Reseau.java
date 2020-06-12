package graphe.grapheS.reseau;

import exceptions.BadIpException;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe representant un reseau de routeurs (reseau de reseau)
 */
public class Reseau {

    private int taille;
    HashMap<Ip,Routeur> networks;

    /**
     * Constructeur de la classe Reseau
     * @param taille La taille du reseau
     * @param tailleRouteurs La taille de chaque routeur
     * @throws BadIpException Plus aucune adresse IP disponible pour l'ensemble des routeurs du reseau
     */
    public Reseau(int taille, int tailleRouteurs) throws BadIpException {
        this.taille = taille;
        this.networks = new HashMap<>();
        Ip exts = new Ip(1,1,1,0);
        int inc = 0;
        while(inc < taille && exts.compareTo(new Ip(0,0,0,0)) > 0){
            Ip ext = new Ip(1,1,1,0); // Eviter les references a l'instance exts
            ext.setIp(exts); // Si on place exts directement dans la hashmap, toutes les valeurs a l'interieur seront changees
            this.networks.put(ext,new Routeur(inc,tailleRouteurs,ext,new Ip(192,168,1,0),24));
            inc++;
            exts.increment();
        }
        if(inc < taille){
            System.out.println("Plus d'adresses IP disponibles pour tous les routeurs");
            throw new BadIpException(exts);
        }
    }

    /**
     * Constructeur de la classe Reseau
     * @param taille La taille du reseau
     * @param tailleRouteurs La taille de chaque routeur
     * @param externe La premiere adresse IP externe des routeurs
     * @param interne L'adresse IP interne des routeurs
     * @param masque Le masque de chaque routeur
     */
    public Reseau(int taille, int tailleRouteurs, Ip externe, Ip interne, int masque){
        this.taille = taille;
        this.networks = new HashMap<>();
        Ip exts = new Ip(0,0,0,0);
        exts.setIp(externe);
        int inc = 0;
        while(inc < taille && exts.compareTo(new Ip(0,0,0,0)) > 0){
            Ip ext = new Ip(1,1,1,0);
            ext.setIp(exts);
            this.networks.put(ext,new Routeur(inc,tailleRouteurs,ext,interne,masque));
            inc++;
            exts.increment();
        }
        if(inc < taille){
            System.out.println("Plus d'adresses IP disponibles pour tous les routeurs");
            throw new BadIpException(exts);
        }
    }

    /**
     * Constructeur de la classe Reseau
     * @param routeurs Le tableau de routeurs a ajouter au reseau
     */
    public Reseau(Routeur[] routeurs){
        this.taille = routeurs.length;
        this.networks = new HashMap<>();
        for(Routeur r: routeurs){
            if(this.networks.containsKey(r.getExt())) throw new IllegalArgumentException("Plusieurs routeurs ont la meme adresse externe");
            this.networks.put(r.getExt(),r);
        }
    }

    /**
     * Getter de la taille du reseau
     * @return La taille du reseau
     */
    public int getTaille() { return this.taille; }

    /**
     * Methode permettant d'obtenir le routeur possedant l'adresse IP donnee en parametre
     * @param ip L'adresse IP externe du routeur
     * @return Le routeur
     * @throws IllegalArgumentException Aucun routeur trouve pour cette adresse IP
     */
    public Routeur getRouteurFromIp(Ip ip) throws IllegalArgumentException {
        if(!this.networks.containsKey(ip)) throw new IllegalArgumentException("Pas de routeur possedant cette adresse IP");
        return this.networks.get(ip);
    }

    /**
     * Methode permettant d'obtenir le routeur possedant l'ID donnee en parametre
     * @param id L'ID du routeur a obtenir
     * @return Le routeur
     * @throws IllegalArgumentException Aucun routeur trouve pour cet ID
     */
    public Routeur getRouteurFromId(int id) throws IllegalArgumentException{
        Map.Entry<Ip,Routeur> res =  this.networks.entrySet().stream()
                .filter(v -> v.getValue().getId() == id)
                .findFirst().orElse(null);
        if(res == null) throw new IllegalArgumentException("Pas de Routeur d'id "+id);
        return res.getValue();
    }

    /**
     * Methode permettant l'ajout d'un routeur au reseau
     * @param r Le routeur a ajouter
     * @throws IllegalArgumentException Adresse IP Ext deja presente dans le reseau
     */
    public void addRouteur(Routeur r) throws IllegalArgumentException {
        if(this.networks.containsKey(r.getExt())) throw new IllegalArgumentException("Adresse IP Ext deja attribuee dans le reseau, ajout impossible");
        this.networks.put(r.getExt(),r);
        this.taille++;
    }

    /**
     * Methode supprimant un routeur du reseau
     * @param ip L'IP externe du routeur
     * @return <b>True</b> si un routeur a ete supprime, <b>False</b> sinon
     */
    public boolean deleteRouteur(Ip ip){
        boolean res = false;
        if(this.networks.remove(ip) != null){
            res = true;
            this.taille--;
        }
        return res;
    }

    /**
     * Methode toString
     * @return String contenant les informations sur le reseau
     */
    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Reseau de "+this.taille+" routeurs.\n");
        this.networks.forEach((k,v) -> res.append("Routeur "+v.getId()+" , @Ext: "+v.getExt()+" , @Reseau: "+v.getReseau()+" , taille: "+v.getTaille()+"\n"));
        return res.toString();
    }
}
