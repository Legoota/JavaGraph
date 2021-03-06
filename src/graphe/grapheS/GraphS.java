package graphe.grapheS;

import exceptions.BadSizeGrapheException;
import graphe.Graphe;

import java.util.LinkedList;
import java.util.Vector;

/**
 * Classe d'un graphe contenant des sommets
 */
public class GraphS <S extends Sommet> implements Graphe {

    private int taille;
    private Vector<S> sommets = new Vector<>();

    /**
     * Constructeur d'un Graphe utilisant des sommets.
     * @param taille taille du graphe
     * @throws IllegalArgumentException taille non valide (negative)
     */
    public GraphS(int taille) throws BadSizeGrapheException {
        if(taille < 0) throw new BadSizeGrapheException(taille);
        this.taille = taille;
        for(int i = 0; i < taille; i++) this.sommets.add((S)new Sommet(i));
    }

    /**
     * Constructeur par defaut
     */
    public GraphS(){}

    /**
     * Setter de taille (utilise par classe fille)
     * @param taille La taille du GraphS
     */
    public void setTaille(int taille){ this.taille = taille; }

    /**
     * Setter de sommet (utilise par classe fille)
     * @param sommets Le vecteur de sommets du GraphS
     */
    public void setSommets(Vector<S> sommets) {
        this.sommets = sommets;
    }

    /**
     * Methode ajoutant un sommet a la liste de sommets
     * @param sommet Le sommet a ajouter
     */
    public void addSommet(S sommet) throws IllegalArgumentException{
        if(this.sommets.stream().anyMatch(s -> s.getId() == (sommet.getId()))) throw new IllegalArgumentException("Sommet deja existant");
        this.sommets.add(sommet);
        this.taille++;
    }

    /**
     * Methode supprimant un sommet de la liste de sommets
     * @param sommet Le sommet a supprimer
     * @return <b>True</b> si supprime, <b>False</b> sinon
     */
    public boolean deleteSommet(S sommet){
        if(this.sommets.remove(sommet)) {
            for(Sommet s: this.sommets){
                if(s.getVoisins().contains(sommet)) deleteArete(s,sommet);
            }
            this.taille--;
            return true;
        }
        return false;
    }

    /**
     * Methode supprimant un sommet de la liste par son id
     * @param id L'id du sommet a supprimer
     * @return <b>True</b> si supprime, <b>False</b> sinon
     */
    public boolean deleteSommet(int id){
        for(Sommet s: this.sommets){
            if(s.getVoisins().contains(getSommetbyId(id))) deleteArete(id, s.getId());
        }
        if(this.sommets.remove(getSommetbyId(id))) {
            this.taille--;
            return true;
        }
        return false;
    }

    /**
     * Methode supprimant une arete entre deux sommets
     * @param i Le premier sommet
     * @param j Le second sommet
     * @return <b>True</b> si une arete a ete supprimee, <b>False</b> sinon
     */
    public boolean deleteArete(Sommet i, Sommet j){
        boolean res, res2;
        res = i.getVoisins().remove(j);
        res2 = j.getVoisins().remove(i);
        return  res || res2;
    }

    /**
     * Methode supprimant une arete entre deux sommets
     * @param i Id du premier sommet
     * @param j Id du deuxieme sommet
     * @return <b>True</b> si une arete a ete supprimee, <b>False</b> sinon
     * @throws IllegalArgumentException Un des sommets est negatif
     */
    public boolean deleteArete(int i, int j) throws IllegalArgumentException {
        if(i < 0 || j < 0) throw new IllegalArgumentException("Un des sommets est negatif");
        boolean res, res2;
        res = this.getSommetbyId(i).getVoisins().remove(this.getSommetbyId(j));
        res2 = this.getSommetbyId(j).getVoisins().remove(this.getSommetbyId(i));
        return res || res2;
    }

    /**
     * Getter permettant de recuperer la taille du graphe
     * @return La taille du graphe
     */
    public int getTaille() {
        return this.taille;
    }

