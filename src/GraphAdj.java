public class GraphAdj implements Graphe {

    private int[][] matrice;


    public GraphAdj(int[][] matAdj){
        this.matrice = matAdj;
    }

    @Override
    public boolean isVoisin(int i, int j) {

        return false;
    }
}
