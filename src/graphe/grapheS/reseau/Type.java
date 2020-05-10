package graphe.grapheS.reseau;

public enum Type {
    PC ("PC"),
    ROUTER ("ROUTER");

    private String type;

    Type(String type){
        this.type = type;
    }

    public String toString(){
        return this.type;
    }

    public String getType(){
        return this.type;
    }
}