    /**
     * Getter permettant de recuperer le vecteur de sommets du graphe
     * @return Le vecteur de sommets du graphe
     */
    public Vector<S> getSommets() {
        return this.sommets;
    }

    /**
     * Methode permettant de recuperer un sommet par son id
     * @param id L'id du sommet
     * @return Le sommet
     * @throws IllegalArgumentException Id pas valide
     */
    public Sommet getSommetbyId(int id) throws IllegalArgumentException {
        for(Sommet s: this.sommets) {
            if(s.getId() == id) return s;
        }
        throw new IllegalArgumentException("ID pas valide");
    }

    /**
     * Ajoute une arete entre deux sommets
     * @param i Premier sommet
     * @param j Deuxieme sommet
     * @throws IllegalArgumentException Sommet non valide
     */
    public void addArete(int i, int j) throws IllegalArgumentException {
        if(i < 0 || j < 0) throw new IllegalArgumentException("Un des sommets est negatif");
        this.sommets.get(i).addVoisin(this.sommets.get(j));
    }

    @Override
    public boolean isVoisin(int i, int j) throws IllegalArgumentException {
        if (i < 0 || j < 0) throw new IllegalArgumentException("Un des sommets est negatif");
        return sommets.get(i).isVoisin(sommets.get(j));
    }

    @Override
    public int distance(int i, int j) throws IllegalArgumentException {
        if (i < 0 || j < 0) throw new IllegalArgumentException("Au moins un des sommets est negatif");
        LinkedList<Integer> l = new LinkedList<>();
        l.add(i);
        sommets.get(i).setFlag(true);
        LinkedList<Integer> ldistance = new LinkedList<>();
        ldistance.add(0);

        while(l.size() != 0) {
            Sommet sommetCourant = sommets.get(l.getFirst());
            for(int x = 0; x<sommetCourant.getVoisins().size(); x++) {
                Sommet voisinCourant = sommetCourant.getVoisins().get(x);
                if (voisinCourant.getId() == j){
                    for(int y = 0; y < this.taille; y++) { this.sommets.get(y).setFlag(false); } // Reinitialisation du flag
                    return ldistance.getFirst()+1;
                }
                if(!voisinCourant.getFlag()){
                    voisinCourant.setFlag(true);
                    l.add(voisinCourant.getId());
                    ldistance.add(ldistance.getFirst()+1);
                }
            }
            l.remove();
            ldistance.remove();
        }
        for(int y = 0; y < this.taille; y++) { this.sommets.get(y).setFlag(false); } // Reinitialisation du flag
        return -1; // Cas ou pas de chemin entre les sommets
    }

    /**
     * Methode realisant un graphe BFS a partir d'un graphS
     * @return Un graphS BFS
     */
    public GraphS BFS() {
        GraphS bfs = new GraphS<S>(this.taille);
        LinkedList<Integer> l = new LinkedList<>();
        l.add(0);
        this.sommets.get(0).setFlag(true);
        while(l.size() != 0) {
            Sommet sommetCourant = this.sommets.get(l.getFirst());
            for(int i = 0; i < sommetCourant.getVoisins().size(); i++){
                Sommet voisinCourant = sommetCourant.getVoisins().get(i);
                if(!voisinCourant.getFlag()){
                    bfs.addArete(sommetCourant.getId(),voisinCourant.getId());
                    voisinCourant.setFlag(true);
                    l.add(voisinCourant.getId());
                }
            }
            l.remove();
        }
        for(int j = 0; j < this.taille; j++) this.sommets.get(j).setFlag(false);

        return bfs;
    }

