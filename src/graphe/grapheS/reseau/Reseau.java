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

    public Reseau(int taille, int tailleRouteurs) throws BadIpException {
        this.taille = taille;
        this.networks = new HashMap<>();
        Ip exts = new Ip(1,1,1,0);
        int inc = 0;
        while(inc < taille && exts.compareTo(new Ip(0,0,0,0)) > 0){
            Ip ext = new Ip(1,1,1,0); // Eviter les references a l'instance exts
            ext.setIp(exts); // Si on place exts directement dans la hashmap, toutes les valeurs a l'interieur seront changees
            networks.put(ext,new Routeur(inc,tailleRouteurs,ext,new Ip(192,168,1,0),24));
            inc++;
            exts.increment();
        }
    }

    /**
     * Getter de la taille du reseau
     * @return La taille du reseau
     */
    public int getTaille() {
        return this.taille;
    }

    /**
     * Methode permettant l'ajout d'un routeur au reseau
     * @param r Le routeur a ajouter
     * @throws IllegalArgumentException Adresse IP Ext deja presente dans le reseau
     */
    public void addRouteur(Routeur r) throws IllegalArgumentException {
        if(networks.containsKey(r.getExt())) throw new IllegalArgumentException("Adresse IP Ext deja attribuee dans le reseau, ajout impossible");
        networks.put(r.getExt(),r);
    }

    /**
     * Methode permettant d'obtenir le routeur possedant l'adresse IP donnee en parametre
     * @param ip L'adresse IP du routeur
     * @return Le routeur
     * @throws IllegalArgumentException Aucun routeur trouve pour cette adresse IP
     */
    public Routeur getRouteurFromIp(Ip ip) throws IllegalArgumentException {
        if(!networks.containsKey(ip)) throw new IllegalArgumentException("Pas de routeur possedant cette adresse IP");
        return networks.get(ip);
    }

    public boolean deleteRouteur(Ip ip){
        return networks.remove(ip) != null ? true : false;
    }

    public Routeur getRouteurFromId(int id) throws IllegalArgumentException{
        Map.Entry<Ip,Routeur> res =  networks.entrySet().stream()
                .filter(v -> v.getValue().getId() == id)
                .findFirst().orElse(null);
        if(res == null) throw new IllegalArgumentException("Pas de Routeur d'id "+id);
        return res.getValue();
    }

    /**
     * Methode toString
     * @return String contenant les informations sur le reseau
     */
    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(this.taille+" routeurs.\n");
        networks.forEach((k,v) -> res.append("Routeur "+v.getId()+" , @Ext: "+v.getExt()+" , @Reseau: "+v.getReseau()+" , taille: "+v.getTaille()+"\n"));
        return res.toString();
    }
}
