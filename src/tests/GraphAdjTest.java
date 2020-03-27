package tests;

import graphe.grapheAdj.GraphAdj;
import matrice.Matrice;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Classe de test de la classe GraphAdj
 */
public class GraphAdjTest {

    private GraphAdj graphe;

    /**
     * Initialisation de la matrice d'adjacence pour les tests
     */
    @Before
    public void setUp() {
        int[][] mat =  {{0,1,1,0,1,1,0,0,0},
                {0,0,0,1,0,0,0,0,0},
                {1,0,0,0,0,0,0,0,0},
                {0,1,0,0,0,1,1,0,0},
                {1,0,0,0,0,0,0,0,0},
                {1,0,0,1,0,0,1,0,0},
                {0,0,0,1,0,1,0,1,0},
                {0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,0,0}};// sommet 8 seul
        Matrice m = new Matrice(mat);
        graphe = new GraphAdj(m);
    }

    /**
     * Test d'ajout d'un voisin dont l'index est negatif (doit renvoyer une erreur)
     */
    @Test (expected = IllegalArgumentException.class)
    public void isVoisinIndexNegatif() {
        graphe.isVoisin(-1, 1);
        fail("Aurait du lancé IllegalArgumentExeption");
    }

    /**
     * Test d'ajout d'un voisin dont l'index est superieur a l'ordre de la matrice (doit renvoyer une erreur)
     */
    @Test (expected = IllegalArgumentException.class)
    public void isVoisinIndexSuperieur() {
        graphe.isVoisin(1, 100);
        fail("Aurait du lancé IllegalArgumentExeption");
    }

    /**
     * Test de la methode isVoisin dans le cas ou les deux sommets sont voisins
     */
    @Test
    public void isVoisinOk() {
        graphe.isVoisin(0, 2);
        assertEquals(true, graphe.isVoisin(0, 2));
    }

    /**
     * Test de la methode isVoisin dans le cas ou les deux sommets ne sont pas voisins
     */
    @Test
    public void isVoisinNotOk() {
        graphe.isVoisin(0, 3);
        assertEquals(false, graphe.isVoisin(0, 3));
    }

    /**
     * Test de la fonction distance dans le cas ou un sommet n'existe pas (doit renvoyer une erreur)
     */
    @Test (expected = IllegalArgumentException.class)
    public void distanceSommetInexistant() {
        graphe.distance(-1, 3);
        fail("Aurait du lancé IllegalArgumentExeption");
    }

    /**
     * Test de la fonction distance dans le cas ou les deux sommets ne sont pas relies (doit renvoyer une erreur)
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void distanceSommetNonRelies() {
        graphe.distance(7, 8);
        fail("Aurait du lancé IndexOutOfBoundsException");
    }

    /**
     * Test de la fonction distance entre un sommet et lui-meme
     */
    @Test
    public void distanceNulle() {
        assertEquals(0, graphe.distance(1, 1));
    }

    /**
     * Test de la fonction distance dans le cas ou les sommets sont directement voisins
     */
    @Test
    public void distanceVoisin() {
        assertEquals(1, graphe.distance(0, 1));
    }

    /**
     * Test de la fonction distance dans le cas ou les sommets ne sont pas directement voisins
     */
    @Test
    public void distanceDeuxSommets() {
        assertEquals(4, graphe.distance(4, 7));
    }
}