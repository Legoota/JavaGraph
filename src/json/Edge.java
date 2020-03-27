package json;

/**
 * Classe representant une arete
 */
public class Edge {

    private String id;
    private String source;
    private String target;

    /**
     * Constructeur d'une arete
     * @param id L'id de l'arete
     * @param source Le sommet source
     * @param target Le sommet cible
     */
    public Edge(int id, Node source, Node target){
        this.id = "e" + id;
        this.source = source.getId();
        this.target = target.getId();
    }

    /**
     * Getter de l'id de l'arete
     * @return L'id de l'arete
     */
    public String getId() {
        return this.id;
    }

    /**
     * Getter de l'id du sommet de la source
     * @return L'id du sommet de la source
     */
    public String getSource() {
        return this.source;
    }

    /**
     * Getter de l'id du sommet de la cible
     * @return L'id du sommet de la cible
     */
    public String getTarget() {
        return this.target;
    }

    /**
     * Methode permettant de savoir si l'arete existe deja (pour eviter les redondances)
     * @param a Le premier sommet
     * @param b Le deuxieme sommet
     * @return True si l'arete existe deja, false sinon
     */
    public boolean alreadyExisting(Node a, Node b){
        return ((a.getId() == this.source && b.getId() == this.target) || (b.getId() == this.source && a.getId() == this.target));
    }
}
