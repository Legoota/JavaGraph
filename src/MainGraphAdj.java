public class MainGraphAdj {

    public static void main(String[] args){
        int[][] matrice =  {{0,1,1,0,1,1,0},{1,0,0,0,1,1,0},{1,0,0,0,1,0,0},{0,0,0,0,1,0,0},{1,1,1,1,0,0,0},{1,1,0,0,0,0,1},{0,0,0,0,0,1,0}};
        GraphAdj graphe = new GraphAdj(matrice);
    }
}
