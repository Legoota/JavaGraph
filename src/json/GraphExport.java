package json;

import graphe.grapheS.GraphS;
import graphe.grapheS.Sommet;

import java.util.Vector;

/**
 * Classe de graphe adaptee a l'export (sommets et aretes)
 */
public class GraphExport {

    private Vector<Node> nodes;
    private Vector<Edge> edges;

    /**
     * Constructeur de GraphExport
     * @param graphs Instance de GraphS a transformer en GraphExport
     */
    public GraphExport(GraphS<Sommet> graphs){
        this.nodes = new Vector<>();
        this.edges = new Vector<>();
        for(int i = 0; i < graphs.getTaille(); i++){
            this.nodes.add(new Node(i));
        }


        int x = 0;
        for(int j = 0; j < graphs.getTaille(); j++){
            for(int k = 0; k < graphs.getSommets().get(j).getVoisins().size(); k++){
                if(!alreadyExistingEdge(this.nodes.get(graphs.getSommets().get(j).getId()),this.nodes.get(graphs.getSommets().get(j).getVoisins().get(k).getId()))){
                    this.edges.add(new Edge(x,this.nodes.get(graphs.getSommets().get(j).getId()),this.nodes.get(graphs.getSommets().get(j).getVoisins().get(k).getId())));
                    x++;
                }
            }
        }
    }

    /**
     * Methode permettant de convertir le GraphExport en GraphS
     * @return GraphS correspondant au GraphExport
     */
    public GraphS toGraphS() {
        GraphS graphS = new GraphS(this.nodes.size());
        for(int i = 0; i < this.edges.size(); i++) graphS.addArete(this.edges.get(i).getSourceInt(),this.edges.get(i).getTargetInt());
        return graphS;
    }

    /**
     * Methode permettant de savoir si l'arete est deja dans la liste edges
     * @param a Premier sommet
     * @param b Deuxieme sommet
     * @return True si l'arete est deja presente, false sinon
     */
    private boolean alreadyExistingEdge(Node a, Node b){
        boolean res = false;
        for(int i = 0; i < edges.size(); i++){
            if(edges.get(i).alreadyExisting(a,b)){
                res = true;
            }
        }
        return res;
    }

    /**
     * Getter du vecteur des aretes
     * @return Vecteur des aretes
     */
    public Vector<Edge> getEdges() {
        return this.edges;
    }

    /**
     * Getter du vecteur des sommets
     * @return Vecteur des sommets
     */
    public Vector<Node> getNodes() {
        return this.nodes;
    }

    /**
     * Methode d'affichage du GraphExport
     * @return String contenant le GraphExport
     */
    @Override
    public String toString(){
        String res = "Nodes : [ ";
        for(int i = 0; i < this.nodes.size(); i++) res += this.nodes.get(i).getId() + ", ";
        res += "],\nEdges : [\n";
        for(int j = 0; j < this.edges.size(); j++){
            res += "id: " + this.edges.get(j).getId() + " - source: " + this.edges.get(j).getSource() + " - destination: " + this.edges.get(j).getTarget() + ", \n";
        }
        return res + "]";
    }
}
