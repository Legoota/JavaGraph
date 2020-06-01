package graphe.grapheS;

import java.util.Vector;

/**
 * Classe Sommet
 * Possede en attribut une liste des sommets voisins et un label
 */
public class Sommet {

    private int id;
    private Vector<Sommet> voisins=new Vector<>();
    private boolean flag = false;
    private Vector<Sommet> chemin = new Vector<>();
    private String label;

    /**
     * Constructeur d'un sommet
     * @param id Le numero du sommet
     * @throws IllegalArgumentException Valeur de sommet negative
     */
    public Sommet(int id) throws IllegalArgumentException {
        if(id < 0) throw new IllegalArgumentException("Valeur de sommet negative impossible");
        this.id = id;
        this.label = Integer.toString(id);
    }

    /**
     * Setter de flag
     * @param val Valeur a attribuer au flag
     */
    public void setFlag(boolean val) { this.flag = val; }

    /**
     * Getter de flag
     * @return Valeur du flag
     */
    public boolean getFlag() { return this.flag; }

    /**
     * Methode permettant d'ajouter un element au chemin
     * @param s Le sommet a ajouter
     */
    public void addToChemin(Sommet s){
        this.chemin.add(s);
    }

    /**
     * Methode permettant de reinitialiser le chemin
     */
    public void resetChemin(){
        this.chemin = new Vector<>();
    }

    /**
     * Getter du vecteur chemin
     * @return Le vecteur chemin
     */
    public Vector<Sommet> getChemin(){ return this.chemin; }

    /**
     * Getter du vecteur voisins
     * @return Vecteur voisins
     */
    public Vector<Sommet> getVoisins() { return this.voisins; }

    /**
     * Getter du label
     * @return Valeur du label
     */
    public String getLabel() { return label; }

    /**
     * Setter du label
     * @param label Valeur du label
     */
    public void setLabel(String label) { this.label = label; }

    /**
     * Getter de l'id du sommet
     * @return L'id du sommet
     */
    public int getId() { return this.id; }

    /**
     * Ajoute un sommet dans la liste des sommets voisins
     * @param s Le sommet a ajouter
     */
    public void addVoisin(Sommet s) throws IllegalArgumentException {
        if(s.id < 0) throw new IllegalArgumentException("Le sommet ne peut pas etre negatif");
        try {
            if(!this.voisins.contains(s)){
                if(s.id == this.id) voisins.add(s);
                else{
                    voisins.add(s);
                    s.voisins.add(this);
                }
            }
        } catch(Exception e){ throw new IllegalArgumentException("Le sommet est inexistant"); }
    }

    /**
     * Verifie si le sommet rentre en parametre est bien un voisin
     * @param s Le voisin potentiel
     * @return true si voisin, false sinon
     */
    public boolean isVoisin(Sommet s) {
        return voisins.contains(s);
    }

    /**
     * Methode permettant de supprimer les chemins doublons
     * @param source Le sommet de depart du chemin
     */
    public void removeRedundancies(Sommet source){
        int redundancy = 0;
        for(int i = 0; i<this.chemin.size(); i++)
            if(this.chemin.get(i).getId() == source.getId()) redundancy++;

        if(redundancy > 1){ // 2 si chemin en doublon, etc
            Vector<Integer> dumpIds = new Vector<>();
            boolean firstpath = false; // true si un premier chemin jusqu'au sommet de depart est atteint
            for(int j = 0; j<this.chemin.size(); j++){
                if(firstpath) dumpIds.add(j); // ajout du sommet a la liste des sommets a supprimer
                if(this.chemin.get(j).getId() == source.getId()) firstpath = true; // le premier chemin est atteint
            }
            for(int k = dumpIds.size()-1;k>=0;k--) this.chemin.removeElementAt(dumpIds.get(k)); // suppression des sommets redondants
        }
    }

    @Override
    public String toString() {
        String str = String.valueOf(id);
        /*
        if(this.voisins.size()>0){
            str += ": [";
            for (int i = 0; i < voisins.size() - 1; i++) str += voisins.get(i).id + ", ";
            str += voisins.get(voisins.size()-1).id + "]";
        }*/
        return str;
    }
}
