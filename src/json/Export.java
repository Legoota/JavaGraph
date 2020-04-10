package json;

import com.google.gson.Gson;
import graphe.grapheS.GraphS;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Classe permettant l'export d'un graphe au type JSON
 */
public class Export {

    /**
     * Main permettant l'export au format JSON
     * @param args Arguments du main
     */
    public static void main(String[] args){
        GraphS graphe = new GraphS(6);
        graphe.addArete(0,1);
        graphe.addArete(2,3);
        graphe.addArete(2,1);
        graphe.addArete(1,3);
        graphe.addArete(4,0);

        GraphExport grex = new GraphExport(graphe);
        System.out.println(grex.toString());

        Gson gson = new Gson();

        String json = gson.toJson(grex);

        System.out.println(json);

        // Ecriture dans un fichier export.json
        try (FileWriter writer = new FileWriter("export.json")) {
            gson.toJson(grex, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Methode statique permettant l'export au format JSON
     * @param graphS Le graphe a exporter
     */
    public static void exportToJson(GraphS graphS){
        // TODO: Ajout fonction export
    }
}
