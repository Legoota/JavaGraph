package json;

import com.google.gson.Gson;
import graphe.grapheS.GraphS;

import java.io.*;

/**
 * Classe permettant l'import d'un graphe au type JSON
 */
public class Import {

    /**
     * Main de test de l'import d'un graphe au format JSON
     * @param args Arguments du main
     */
    public static void main(String[] args){
        GraphS gs = null;
        try {
            gs = Import.fetchGraphS("arctic.json"); // test de l'import avec le fichier arctic.json
        } catch (FileNotFoundException e) { e.printStackTrace(); }
        System.out.println(gs.toString());
    }

    /**
     * Methode permettant de creer un GraphS a partir d'un fichier JSON
     * @param path Chemin du fichier JSON
     * @return GraphS correspondant au graphe du fichier
     * @throws FileNotFoundException Chemin du fichier invalide
     */
    public static GraphS fetchGraphS(String path) throws FileNotFoundException{
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(path)) { // lecture du fichier
            GraphExport grex = gson.fromJson(reader, GraphExport.class);

            return grex.toGraphS();

        } catch (IOException e) { throw new FileNotFoundException("Le fichier specifie est introuvable"); }
    }
}
