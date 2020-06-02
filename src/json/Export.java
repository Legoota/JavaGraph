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
     * Main de test de l'export au format JSON
     * @param args Arguments du main
     */
    public static void main(String[] args){
        GraphS graphe = new GraphS(6);
        graphe.addArete(0,1);
        graphe.addArete(2,3);
        graphe.addArete(2,1);
        graphe.addArete(1,3);
        graphe.addArete(4,0);

        Export.exportToJson(graphe,"export");
    }

    /**
     * Methode statique permettant l'export au format JSON
     * @param graphS Le graphe a exporter
     * @param nom Nom du fichier json
     */
    public static void exportToJson(GraphS graphS, String nom){
        GraphExport grex = new GraphExport(graphS);
        Gson gson = new Gson(); // objet permettant l'export en fichier JSON

        try (FileWriter writer = new FileWriter(nom+".json")) {
            gson.toJson(grex, writer); // export du graphe
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
