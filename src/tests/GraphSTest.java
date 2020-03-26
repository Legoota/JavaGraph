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

    @Before
    public void setUp() {
        graphe = new GraphS(5);
        graphe.addArete(0,1);
        graphe.addArete(2,3);
        graphe.addArete(2,1);
        graphe.addArete(1,3);
        graphe.addArete(4,0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void isVoisinNegatif() {
        graphe.isVoisin(-1, 1);
        fail("Aurait du lancer IllegalArgumentException");
    }

    @Test
    public void isVoisinOk() {
        assertEquals(true, graphe.isVoisin(0,1));
    }

    @Test
    public void isVoisinNotOk() {
        assertEquals(false, graphe.isVoisin(0,3));
    }

    @Test (expected = IllegalArgumentException.class)
    public void addAreteNegatif() {
        graphe.addArete(-1,-1);
    }

    @Test
    public void addAreteOk() {
        assertEquals(true,(graphe.isVoisin(0,1)));
    }

    @Test
    public void addAreteNotOk() {
        assertEquals(false, graphe.isVoisin(0,3));
    }

    @Test
    public void distance() {
        //TODO corps test
    }
}