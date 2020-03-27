package tests;

import graphe.grapheS.GraphS;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Classe de test de la classe GraphS
 */
public class GraphSTest {
    GraphS graphe;

    /**
     * Initialisation d'un graphe de test
     */
    @Before
    public void setUp() {
        graphe = new GraphS(6);
        graphe.addArete(0,1);
        graphe.addArete(2,3);
        graphe.addArete(2,1);
        graphe.addArete(1,3);
        graphe.addArete(4,0);
    }

    /**
     * Test d'ajout d'une valeur negative (doit renvoyer une erreur)
     */
    @Test (expected = IllegalArgumentException.class)
    public void isVoisinNegatif() {
        graphe.isVoisin(-1, 1);
        fail("Aurait du lancer IllegalArgumentException");
    }

    /**
     * Test de presence d'une arete entre deux sommets
     */
    @Test
    public void isVoisinOk() {
        assertEquals(true, graphe.isVoisin(0,1));
    }

    /**
     * Test de non presence d'une arete entre deux sommets
     */
    @Test
    public void isVoisinNotOk() {
        assertEquals(false, graphe.isVoisin(0,3));
    }

    /**
     * Test d'ajout d'une arete avec un sommet negatif
     */
    @Test (expected = IllegalArgumentException.class)
    public void addAreteNegatif() {
        graphe.addArete(-1,1);
    }

    /**
     * Test d'ajout d'une arete entre deux sommets (valide)
     */
    @Test
    public void addAreteOk() {
        assertEquals(true,(graphe.isVoisin(0,1)));
    }

    /**
     * Test d'ajout d'une arete entre deux sommets (non valide)
     */
    @Test
    public void addAreteNotOk() {
        assertEquals(false, graphe.isVoisin(0,3));
    }

    /**
     * Test de distance entre deux sommets (valide)
     */
    @Test
    public void distanceOk() {
        assertEquals(3,graphe.distance(3,4));
    }

    /**
     * Test de distance entre deux sommets qui n'ont pas de chemin (non valide)
     */
    @Test
    public void distanceNotOk() {
        assertEquals(-1,graphe.distance(5,0));
    }

    /**
     * Test de distance avec un sommet inexistant (doit rendre une erreur)
     */
    @Test (expected = IllegalArgumentException.class)
    public void distanceError() {
        graphe.distance(-1,1);
        fail("Aurait du lancer IllegalArgumentException");
    }
}