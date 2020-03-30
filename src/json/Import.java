package json;

import com.google.gson.Gson;
import graphe.grapheS.GraphS;

import java.io.FileReader;
import java.io.IOException;

/**
 * Classe permettant l'import d'un graphe au type JSON
 */
public class Import {

    /**
     * Main permettant l'import d'un graphe au format JSON
     * @param args Arguments du main
     */
    public static void main(String[] args){
        Gson gson = new Gson();

        // Lecture d'un fichier JSON
        try (FileReader reader = new FileReader("export.json")) {
            GraphExport grex = gson.fromJson(reader, GraphExport.class);
            System.out.println("GraphExport recupere:");
            System.out.println(grex.toString());

            System.out.println("GraphS produit:");
            GraphS graphS = grex.toGraphS();
            System.out.println(graphS.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
