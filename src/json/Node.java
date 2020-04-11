package json;

/**
 * Classe representant un sommet
 */
public class Node {

    private String id;
    private String label;

    /**
     * Constructeur d'un Node
     * @param id L'id du sommet
     */
    public Node(int id){
        this.id = "n" + id;
        this.label = "Node " + id;
    }

    public Node(int id, String label){
        this.id = "n" + id;
        this.label = label;
    }

    /**
     * Getter de l'id du Node
     * @return L'id du Node
     */
    public String getId() {
        return id;
    }
}
