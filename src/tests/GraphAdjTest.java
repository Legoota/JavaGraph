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

    @Before
    public void setUp() {
        int[][] mat =  {{1,1,1,0,1,1,0,0,0},
                {1,1,0,1,0,0,0,0,0},
                {1,0,1,0,0,0,0,0,0},
                {0,1,0,1,0,1,1,0,0},
                {1,0,0,0,1,0,0,0,0},
                {1,0,0,1,0,1,1,0,0},
                {0,0,0,1,0,1,1,1,0},
                {0,0,0,0,0,0,1,1,0},
                {0,0,0,0,0,0,0,0,1}};// sommet 8 seul
        Matrice m = new Matrice(mat);
        graphe = new GraphAdj(m);
    }

    @Test (expected = IllegalArgumentException.class)
    public void isVoisinIndexNegatif() {
        graphe.isVoisin(-1, 1);
        fail("Aurait du lancé IllegalArgumentExeption");
    }

    @Test (expected = IllegalArgumentException.class)
    public void isVoisinIndexSuperieur() {
        graphe.isVoisin(1, 100);
        fail("Aurait du lancé IllegalArgumentExeption");
    }

    @Test
    public void isVoisinOk() {
        graphe.isVoisin(0, 2);
        assertEquals(true, graphe.isVoisin(0, 2));
    }

    @Test
    public void isVoisinNotOk() {
        graphe.isVoisin(0, 3);
        assertEquals(false, graphe.isVoisin(0, 3));
    }

    @Test (expected = IllegalArgumentException.class)
    public void distanceSommetInexistant() {
        graphe.distance(-1, 3);
        fail("Aurait du lancé IllegalArgumentExeption");
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void distanceSommetNonRelies() {
        graphe.distance(7, 8);
        fail("Aurait du lancé IndexOutOfBoundsException");
    }

    @Test
    public void distanceNulle() {
        assertEquals(0, graphe.distance(1, 1));
    }

    @Test
    public void distanceVoisin() {
        assertEquals(1, graphe.distance(0, 1));
    }

    @Test
    public void distanceDeuxSommets() {
        assertEquals(4, graphe.distance(4, 7));
    }
}