    /**
     * Methode realisant le plus court chemin entre un Sommet source et un Sommet target
     * @param source Sommet de depart
     * @param target Sommet d'arrivee
     * @return Un vecteur du chemin des sommets
     * @throws IllegalArgumentException Aucun chemin possible
     */
    public Vector<Sommet> BFSPath(Sommet source, Sommet target) throws IllegalArgumentException, ArithmeticException {
        if(distance(source.getId(),target.getId()) < 0) throw new IllegalArgumentException("Aucun chemin possible");
        for(int reset = 0; reset < this.taille; reset++) this.sommets.get(reset).resetChemin(); // reset des chemins les plus court
        if(source.getId() > target.getId()){ // inversion des sommets si target.id < source.id
            Sommet temp = source;
            source = target;
            target = temp;
        }

        GraphS bfs = new GraphS<S>(this.taille);
        LinkedList<Sommet> l = new LinkedList<>();
        l.add(source); // Depart de l'algorithme au sommet source (non fonctionnel dans certains cas) => entraine erreurs dans l'algo
        this.sommets.get(0).setFlag(true);
        LinkedList<Integer> alreadyPassed = new LinkedList<>();
        while(l.size() != 0) {
            Sommet sommetCourant = this.sommets.get(l.getFirst().getId());
            for(int i = 0; i < sommetCourant.getVoisins().size(); i++){
                Sommet voisinCourant = sommetCourant.getVoisins().get(i);
                if(!alreadyPassed.contains(voisinCourant.getId())){ // eviter les parcours en boucle
                    voisinCourant.addToChemin(sommetCourant); // ajout au chemin du sommet voisin le sommet courant
                    for(int chems = 0; chems < sommetCourant.getChemin().size(); chems++){
                        voisinCourant.addToChemin(sommetCourant.getChemin().get(chems)); // ajout au chemin du sommet voisin les sommets permettant d'acceder au sommet courant
                    }
                }
                if(!voisinCourant.getFlag()){
                    bfs.addArete(sommetCourant.getId(),voisinCourant.getId());
                    voisinCourant.setFlag(true);
                    l.add(voisinCourant);
                }
            }
            l.remove();
            alreadyPassed.add(sommetCourant.getId()); // ajout du sommet a la liste des sommets deja traites (eviter les parcours en boucle)
        }
        for(int j = 0; j < this.taille; j++) this.sommets.get(j).setFlag(false);
        target.getChemin().add(0,target); // ajout du sommet cible dans le chemin
        target.removeRedundancies(source); // suppression des redondances de chemin

        if(target.getChemin().size() > 1 && target.getChemin().get(0).getId() != target.getId() && target.getChemin().get(target.getChemin().size()-1).getId() != source.getId()) throw new ArithmeticException("Erreur de l'algorithme");

        return target.getChemin();
    }

    /**
     * Methode realisant un graph DFS a partir d'un graphS
     * @return Un graphe DFS
     */
    public GraphS DFS() {
        GraphS dfs = new GraphS<S>(this.taille);
        LinkedList<Integer> l = new LinkedList<>();
        l.add(0);
        this.sommets.get(0).setFlag(true);
        LinkedList<Integer> lposition = new LinkedList<>();
        lposition.add(0);
        while(l.size()!=0){
            Sommet sommetCourant = sommets.get(l.getLast());
            if(sommetCourant.getVoisins().size()==lposition.getLast()){
                l.removeLast();
                lposition.removeLast();
            }
            else{
                Sommet voisinCourant = sommetCourant.getVoisins().get(lposition.getLast());
                lposition.set(lposition.size()-1,lposition.getLast()+1);
                if(!voisinCourant.getFlag()){
                    dfs.addArete(sommetCourant.getId(),voisinCourant.getId());
                    voisinCourant.setFlag(true);
                    l.add(voisinCourant.getId());
                    lposition.add(0);
                }
            }
        }
        for (int i=0; i<taille;i++)sommets.get(i).setFlag(false);
        return dfs;
    }

    @Override
    public String toString(){
        String str = "";
        for(int i = 0; i < this.taille; i++) str += sommets.get(i).toString() + "\n";
        return str;
    }
}
